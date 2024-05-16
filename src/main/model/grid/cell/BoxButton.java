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
		imagePath = "BoxButton.png";
	}

	/**
	 * Constructor of the class BoxButton
	 *
	 * @param grid
	 */
	public BoxButton(GridGame grid) {
		this(grid, Color.RED);
	}

	// === Methods ===

	/**
	 * Returns true if the button is activated, false otherwise.
	 *
	 * @return true if the button is activated, false otherwise
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * Enters an entity into the box button cell from a specified direction.
	 * If the entity can enter the cell, it checks if it is a Box and if its color matches the button's color.
	 * If the conditions are met, the button is activated.
	 * 
	 * @param e   the entity to enter the cell
	 * @param dir the direction from which the entity is entering
	 * @return true if the entity successfully enters the cell, false otherwise
	 */
	@Override
	public boolean enter(Entity e, Direction dir) {
		if (super.enter(e, dir)) {
			activated = (e instanceof Box && ((Box) e).getColor() == color);
			return true;
		}
		else
			return false;			
	}
}
