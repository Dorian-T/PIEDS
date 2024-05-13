package main.model.entity;

import java.util.Observable;

import main.model.grid.Direction;
import main.model.grid.cell.Cell;

/**
 * Represents an entity in the game.
 * An entity is an object that can move in the grid.
 *
 * @see Observable
 */
public abstract class Entity extends Observable{

	// === Variables ===

	/**
	 * The cell where the entity is.
	 */
	protected Cell cell;

	/**
	 * The direction of the entity.
	 */
	protected Direction dir;

	/**
	 * The path of the image of a default entity.
	 */
	public String imagePath;


	// === Constructors ===

	/**
	 * Constructor of the class Entity
	 *
	 * @param cell
	 */
	protected Entity(Cell cell) {
		this.cell = cell;
		dir = Direction.LEFT;
	}

	/**
	 * Load an entity from a character.
	 *
	 * @param c the character representing the entity
	 * @param cell the cell where the entity is
	 * @return the entity corresponding to the character
	 */
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


	// === Getters ===

	/**
	 * Returns the cell where the entity is.
	 *
	 * @return the cell where the entity is
	 */
	public Cell getCell() {
		return cell;
	}


	// === Setters ===

	/**
	 * Set the direction of the entity.
	 *
	 * @param dir
	 */
	public void setDirection(Direction dir) {
		this.dir = dir;
	}


	// === Methods ===

	/**
	 * Move the entity in the grid.
	 * The entity ask the cell to move it.
	 *
	 * @param dir
	 * @return true if the entity can move, false otherwise
	 */
	public boolean moveTo(Direction dir) { // est-ce vraiment utile pour toutes les entités ?
		Cell newCell = cell.getCell(cell, dir);
		if(newCell.enter(this, dir)) {
			cell = newCell;
			return true;
		}
		return false;
	}
}
