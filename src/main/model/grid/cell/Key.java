package main.model.grid.cell;

import java.util.Observable;

import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;


public class Key extends Cell {
	
	// === Variables ===

	/**
	 * True if the key has been picked up, false otherwise
	 */
	private boolean pickedUp;
	
	// === Constructors ===

	/**
	 * Constructor of the class Key
	 *
	 * @param cell the cell where the key is
	 */
	public Key(GridGame gg) {
		super(gg);
		pickedUp = false;
		imagePath = "key.png";
		version = 0;
	}


	// === Getters ===

	/**
	 * Returns the id of the curent version of the image of the key.
	 *
	 * @return 1 if the key has been picked up, 0 otherwise
	 */
	@Override
	public int getVersion() {
		return pickedUp ? 1 : 0;
	}

	/**
	 * Returns true if the key has been picked up, false otherwise.
	 * The key is picked up if there is an entity in the cell.
	 *
	 * @return true if the key has been picked up, false otherwise
	 */
	public boolean isPickedUp() {
		return occupant != null;
	}
}