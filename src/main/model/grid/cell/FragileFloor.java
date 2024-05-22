package main.model.grid.cell;

import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;


/**
 * Represents a cell of the grid that is fragile.
 * Fragile floor cells are destroyed when an entity enters them and are replaced with fire cells.
 * 
 * @see Cell
 */
public class FragileFloor extends Cell {

	// === Constructors ===

	/**
	 * Constructor of the class FragileFloor
	 *
	 * @param grid
	 */
	public FragileFloor(GridGame grid) {
		super(grid);
		imagePath = "foliage.png";
	}


	// === Methods ===

	/**
	 * Allows an entity to enter the fragile floor cell from a specified direction.
	 * If the entity successfully enters the cell, the cell is replaced with a fire cell.
	 * 
	 * @param e The entity attempting to enter the cell.
	 * @param dir The direction from which the entity is entering the cell.
	 * @return true if the entity successfully enters the cell, false otherwise.
	 */
	@Override
	public boolean enter(Entity e, Direction dir) {
		if(super.enter(e, dir)) {
			grid.setCell(this, new Fire(grid), e);
			return true;
		}
		return false;
	}
}
