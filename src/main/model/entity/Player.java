package main.model.entity;

import main.model.grid.cell.Cell;
import main.model.grid.Direction;

/**
 * Represents the player in the game. The player is the entity that can be
 * controlled by the user.
 *
 * @see Entity
 */
public class Player extends Entity {

	// === Constructors ===

	/**
	 * Constructor of the class Player
	 *
	 * @param cell the cell where the player is
	 */
	public Player(Cell cell) {
		super(cell);
		imagePath = "baba.png";
		version = 3;
	}


	// === Getters ===

	@Override
	public int getVersion() {
		switch(direction) {
			case UP:
				return 0;
			case RIGHT:
				return 1;
			case DOWN:
				return 2;
			case LEFT:
				return 3;
			default:
				return 2;
		}
	}


	// === Methods ===

	/**
	 * Moves the player to the specified direction. If the player can move to the
	 * new cell, the player is moved and the observers are notified.
	 * 
	 * @param dir the direction to move the player to
	 * @return true if the player successfully moved to the new cell, false
	 *         otherwise
	 */
	@Override
	public boolean moveTo(Direction dir) {
		Cell newCell = cell.getCell(cell, dir);
		if (newCell.enter(this, dir)) {
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}
}
