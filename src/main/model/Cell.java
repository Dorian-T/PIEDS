package main.model;

import java.awt.Frame;
import java.util.Observable;

public class Cell extends Observable{
	int x;
	int y;
	
	Entity occupant;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void move(Direction dir) {
		if(dir == Direction.LEFT) { //tester si c'est possible ?
			x--; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
		}else if(dir == Direction.RIGHT) { //tester si c'est possible ?
			x++; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
		}else if(dir == Direction.UP) { //tester si c'est possible ?
			y--; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
		}else if(dir == Direction.DOWN) { //tester si c'est possible ?
			y++; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
		}
		setChanged();
		notifyObservers();
	}

	public boolean enter(Entity e, Direction dir) {
		if(occupant == null) {
			//depl(e);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean quit(Entity e) {
		// TODO Auto-generated method stub
		//euh ça ressemble a enter ? on fait quitter dans une direction depuis enter ?
		return true;
	}
	
	public void begin() {
		setChanged();
		notifyObservers();
	}
	
	public static Cell loadCell(char c, int x, int y) {
		Cell res = null;
		switch(c) {
			case 'w':
				res = new Wall(x, y);
				break;
			default:
				System.out.println("Erreur de charactere impossible");
				break;
		}
		return res;
	}

	public Entity getOccupant() {
		// TODO Auto-generated method stub
		return occupant;
	}
}