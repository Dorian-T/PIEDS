package main.model.grid.cell;

import java.util.Observable;

//import main.model.Cell;
//import main.model.Wall;
import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;
import main.model.grid.Point;


/**
 * Represents a cell in the grid of the game.
 */
public class Cell extends Observable {

	// === Variables ===

	GridGame grid;
	Entity occupant;
	public static final Object imagePath = "emptyCellTemp.png";


	// === Constructors ===

	/**
	 * Constructor of the class Cell
	 *
	 * @param grid
	 */
	public Cell(GridGame grid) {
		this.grid = grid;
		occupant = null;
	}

	public static Cell loadCell(GridGame grid, char c) throws IllegalArgumentException {
		Cell res = null;
		switch(c) {
			case '#':
				res = new Wall(grid);
				break;
			case '.':
				res = new Cell(grid);
				break;
			default:
				throw new IllegalArgumentException("Invalid character for cell.");
		}
		return res;
	}


	// === Getters ===

	public Entity getOccupant() {
		return occupant;
	}

	public Cell getCell(Cell cell, Direction dir) {
		return grid.getCell(cell, dir);
	}


	// === Methods ===

	// public void move(Direction dir) { // C'est quoi ?
	// 	if(dir == Direction.LEFT) { //tester si c'est possible ?
	// 		x--; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
	// 	}else if(dir == Direction.RIGHT) { //tester si c'est possible ?
	// 		x++; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
	// 	}else if(dir == Direction.UP) { //tester si c'est possible ?
	// 		y--; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
	// 	}else if(dir == Direction.DOWN) { //tester si c'est possible ?
	// 		y++; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
	// 	}
	// 	setChanged();
	// 	notifyObservers();
	// }

	public boolean enter(Entity e, Direction dir) {
		if(occupant == null) {
			e.setDirection(dir);
			occupant = e;
			return true;
		}
		else {
			Cell newCell = grid.getCell(this, dir);
			if(newCell.enter(e, dir)) {
				occupant = e;
				return true;
			}
			return false;
		}
	}

	// public boolean leave() {
	// 	occupant = null;
	// 	return true;
	// }

	public void begin() {
		setChanged();
		notifyObservers();
	}
}