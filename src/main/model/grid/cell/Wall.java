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

	// === Variables ===



	// === Constructors ===

	/**
	 * Constructor of the class Wall
	 *
	 * @param grid
	 */
	public Wall(GridGame grid) {
		super(grid);
		imagePath = "wall.png";
		// TODO Auto-generated constructor stub
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
