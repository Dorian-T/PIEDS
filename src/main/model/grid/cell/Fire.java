package main.model.grid.cell;

import main.model.entity.Entity;
import main.model.entity.Player;
import main.model.grid.Direction;
import main.model.grid.GridGame;


/**
 * Represents a cell of the grid that is on fire.
 * Fire cells are dangerous and can kill entities that enter them.
 * 
 * @see Cell
 */
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

	/**
	 * Allows an entity to enter the fire cell.
	 * If the entity is a player, the game is over.
	 * If the entity is not a player, the fire cell is replaced with a ground cell and the entity is removed from the grid.
	 * 
	 * @param e The entity trying to enter the fire cell.
	 * @param dir The direction from which the entity is entering the fire cell.
	 * @return True if the entity successfully enters the fire cell, false otherwise.
	 */
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
