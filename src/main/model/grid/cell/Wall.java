package main.model.grid.cell;

import main.model.grid.Direction;
import main.model.grid.GridGame;
import main.model.entity.Entity;


/**
 * Represents a wall in the game.
 * The wall is a cell that cannot be entered by any entity.
 *
 * @see Cell
 */
public class Wall extends Cell {

	// === Constructors ===

	/**
	 * Constructor of the class Wall
	 *
	 * @param grid the grid where the wall is
	 */
	public Wall(GridGame grid) {
		super(grid);
		imagePath = "wall.png";
	}


	// === Methods ===

	/**
	 * Returns false because a wall cannot be entered.
	 *
	 * @param e the entity that wants to enter the cell
	 * @param dir the direction from which the entity wants to enter the cell
	 * @return false
	 */
	@Override
	public boolean enter(Entity e, Direction dir) {
		return false;
	}
}
