package main.model.entity;

import main.model.Color;
import main.model.grid.cell.Cell;


/**
 * Represents a box in the game.
 * The box is an entity that can be pushed by the player.
 *
 * @see Entity
 */
public class Box extends Entity {

	// === Variables ===

	Color color;


	// === Constructors ===

	/**
	 * Constructor of the class Box
	 *
	 * @param cell the cell where the box is
	 * @param color the color of the box
	 */
	public Box(Cell cell, Color color) {
		super(cell);
		this.color = color;
		imagePath = "box.png";
	}

	/**
	 * Constructor of the class Box
	 *
	 * @param cell the cell where the box is
	 */
	public Box(Cell cell) {
		this(cell, Color.RED);
	}


	// === Getters ===

	/**
	 * Returns the color of the box.
	 *
	 * @return the color of the box
	 */
	public Color getColor() {
		return color;
	}
}
