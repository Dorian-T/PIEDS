package main.model.entity;

import main.model.grid.cell.Cell;


/**
 * Represents a box in the game.
 * The box is an entity that can be pushed by the player.
 *
 * @see Entity
 */
public class Box extends Entity {

	// === Variables ===


	// === Constructors ===

	/**
	 * Constructor of the class Box
	 *
	 * @param cell the cell where the box is
	 */
	public Box(Cell cell) {
		super(cell);
		imagePath = "box.png";
	}
}
