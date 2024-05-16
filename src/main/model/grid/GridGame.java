package main.model.grid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.entity.*;
import main.model.grid.cell.*;


public class GridGame {

	private Player p;
	private Cell[][] tab;
	private Map<Cell, Point> allPoint; // Same thing as tab, so it needs to be updated at the same time
	private int height;
	private int width;
	private List<BoxButton> boxButtons; // TODO: test if the buttons are activated

	public GridGame(String filename) throws FileNotFoundException, IllegalArgumentException {
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
			createGrid(br);

			// Create the entities
			createEntities(br);
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

	/**
	 * Creates a grid based on the provided BufferedReader, width, and height.
	 * Each line from the BufferedReader represents a row in the grid, and each value in the line represents a cell in the row.
	 * The grid is stored in the 'tab' array, and the mapping of each cell to its corresponding Point is stored in the 'allPoint' HashMap.
	 *
	 * @param br     the BufferedReader used to read the grid data
	 * @param width  the width of the grid
	 * @param height the height of the grid
	 * @throws IOException if an I/O error occurs while reading the grid data
	 */
	private void createGrid(BufferedReader br) throws IOException {
		String line;
		String[] values;

		tab = new Cell[height][width];
		allPoint = new HashMap<>();
		boxButtons = new ArrayList<>();

		for(int y = 0; y < height; y++) {
			line = br.readLine();
			values = line.split(" ");
			for(int x = 0; x < width; x++) {
				Cell c = Cell.loadCell(this, values[x].charAt(0));

				// BoxButton
				if(c instanceof BoxButton)
					boxButtons.add((BoxButton) c);

				tab[y][x] = c;
				allPoint.put(tab[y][x], new Point(x, y));
			}
		}
	}

	/**
	 * Creates the entities based on the provided BufferedReader, width, and height.
	 * Each line from the BufferedReader represents a row in the grid, and each value in the line represents a cell in the row.
	 * The entities are stored in the corresponding cells in the 'tab' array.
	 *
	 * @param br     the BufferedReader used to read the entities data
	 * @param width  the width of the grid
	 * @param height the height of the grid
	 * @throws IOException if an I/O error occurs while reading the entities data
	 */
	private void createEntities(BufferedReader br) throws IOException {
		String line;
		String[] values;

		for(int i = 0; i < height; i++) {
			line = br.readLine();
			values = line.split(" ");
			for(int j = 0; j < width; j++) {
				Entity e = Entity.loadEntity(values[j].charAt(0), tab[i][j]);
				if(e != null) {
					if(e instanceof Player)
						p = (Player) e;
					tab[i][j].enter(e, Direction.LEFT);
				}
			}
		}
	}


	// === Getters ===

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Player getPlayer() {
		return p;
	}

	public Cell getCell(Point p) {
		return tab[p.y][p.x];
	}

	public Cell getCell(Cell cell, Direction dir) {
		Point cellCoordinates = allPoint.get(cell);
		switch(dir) {
			case UP:
				return tab[cellCoordinates.y - 1][cellCoordinates.x];
			case DOWN:
				return tab[cellCoordinates.y + 1][cellCoordinates.x];
			case LEFT:
				return tab[cellCoordinates.y][cellCoordinates.x - 1];
			case RIGHT:
				return tab[cellCoordinates.y][cellCoordinates.x + 1];
			default:
				return null;
		}
	}


	// === Methods ===

	public void move(Entity entity, Direction dir) {
		// TODO Auto-generated method stub
	}

	public Point getPosition(Entity e) {
		for(int y = 0; y < tab.length; y++) {
			for(int x = 0; x < tab[0].length; x++) {
				if(tab[y][x].getOccupant() != null && tab[y][x].getOccupant().equals(e)) {
					return new Point(x,y);
				}
			}
		}
		return null;
	}

	public boolean isWin() {
		for(BoxButton bb : boxButtons)
			if(!bb.isActivated())
				return false;
		return true;
	}

	// public void seDeplacer(Entity e, Direction dir) {
	// 	Point pe = getPosition(e);
	// 	Cell cible = getCible(pe,dir);
	// 	cible.enter(e, dir);
	// 	//setChanged();
	// 	//notifyObservers(...);
	// }

	// public void moveHero(Direction d) {
	// 	Cell cCible = getCible(getPosition(p),d);
	// 	p.moveTo(cCible, d);
	// 	//setChanged();
	// 	//notifyObservers(...);
	// }
}
