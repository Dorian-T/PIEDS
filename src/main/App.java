package main;

import main.model.*;
import main.view_controller.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Cell c = new Cell(0, 0);
        Frame f = new Frame(c);
        c.addObserver(f);
        c.begin();
        f.setSize(800,450);
        f.setVisible(true);
    }
}
