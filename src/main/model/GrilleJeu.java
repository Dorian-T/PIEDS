package main.model;

import java.util.Map;

public class GrilleJeu {

	private Player p;
	private Case [][] tab;
	private Map<Case, Point> allPoint; //meme chose que tab donc doit etre MAJ en meme temps
	
	public void move(Entity entity, Direction dir) {
		// TODO Auto-generated method stub
		
	}
	
	public void getPosition(Entity e) {
		
	}
	
	public void seDeplacer(Entity e, Direction dir) {
		Point pe = getPosition(e);
		Case cible = getCible(pe,dir);
		cible.entrer(e);
		//setChange();
		//notifyObserver(...);
	}
	
	public Case getCible( Point pe, Direction dir) {
		return new Case;
	}

}
