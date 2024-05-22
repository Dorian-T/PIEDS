package main.model.grid.cell;

import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;


/**
 * Represents a cell of the grid that is ice.
 * Ice cells allow entities to slide on them.
 * 
 * @see Cell
 */
public class Ice extends Cell {
	
	// === Constructors ===

	/**
	 * Constructor of the class Ice
	 *
	 * @param grid the grid where the ice cell is
	 */
	public Ice(GridGame grid) {
		super(grid);
		imagePath = "ice.png";
	}


	// === Methods ===

	/**
	 * Allows an entity to enter the ice cell from a specified direction.
	 * If the cell is unoccupied, the entity can enter the next cell in the specified direction.
	 * If the next cell is also unoccupied, the entity can continue moving in that direction.
	 * If the next cell is occupied, the entity cannot move and stays on the ice cell.
	 *
	 * @param e   the entity trying to enter the cell
	 * @param dir the direction from which the entity is trying to enter
	 * @return true if the entity successfully enters the cell or continues moving, false otherwise
	 */
	@Override
	public boolean enter(Entity e, Direction dir) {
		if(occupant == null) {
			Cell nextCell = grid.getCell(this, dir);
			if(nextCell.getOccupant() == null)
				return nextCell.enter(e, dir);
			else
				return super.enter(e, dir);
		}
		return false;
	}
}
