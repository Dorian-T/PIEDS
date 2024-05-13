package main.model.grid.cell;

import java.util.Observable;

import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;


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

	public void begin() {
		setChanged();
		notifyObservers();
	}
}