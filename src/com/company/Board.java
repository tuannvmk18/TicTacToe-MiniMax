package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends View implements Clear{
    protected JPanel panel;
    protected GridLayout grid;
    public JButton[][] btn;
    protected String X_Player = "X";
    protected String O_Player = "O";
    protected String NO_Player = "";

    public Board(int Height, int Weight){
        super(Height, Weight);
    }

    @Override
    public void Initialization() {
        panel = new JPanel();
        btn = new JButton[3][3];
        grid = new GridLayout();

        setDefaultCloseOperation(3);
        setResizable(false);
        setBounds(0, 0, super.Height, super.Weight);
        setVisible(true);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                btn[i][j] = new JButton();
                btn[i][j].setBackground(Color.WHITE);
                btn[i][j].setFont(new Font(Font.SERIF, Font.PLAIN, 80));
                add(btn[i][j]);
            }
        }

        setLayout(grid);
        grid.setColumns(3);
        grid.setRows(3);

        clear();
    }

    public boolean is_Empty(JButton btn) {
        return btn.getText() == NO_Player;
    }

    public boolean is_Win(String current_Player){
        if(btn[0][0].getText() == btn[1][1].getText() && btn[0][0].getText() == btn[2][2].getText() && btn[0][0].getText() != NO_Player && btn[0][0].getText() ==  current_Player)
            return true;
        if(btn[0][2].getText() == btn[1][1].getText() && btn[0][2].getText() == btn[2][0].getText() && btn[0][2].getText() != NO_Player && btn[0][2].getText() ==  current_Player)
            return true;

        for(int i = 0; i < 3; i++){
            if(btn[i][0].getText() == btn[i][1].getText() && btn[i][0].getText() == btn[i][2].getText() && btn[i][0].getText() != NO_Player && btn[i][0].getText() == current_Player)
                return true;
            if(btn[0][i].getText() == btn[1][i].getText() && btn[0][i].getText() == btn[2][i].getText() && btn[0][i].getText() != NO_Player && btn[0][i].getText() == current_Player)
                return true;
        }
        return false;
    }

    public boolean is_Draw(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(is_Empty(btn[i][j]))
                    return false;
            }
        }
        return is_Win(X_Player) || is_Win(O_Player);
    }

    public ArrayList<Point> get_EmptyButton(){
        ArrayList<Point> list = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(is_Empty(btn[i][j]))
                    list.add(new Point(i, j));
            }
        }
        return list;
    }

    @Override
    public void clear() {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                btn[i][j].setText("");
            }
        }
    }
}
