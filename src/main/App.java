package main;

import main.model.grid.GridGame;
import main.view_controller.*;


public class App {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello, World!");
        GridGame g = new GridGame("data/levels/level9.txt");
		Frame f = new Frame(g);
		g.getPlayer().addObserver(f);
		f.setVisible(true);
	}
}
