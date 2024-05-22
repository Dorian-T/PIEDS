package main.model.grid.cell;

import java.util.Observable;

import main.model.Color;
import main.model.entity.Box;
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
	protected String imagePath;

	/**
	 * The id of the curent version of the image of the cell.
	 */
	protected int version;


	// === Constructors ===

	/**
	 * Constructor of the class Cell
	 *
	 * @param grid the grid where the cell is
	 */
	public Cell(GridGame grid) {
		this.grid = grid;
		occupant = null;
		version = 0;
	}

	/**
	 * Load a cell from a character.
	 *
	 * @param grid the grid where the cell is
	 * @param str the character representing the cell
	 * @return the cell corresponding to the character
	 * @throws IllegalArgumentException if the character is invalid
	 */
	public static Cell loadCell(GridGame grid, String str) throws IllegalArgumentException {
		Cell res = null;
		switch(str.charAt(0)) {
			case '#':
				res = new Wall(grid);
				break;
			case '.':
				res = new Ground(grid);
				break;
			case 'b':
				if(str.length() == 1)
					res = new Flag(grid);
				else
					res = new Flag(grid, Color.fromChar(str.charAt(1)));
				break;
			case 'r':
				if(str.length() == 1)
					res = new Rail(grid, 0);
				else
					res = new Rail(grid, ((int) str.charAt(1))-48 );
				break;
			case 'i':
				res = new Ice(grid);
				break;
			case 'd':
				res = new Door(grid);
				break;
			case 'k':
				res = new Key(grid);
				break;
			case 'p':
				if(str.length() == 1)
					res = new Portal(grid);
				else
					res = new Portal(grid, Color.fromChar(str.charAt(1)));
				break;
			case 'f':
				res = new FragileFloor(grid);
				break;
			case 'h': // 'h' for hole in the original sokoban
				res = new Fire(grid);
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
	 * Returns the path of the image of the cell.
	 *
	 * @return the path of the image of the cell
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Returns the number of versions for this cell.
	 *
	 * @return the number of versions
	 */
	public int getNumberOfVersions() {
		return 1;
	}

	/**
	 * Returns the id of the current version of the image of the cell.
	 *
	 * @return the id of the current version of the image of the cell
	 */
	public int getVersion() {
		return version;
	}


	// === Setters ===

	/**
	 * Sets the occupant of the cell.
	 *
	 * @param occupant the new occupant of the cell
	 */
	public void setOccupant(Entity occupant) {
		this.occupant = occupant;
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
			e.getCell().setOccupant(null);
			occupant = e;
			e.setCell(this);
			return true;
		}
		else if(e instanceof Box)
			return false;
		else {
			Cell newCell = grid.getCell(this, dir);
			if(newCell.enter(occupant, dir)) {
				e.setDirection(dir);
				e.getCell().setOccupant(null);
				occupant = e;
				e.setCell(this);
				return true;
			}
			return false;
		}
	}

	/**
	 * Returns true if the entity can exit the cell in the specified direction, false otherwise.
	 *
	 * @param isPlayer true if the entity is a player, false if it's an object
	 * @param dir the direction in which the entity wants to exit
	 * @return true if the entity can exit, false otherwise
	 */
	public boolean canExit(boolean isPlayer, Direction dir) {
		return true;
	}

	/**
	 * Returns true if the entity can exit the cell in the specified direction, false otherwise.
	 *
	 * @param dir the direction in which the entity wants to exit
	 * @return true if the entity can exit, false otherwise
	 */
	public boolean canExit(Direction dir) {
		return canExit(false, dir);
	}
}