package main.model.entity;

import main.model.grid.cell.Cell;
import main.model.grid.Direction;


/**
 * Represents the player in the game.
 * The player is the entity that can be controlled by the user.
 *
 * @see Entity
 */
public class Player extends Entity {
	
	// === Variables ===

	/**
	 * The path of the image of the player.
	 */
	public static final Object imagePath = "player.png";


	// === Constructors ===

	/**
	 * Constructor of the class Player
	 *
	 * @param cell the cell where the player is
	 */
	public Player(Cell cell) {
		super(cell);
	}


	// === Methods ===

	/**
	 * Moves the player to the specified direction.
	 * If the player can move to the new cell, the player is moved and the observers are notified.
	 * 
	 * @param dir the direction to move the player to
	 * @return true if the player successfully moved to the new cell, false otherwise
	 */
	@Override
	public boolean moveTo(Direction dir) {
		Cell newCell = cell.getCell(cell, dir);
		if(newCell.enter(this, dir)) {
			cell = newCell;
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}
}
