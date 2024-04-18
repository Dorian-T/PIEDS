package main.model.entity;

import main.model.grid.cell.Cell;
//import main.model.Box;
//import main.model.Entity;
//import main.model.Player;
import main.model.grid.Direction;
import main.model.grid.GridGame;

public abstract class Entity {
	GridGame gj;
	
	/*public void move(Direction dir) {
		gj.move(this, dir);
	}*/
	
	public void moveTo(Cell c , Direction dir) {
		Entity e = c.getOccupant();
		if(e != null) {
			//e.push();
		}
		c.enter(this, dir);
	}
	
	public static Entity loadEntity(char c) {
		Entity res = null;
		switch(c) {
			case 'p':
				res = new Player();
				break;
			case 'b':
				res = new Box();
				break;
			default:
				System.out.println("Erreur de charactere impossible");
				break;
		}
		return res;
	}
}
