package main;

import main.model.grid.GridGame;
import main.view_controller.*;


public class App {
	public static void main(String[] args) throws Exception {
        GridGame g = new GridGame("data/levels/level13.txt");
		Frame f = new Frame(g, g.getWidth(), g.getHeight());
		g.getPlayer().addObserver(f);
		f.setVisible(true);
	}
}
