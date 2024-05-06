package main.model.grid.cell;

import main.model.grid.Direction;
import main.model.grid.GridGame;
import main.model.entity.Entity;


public class Wall extends Cell {
	public static final Object imagePath = "wall.jpg";
	
	public Wall(GridGame grid) {
		super(grid);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean enter(Entity e, Direction dir) {
		return false;
	}

	// @Override
	// public boolean leave(Entity e) {
	// 	System.out.println("Comment ça mon reuf ?");
	// 	return true;
	// }
}
