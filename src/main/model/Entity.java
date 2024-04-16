package main.model;

public abstract class Entity {
	GridGame gj;
	public void move(Direction dir) {
		gj.move(this, dir);
	}
}
