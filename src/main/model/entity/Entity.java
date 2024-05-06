package main.model.entity;

import main.model.grid.cell.Cell;
//import main.model.Box;
//import main.model.Entity;
//import main.model.Player;
import main.model.grid.Direction;
import main.model.grid.GridGame;
import main.model.grid.Point;

public abstract class Entity {
	GridGame gj;
	Direction dir;
	public static final Object imagePath = "weirdEntity.png";

	/*public void move(Direction dir) {
		gj.move(this, dir);
	}*/

	public void setDirection(Direction dir) {
		this.dir = dir;
	}

	public boolean moveTo(Point coordinates, Direction dir, Cell oldCell) {
		//oldCell.enter(this, dir);
		if(gj.getCell(coordinates.x, coordinates.y).enter(this, dir)) {
			oldCell.leave();
			
			return true;
		}
		return false;
	}
	
	public boolean moveTo(Direction dir, Cell oldCell) {
		
		if(gj.getCible(new Point(oldCell.getX(),oldCell.getY()), dir).enter(this, dir)) {
			oldCell.leave();
			return true;
		}
		return false;
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
			case '.': // empty cell
				break;
			default:
				System.out.println("Erreur de caractere impossible");
				break;
		}
		return res;
	}
}
