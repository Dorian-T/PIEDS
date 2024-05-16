package main;

import main.model.grid.GridGame;
import main.view_controller.*;


public class App {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello, World!");
        GridGame g = new GridGame("data/test.txt");
		Frame2 f = new Frame2(g);
		g.getPlayer().addObserver(f);
		f.setVisible(true);
	}
}
