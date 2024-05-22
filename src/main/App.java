package main;

import main.view_controller.Menu;


public class App {
	public static void main(String[] args) {
		openMenu();
	}

	public static void openMenu() {
		Menu menu = new Menu();
		menu.setVisible(true);
	}
}
