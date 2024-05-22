package main;

import main.view_controller.Menu;


/**
 * Main class of the game.
 * The game is a puzzle game where the player has to move boxes to reach a goal.
 * The game is composed of levels that are loaded from files.
 * The player can move in four directions and push boxes.
 * The player wins when all the boxes are on the goals.
 * 
 * @see Menu
 */
public class App {

	/**
	 * Main method of the game.
	 * Opens the menu of the game.
	 * 
	 * @param args the arguments of the program
	 */
	public static void main(String[] args) {
		openMenu();
	}

	/**
	 * Opens the menu of the game.
	 */
	public static void openMenu() {
		Menu menu = new Menu();
		menu.setVisible(true);
	}
}
