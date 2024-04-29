package main.model.grid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import main.model.grid.Direction;
import main.model.entity.Entity;
import main.model.grid.Point;
import main.model.entity.Player;
import main.model.grid.cell.Cell;


public class GridGame {

	private Player p;
	private Cell [][] tab;
	private Map<Cell, Point> allPoint; //Same thing as tab, so it needs to be updated at the same time
	private Player heros;
	
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
                		tab[row][col].enter(Entity.loadEntity(valuesE[col].charAt(0)), Direction.RIGHT);
                	}
                	
                }
                row++;
            }
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GridGame(File file) {
		int width, height;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			String[] values;

			// Get the width and height of the grid
			line = br.readLine();
			values = line.split(" ");
			width = Integer.parseInt(values[0]);
			height = Integer.parseInt(values[1]);
			if(width <= 0 || height <= 0)
				throw new IllegalArgumentException("Width and height must be positive.");

			// Create the grid and the entities
			tab = new Cell[height][width];
			// allPoint = new Map<>(); // TODO: Initialize allPoint
			for(int i = 0; i < height; i++) {
				line = br.readLine();
				values = line.split(" ");
				for(int j = 0; j < width; j++) {
					tab[i][j] = Cell.loadCell(values[j].charAt(0), i, j);
					tab[i][j].enter(Entity.loadEntity(values[j].charAt(1)), Direction.LEFT);
					// allPoint.put(tab[i][j], new Point(i, j)); // TODO: Update allPoint
				}
			}
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error while reading the file.");
		}
		catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
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
		cible.enter(e, dir);
		//setChange();
		//notifyObserver(...);
	}
	
	public void moveHero(Direction d) {
		Cell cCible = getCible(getPosition(heros),d);
		heros.moveTo(cCible, d);
		//setChange();
		//notifyObserver(...);
	}
	
	public Cell getCible( Point pe, Direction dir) {
		return null;
	}

}
