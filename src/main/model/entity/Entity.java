package main.model.entity;

import java.util.Observable;

import main.model.Color;
import main.model.grid.Direction;
import main.model.grid.cell.Cell;


/**
 * Represents an entity in the game.
 * An entity is an object that can move in the grid.
 *
 * @see Observable
 */
public abstract class Entity extends Observable {

	// === Variables ===

	/**
	 * The cell where the entity is.
	 */
	protected Cell cell;

	/**
	 * The direction of the entity.
	 */
	protected Direction direction;

	/**
	 * The path of the image of a the entity.
	 */
	protected String imagePath;


	// === Constructors ===

	/**
	 * Constructor of the class Entity
	 *
	 * @param cell
	 */
	protected Entity(Cell cell) {
		this.cell = cell;
		direction = Direction.LEFT;
	}

	/**
	 * Load an entity from a character.
	 *
	 * @param str the character representing the entity
	 * @param cell the cell where the entity is
	 * @return the entity corresponding to the character
	 */
	public static Entity loadEntity(String str, Cell cell) {
		Entity res = null;
		switch(str.charAt(0)) {
			case 'b':
				if(str.length() == 1)
					res = new Box(cell);
				else
					res = new Box(cell, Color.fromChar(str.charAt(1)));
				break;
			case 'p':
				res = new Player(cell);
				break;
			case '.': // empty cell
				break;
			default:
				System.out.println("Erreur de caractere impossible");
				break;
		}
		return res;
	}


	// === Getters ===

	/**
	 * Returns the cell where the entity is.
	 *
	 * @return the cell where the entity is
	 */
	public Cell getCell() {
		return cell;
	}

	/**
	 * Returns the direction of the entity.
	 *
	 * @return the direction of the entity
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Returns the path of the image of the entity.
	 *
	 * @return the path of the image of the entity
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Returns the number of versions for this entity.
	 *
	 * @return the number of versions
	 */
	public int getNumberOfVersions() {
		return 1;
	}

	/**
	 * Returns the id of the current version of the image of the entity.
	 *
	 * @return the id of the current version of the image of the entity
	 */
	public int getVersion() {
		return 0;
	}


	// === Setters ===

	/**
	 * Set the direction of the entity.
	 *
	 * @param dir
	 */
	public void setDirection(Direction dir) {
		this.direction = dir;
	}

	/**
	 * Set the cell where the entity is.
	 * 
	 * @param cell
	 */
	public void setCell(Cell cell) {
		this.cell = cell;
	}


	// === Methods ===

	/**
	 * Move the entity in the grid.
	 * The entity ask the cell to move it.
	 *
	 * @param dir
	 * @return true if the entity can move, false otherwise
	 */
	public boolean moveTo(Direction dir) {
		if(cell.canExit(dir)) {
			Cell newCell = cell.getCell(cell, dir);
			return newCell.enter(this, dir);
		}
		return false;
	}
}
