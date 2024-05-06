package main.model.entity;

import main.model.grid.cell.Cell;

import java.util.Observable;

//import main.model.Box;
//import main.model.Entity;
//import main.model.Player;
import main.model.grid.Direction;
import main.model.grid.GridGame;
import main.model.grid.Point;

public abstract class Entity extends Observable{
	Cell cell;
	Direction dir;
	public static final Object imagePath = "weirdEntity.png";


	// === Constructors ===

	public Entity(Cell cell) {
		this.cell = cell;
		dir = Direction.LEFT;
	}

	/*public void move(Direction dir) {
		gj.move(this, dir);
	}*/

	public void setDirection(Direction dir) {
		this.dir = dir;
	}

	public boolean moveTo(Direction dir) {
		Cell newCell = cell.getCell(cell, dir);
		if(newCell.enter(this, dir)) {
			cell = newCell;
			return true;
		}
		return false;
	}

	public static Entity loadEntity(char c, Cell cell) {
		Entity res = null;
		switch(c) {
			case 'p':
				res = new Player(cell);
				break;
			case 'b':
				res = new Box(cell);
				break;
			case '.': // empty cell
				break;
			default:
				System.out.println("Erreur de caractere impossible");
				break;
		}
		return res;
	}
}
