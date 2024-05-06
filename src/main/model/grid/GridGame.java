package main.model.grid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import main.model.grid.Direction;
import main.model.entity.Entity;
import main.model.grid.Point;
import main.model.entity.Player;
import main.model.grid.cell.Cell;


public class GridGame {

	private Player p;
	private Cell [][] tab;
	private Map<Cell, Point> allPoint; // Same thing as tab, so it needs to be updated at the same time

	public GridGame(String filename) throws FileNotFoundException, IllegalArgumentException {
		int width, height;

		try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
			String line;
			String[] values;

			// Get the width and height of the grid
			line = br.readLine();
			values = line.split(" ");
			width = Integer.parseInt(values[0]);
			height = Integer.parseInt(values[1]);
			if(width <= 0 || height <= 0)
				throw new IllegalArgumentException("Width and height must be positive.");

			// Create the grid
			tab = new Cell[height][width];
			allPoint = new HashMap<>();
			for(int i = 0; i < height; i++) {
				line = br.readLine();
				values = line.split(" ");
				for(int j = 0; j < width; j++) {
					tab[i][j] = Cell.loadCell(this, values[j].charAt(0));
					allPoint.put(tab[i][j], new Point(i, j));
				}
			}

			// Create the entities
			for(int i = 0; i < height; i++) {
				line = br.readLine();
				values = line.split(" ");
				for(int j = 0; j < width; j++) {
					Entity e = Entity.loadEntity(values[j].charAt(0));
					if(e != null) {
						if(e instanceof Player)
							p = (Player) e;
						tab[i][j].enter(e, Direction.LEFT);
					}
				}
			}
		}
		catch (FileNotFoundException e) {
			throw new FileNotFoundException("File not found.");
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Error while reading the file.");
		}
		catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}


	// === Getters ===

	public Player getPlayer() {
		return p;
	}

	public Cell getCell(Point p) {
		return tab[p.x][p.y];
	}

	public Cell getCell(Cell cell, Direction dir) {
		Point cellCoordinates = allPoint.get(cell);
		switch(dir) {
			case UP:
				return tab[cellCoordinates.x][cellCoordinates.y - 1];
			case DOWN:
				return tab[cellCoordinates.x][cellCoordinates.y + 1];
			case LEFT:
				return tab[cellCoordinates.x - 1][cellCoordinates.y];
			case RIGHT:
				return tab[cellCoordinates.x + 1][cellCoordinates.y];
			default:
				return null;
		}
	}


	// === Methods ===

	public void move(Entity entity, Direction dir) {
		// TODO Auto-generated method stub
		
	}

	public Point getPosition(Entity e) {
		for(int i = 0; i < tab.length; i++) {
			for(int j = 0; j < tab[0].length; j++) {
				if(tab[i][j].getOccupant() != null && tab[i][j].getOccupant().equals(e)) {
					return new Point(i,j);
				}
			}
		}
		return null;
	}

	// public void seDeplacer(Entity e, Direction dir) {
	// 	Point pe = getPosition(e);
	// 	Cell cible = getCible(pe,dir);
	// 	cible.enter(e, dir);
	// 	//setChange();
	// 	//notifyObserver(...);
	// }
	
	// public void moveHero(Direction d) {
	// 	Cell cCible = getCible(getPosition(p),d);
	// 	p.moveTo(cCible, d);
	// 	//setChange();
	// 	//notifyObserver(...);
	// }
}
