package main.model.grid.cell;

import java.util.Observable;

import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;


/**
 * Represents a cell in the grid of the game.
 * A cell is a place where an entity can be.
 * 
 * @see Observable
 */
public class Cell extends Observable {

	// === Variables ===

	/**
	 * The grid where the cell is.
	 */
	GridGame grid;

	/**
	 * The entity that is in the cell.
	 */
	Entity occupant;

	/**
	 * The path of the image of an empty cell.
	 */
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

	/**
	 * Load a cell from a character.
	 *
	 * @param grid the grid where the cell is
	 * @param c the character representing the cell
	 * @return the cell corresponding to the character
	 * @throws IllegalArgumentException if the character is invalid
	 */
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

	/**
	 * Returns the occupant of the cell.
	 *
	 * @return the occupant of the cell
	 */
	public Entity getOccupant() {
		return occupant;
	}

	/**
	 * Returns the cell in the specified direction.
	 *
	 * @param cell the cell from which to get the new cell
	 * @param dir the direction from which to get the new cell
	 * @return the cell in the specified direction
	 */
	public Cell getCell(Cell cell, Direction dir) {
		return grid.getCell(cell, dir);
	}


	// === Methods ===

	/**
	 * Returns true if the entity can enter the cell, false otherwise.
	 *
	 * @param e the entity that wants to enter the cell
	 * @param dir the direction from which the entity wants to enter the cell
	 * @return true if the entity can enter the cell, false otherwise
	 */
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