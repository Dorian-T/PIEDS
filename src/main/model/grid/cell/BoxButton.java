package main.model.grid.cell;

import main.model.Color;
import main.model.entity.Box;
import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;


/**
 * Represents a button that can be activated by a box.
 * When a box is on the button, the button is activated.
 * 
 * @see Cell
 */
public class BoxButton extends Cell {

	// === Variables ===

	/**
	 * The color of the button
	 */
	Color color;

	/**
	 * True if the button is activated, false otherwise
	 */
	boolean activated;

	
	// === Constructors ===

	/**
	 * Constructor of the class BoxButton
	 *
	 * @param grid
	 * @param color
	 */
	public BoxButton(GridGame grid, Color color) {
		super(grid);
		this.color = color;
		activated = false;
		imagePath = "flag.png";
	}

	/**
	 * Constructor of the class BoxButton
	 *
	 * @param grid
	 */
	public BoxButton(GridGame grid) {
		this(grid, Color.RED);
	}


	// === Getters ===

	/**
	 * Returns the color of the button.
	 *
	 * @return the color of the button
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Returns the path of the image of the button.
	 *
	 * @return the path of the image of the button
	 */
	@Override
	public String getImagePath() {
		return color + "-" + imagePath;
	}


	// === Methods ===

	/**
	 * Returns true if the button is activated, false otherwise.
	 * The button is activated if a box of the same color is on it.
	 *
	 * @return true if the button is activated, false otherwise
	 */
	public boolean isActivated() {
		return occupant instanceof Box && ((Box) occupant).getColor() == color;
	}
}
