package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Computer {

    public void add_Action_with_Computer(Board board){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board.btn[i][j].addActionListener(actionEvent -> {
                    JButton ml = (JButton)actionEvent.getSource();
                    if (board.is_Empty(ml)){
                        ml.setForeground(Color.BLUE);
                        ml.setText(board.X_Player);
                        if(board.is_Win(board.X_Player)) {
                            JOptionPane.showMessageDialog(null, "Ban da chien thang",   "Thong bao", JOptionPane.INFORMATION_MESSAGE);
                            board.clear();
                            return;
                        }
                        Point p = find_Best_Move(board);
                        if(p != null)
                            board.btn[p.x][p.y].setText(board.O_Player);
                        if(board.is_Win(board.O_Player)){
                            JOptionPane.showMessageDialog(null, "Ban da thua cuoc",   "Thong bao: You're LOSER", JOptionPane.INFORMATION_MESSAGE);
                            board.clear();
                            return;
                        }
                        if(p == null){
                            JOptionPane.showMessageDialog(null, "Tran dau hoa",   "Thong bao", JOptionPane.INFORMATION_MESSAGE);
                            board.clear();
                            return;
                        }
                    }
                });
            }
        }
    }

    public int minimax(Board board,int dept , boolean isX_Player){
        if(board.is_Win(board.X_Player))
            return 10 - dept;
        if(board.is_Win(board.O_Player))
            return -10 + dept;
        ArrayList<Point> list = board.get_EmptyButton();
        if(list.size() == 0)
            return 0;

        int bestVal;
        if(isX_Player){
            bestVal = Integer.MIN_VALUE;
            for(int i = 0; i < list.size(); i++){
                int x = list.get(i).x;
                int y = list.get(i).y;
                board.btn[x][y].setText(board.X_Player);
                int value = minimax(board, dept + 1, false);
                bestVal = Math.max(bestVal, value);
                board.btn[x][y].setText(board.NO_Player);
            }
            return bestVal;
        }else if(!isX_Player){
            bestVal = Integer.MAX_VALUE;
            for(int i = 0; i < list.size(); i++){
                int x = list.get(i).x;
                int y = list.get(i).y;
                board.btn[x][y].setText(board.O_Player);
                int value = minimax(board, dept + 1, true);
                bestVal = Math.min(bestVal, value);
                board.btn[x][y].setText(board.NO_Player);
            }
	    return bestVal;
        }
        return 0;
    }

    public Point find_Best_Move(Board board){
        Point p = null;
        int bestVal = Integer.MAX_VALUE;
        ArrayList<Point> list = board.get_EmptyButton();
        for(int i = 0; i < list.size(); i++){
            int x = list.get(i).x;
            int y = list.get(i).y;
            board.btn[x][y].setText(board.O_Player);
            int value = minimax(board, 0, true);
            if(value < bestVal){
                bestVal = Math.min(bestVal, value);
                p = list.get(i);
            }
            board.btn[x][y].setForeground(Color.RED);
            board.btn[x][y].setText(board.NO_Player);
        }
        return p;
    }
}
