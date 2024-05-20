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

	public void setOpen(boolean bool) {
		open = bool;
	}


	// === Methods ===

	@Override
	public boolean enter(Entity entity, Direction direction) {
		if(open)
			return super.enter(entity, direction);
		return false;
	}
}
