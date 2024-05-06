package main.model.grid.cell;

import java.util.Observable;

//import main.model.Cell;
//import main.model.Wall;
import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;
import main.model.grid.Point;


public class Cell extends Observable{

	// === Variables ===

	public static final Object imagePath = "emptyCell.png";
	int x;
	int y;
	Entity occupant;


	// === Constructors ===

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}


	// === Getters ===

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Entity getOccupant() {
		return occupant;
	}


	// === Methods ===

	public void move(Direction dir) { // C'est quoi ?
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
			e.setDirection(dir);
			occupant = e;
			return true;
		}
		else {
			if(e.moveTo(new Point(x, y, dir), dir, this)) {
				occupant = e;
				return true;
			}
			return false;
		}
	}

	public boolean leave() {
		occupant = null;
		return true;
	}

	public void begin() {
		setChanged();
		notifyObservers();
	}

	public static Cell loadCell(char c, int x, int y) {
		Cell res = null;
		switch(c) {
			case '#':
				res = new Wall(x, y);
				break;
			case '.':
				res = new Cell(x, y);
				break;
			default:
				System.out.println("Erreur de charactere impossible");
				break;
		}
		return res;
	}
}