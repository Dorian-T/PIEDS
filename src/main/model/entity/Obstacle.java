package main.model.entity;

import main.model.grid.cell.Cell;


/**
 * Represents a movable obstacle in the game.
 * The obstacle is an entity that can be pushed by the player.
 * 
 * @see Entity
 */
public class Obstacle extends Entity { // TODO: parfois les cases disparaissent
	
	// === Constructors ===

	/**
	 * Constructor of the class Obstacle
	 *
	 * @param cell the cell where the obstacle is
	 */
	public Obstacle(Cell cell) {
		super(cell);
		imagePath = "obstacle.png";
	}
}
