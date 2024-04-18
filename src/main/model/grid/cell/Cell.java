package main.model.grid.cell;

import java.util.Observable;

import main.model.entity.Entity;
import main.model.grid.Direction;


public class Cell extends Observable{
	int x;
	int y;
	
	Entity occupant;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void move(Direction dir) {
		if(dir == Direction.LEFT) { //tester si c'est possible ?
			x--; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
		}else if(dir == Direction.RIGHT) { //tester si c'est possible ?
			x++; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
		}else if(dir == Direction.UP) { //tester si c'est possible ?
			y--; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
		}else if(dir == Direction.DOWN) { //tester si c'est possible ?
			y++; //prévoir de changer la distance a laquelle elle se déplace ? (au lieu de juste 1 par 1 px)
		}
		setChanged();
		notifyObservers();
	}

	public boolean enter(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	public void quit(Entity e) {
		// TODO Auto-generated method stub
		
	}

	public void begin() {
		setChanged();
		notifyObservers();
	}
}