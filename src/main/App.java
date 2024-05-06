package main;

import main.model.*;
import main.model.grid.cell.Cell;
import main.model.grid.GridGame;
import main.view_controller.*;

public class App {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello, World!");
        GridGame g = new GridGame("data/test.txt");
		Frame f = new Frame(g);
		//f.setVisible(true);
	}
}
