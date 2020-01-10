package com.company;

import javax.swing.*;

public abstract class View extends JFrame {
    int Height, Weight;

    public View(int Height, int Weight){
        this.Height = Height;
        this.Weight = Weight;
    }

    public abstract void Initialization();
}
