package main.model;

import java.awt.Frame;

public class Case {
	int x;
	int y;
	
	Entity occupant;

	public Case(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void addObserver(Frame f) {
		// TODO Auto-generated method stub
		
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
	}

	public void entrer(Entity e) {
		// TODO Auto-generated method stub
		
	}
	
	public void quitter(Entity e) {
		// TODO Auto-generated method stub
		
	}
}
