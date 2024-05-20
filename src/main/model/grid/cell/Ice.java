package main.model.grid.cell;

import main.model.entity.Box;
import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;


public class Ice extends Cell {
	
	// === Constructors ===

	/**
	 * Constructor of the class Ice
	 *
	 * @param grid
	 */
	public Ice(GridGame grid) {
		super(grid);
		imagePath = "ice.png";
	}


	// === Methods ===

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
	// public boolean enter(Entity e, Direction dir) {
	// 	if(occupant == null) {
	// 		e.setDirection(dir);
	// 		e.getCell().setOccupant(null);
	// 		occupant = e;
	// 		return true;
	// 	}
	// 	else if(e instanceof Box)
	// 		return false;
	// 	else {
	// 		Cell newCell = grid.getCell(this, dir);
	// 		if(newCell.enter(occupant, dir)) {
	// 			e.setDirection(dir);
	// 			e.getCell().setOccupant(null);
	// 			occupant = e;
	// 			return true;
	// 		}
	// 		return false;
	// 	}
	// }
}
