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
public class Ground extends Cell {

	// === Variables ===



	// === Constructors ===

	/**
	 * Constructor of the class Wall
	 *
	 * @param grid
	 */
	public Ground(GridGame grid) {
		super(grid);
		imagePath = "ground.png";
		// TODO Auto-generated constructor stub
	}

}
