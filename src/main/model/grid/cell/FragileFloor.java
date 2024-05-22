package main.model.grid.cell;

import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;


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

	@Override
	public boolean enter(Entity e, Direction dir) {
		if(super.enter(e, dir)) {
			grid.setCell(this, new Fire(grid), e);
			return true;
		}
		return false;
	}
}
