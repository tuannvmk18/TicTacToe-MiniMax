package com.company;

public class Main {

    public static void main(String[] args) {
        Board b = new Board(600,600);
        Computer a = new Computer();
        b.Initialization();
        a.add_Action_with_Computer(b);
    }
}
