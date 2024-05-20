package main.model.grid.cell;

import main.model.Color;
import main.model.entity.Box;
import main.model.grid.GridGame;


/**
 * Represents a button that can be activated by a box.
 * When a box is on the button, the button is activated.
 * 
 * @see Cell
 */
public class Flag extends Cell {

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
	public Flag(GridGame grid, Color color) {
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
	public Flag(GridGame grid) {
		this(grid, Color.RED);
	}


	// === Getters ===

	/**
	 * Returns the number of versions for this flag.
	 *
	 * @return the number of versions
	 */
	@Override
	public int getNumberOfVersions() {
		return 6;
	}

	/**
	 * Returns the version of the flag.
	 * The version is the color of the flag.
	 *
	 * @return the version of the flag
	 */
	@Override
	public int getVersion() {
		return color.toInt();
	}

	/**
	 * Returns the color of the button.
	 *
	 * @return the color of the button
	 */
	public Color getColor() {
		return color;
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
