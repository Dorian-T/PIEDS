package main.model;

public abstract class Entity {
	GrilleJeu gj;
	public void move(Direction dir) {
		gj.move(this, dir);
	}
}
