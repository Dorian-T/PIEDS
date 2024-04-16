package main.model;

import java.util.Map;

public class GridGame {

	private Player p;
	private Cell [][] tab;
	private Map<Cell, Point> allPoint; //Same thing as tab, so it needs to be updated at the same time
	
	public void move(Entity entity, Direction dir) {
		// TODO Auto-generated method stub
		
	}
	
	public Point getPosition(Entity e) {
		return null;
	}
	
	public void seDeplacer(Entity e, Direction dir) {
		Point pe = getPosition(e);
		Cell cible = getCible(pe,dir);
		cible.entrer(e);
		//setChange();
		//notifyObserver(...);
	}
	
	public Cell getCible( Point pe, Direction dir) {
		return null;
	}

}
