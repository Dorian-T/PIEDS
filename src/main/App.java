package main;

import java.awt.Frame;

import main.model.*;
import main.view_controller.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Case c = new Case(1, 2);
        Frame f = new Frame(c);
        c.addObserver(f);
        f.setVisible(true);
    }
}
