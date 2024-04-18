package main.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class GridGame {

	private Player p;
	private Cell [][] tab;
	private Map<Cell, Point> allPoint; //Same thing as tab, so it needs to be updated at the same time
	
	public GridGame(int x, int y, File levelCells, File levelEntities) throws IOException {
		tab = new Cell[y][x];
		try {
			BufferedReader brC = new BufferedReader(new FileReader(levelCells));
			BufferedReader brE = new BufferedReader(new FileReader(levelEntities));
			String lineC, lineE;
            int row = 0;
            while ((lineC = brC.readLine()) != null && (lineE = brE.readLine()) != null && row < y) {
                String[] valuesC = lineC.split(" ");
                String[] valuesE = lineE.split(" ");
                for (int col = 0; col < tab[row].length && col < x; col++) {
                	if(valuesC[col] != ".") {
                		tab[row][col] = Cell.loadCell(valuesC[col].charAt(0), row, col);
                	}
                	if(valuesE[col] != ".") {
                		tab[row][col].enter(Entity.loadEntity(valuesE[col].charAt(0)));
                	}
                	
                }
                row++;
            }
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void move(Entity entity, Direction dir) {
		// TODO Auto-generated method stub
		
	}
	
	public Point getPosition(Entity e) {
		return null;
	}
	
	public void seDeplacer(Entity e, Direction dir) {
		Point pe = getPosition(e);
		Cell cible = getCible(pe,dir);
		cible.enter(e);
		//setChange();
		//notifyObserver(...);
	}
	
	public void moveHero(Direction d) {
		Cell cCible = getCible(heros,d);
		h.moveTo(cCible, d);
	}
	
	public Cell getCible( Point pe, Direction dir) {
		return null;
	}

}
