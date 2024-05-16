package main.model.grid.cell;

import java.util.ArrayList;
import java.util.List;

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

	boolean activated; // True if the button is activated, false otherwise

	
	// === Constructors ===

	/**
	 * Constructor of the class Lever
	 *
	 * @param grid
	 */
	public BoxButton(GridGame grid) {
		super(grid);
		imagePath = "lever.png";
		activated = false;
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

	@Override
	public boolean enter(Entity e, Direction dir) {
		if (super.enter(e, dir)) {
			activated = e instanceof Box; // TODO: ajouter la vérification de la couleur de la box
			return true;
		}
		else
			return false;			
	}
}
