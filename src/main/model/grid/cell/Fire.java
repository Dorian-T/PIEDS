package main.model.grid.cell;

import main.model.entity.Entity;
import main.model.entity.Player;
import main.model.grid.Direction;
import main.model.grid.GridGame;


public class Fire extends Cell {
	
	// === Constructors ===
	
	/**
	 * Constructor of the class Fire
	 *
	 * @param grid
	 */
	public Fire(GridGame grid) {
		super(grid);
		imagePath = "fire.png";
	}


	// === Methods ===

	@Override
	public boolean enter(Entity e, Direction dir) {
		if(super.enter(e, dir)) {
			if(e instanceof Player)
				grid.gameOver();
			else {
				grid.setCell(this, new Ground(grid), e);
				grid.removeEntity(e);
			}
			return true;
		}
		return false;
	}
}
