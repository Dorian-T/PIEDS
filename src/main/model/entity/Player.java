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

	int indexImage = 0;

	public Player(Cell cell) {
		super(cell);
		imagePath = "player_right0.png";
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
			cell = newCell;
			setChanged();
			notifyObservers();
			switch (dir) {
			case LEFT:
				indexImage++;
				imagePath = "player_left" + indexImage % 2 + ".png";
				System.out.println("player mooved");
				break;
			case RIGHT:
				indexImage++;
				imagePath = "player_right" + indexImage % 2 + ".png";
				System.out.println("player mooved");
				break;
			default:
				break;
			}
			return true;
		}

		return false;
	}
}
