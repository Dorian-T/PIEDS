package main.model.grid.cell;

import main.model.grid.GridGame;


/**
 * Represents a wall in the game.
 * The wall is a cell that cannot be entered by any entity.
 *
 * @see Cell
 */
public class Ground extends Cell {

	// === Constructors ===

	/**
	 * Constructor of the class Wall
	 *
	 * @param grid
	 */
	public Ground(GridGame grid) {
		super(grid);
		imagePath = null;
	}

}
