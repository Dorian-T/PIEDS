package main.model.entity;

import java.util.Observable;

import main.model.grid.Direction;
import main.model.grid.cell.Cell;


public abstract class Entity extends Observable{
	Cell cell;
	Direction dir;
	public String imagePath = "weirdEntity.png";


	// === Constructors ===

	protected Entity(Cell cell) {
		this.cell = cell;
		dir = Direction.LEFT;
	}

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
