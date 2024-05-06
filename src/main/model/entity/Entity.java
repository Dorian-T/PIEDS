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

	public boolean moveTo(Cell cell, Direction dir) {
		return cell.enter(this, dir);
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
