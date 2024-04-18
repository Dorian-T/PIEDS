package main.model.grid.cell;

import main.model.grid.Direction;
import main.model.entity.Entity;


public class Wall extends Cell {

	public Wall(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean enter(Entity e, Direction dir) {
		return false;
	}
	
	@Override
	public boolean quit(Entity e) {
		System.out.println("Comment ça mon reuf ?");
		return true;
	}
	
}
