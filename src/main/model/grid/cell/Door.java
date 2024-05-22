package main.model.grid.cell;

import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;


/**
 * Represents a door in the game.
 * A door is a cell that can be opened by pushing a button.
 *
 * @see Cell
 */
public class Door extends Cell {

	// === Variables ===

	/**
	 * True if the door is open, false otherwise
	 */
	boolean open;


	// === Constructors ===

	/**
	 * Constructor of the class Door
	 *
	 * @param grid
	 */
	public Door(GridGame grid) {
		super(grid);
		open = false;
		imagePath = "door.png";
		version = 0;
	}


	// === Getters ===

	/**
	 * Returns the number of versions for this door.
	 *
	 * @return the number of versions
	 */
	@Override
	public int getNumberOfVersions() {
		return 2;
	}

	/**
	 * Returns the id of the curent version of the image of the door.
	 *
	 * @return 1 if the door is open, 0 otherwise
	 */
	@Override
	public int getVersion() {
		return open ? 1 : 0;
	}

	/**
	 * Returns true if the door is open, false otherwise.
	 *
	 * @return true if the door is open, false otherwise
	 */
	public boolean isOpen() {
		return open;
	}


	// === Setters ===

	/**
	 * Sets the open state of the door.
	 *
	 * @param bool the boolean value indicating whether the door should be open or not
	 */
	public void setOpen(boolean bool) {
		open = bool;
	}


	// === Methods ===

	/**
	 * Allows an entity to enter the door from a specific direction.
	 * 
	 * @param entity the entity trying to enter the door
	 * @param direction the direction from which the entity is trying to enter
	 * @return true if the door is open and the entity is allowed to enter, false otherwise
	 */
	@Override
	public boolean enter(Entity entity, Direction direction) {
		if(open)
			return super.enter(entity, direction);
		return false;
	}
}
