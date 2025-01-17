package main.model.grid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.Color;
import main.model.entity.Entity;
import main.model.entity.Player;
import main.model.grid.cell.Cell;
import main.model.grid.cell.Door;
import main.model.grid.cell.Flag;
import main.model.grid.cell.Key;
import main.model.grid.cell.Portal;


/**
 * Represents a grid game.
 * A grid game is a game that is played on a grid of cells.
 */
public class GridGame {

	// === Variables ===

	/**
	 * Represents the filename of the level to load.
	 */
	private String levelFilename;

	/**
	 * Represents the height of the grid.
	 */
	private int height;

	/**
	 * Represents the width of the grid.
	 */
	private int width;

	/**
	 * Represents the grid of cells for the game.
	 */
	private Cell[][] tab;

	/**
	 * Represents the mapping of each cell to its corresponding Point.
	 */
	private Map<Cell, Point> allPoint;

	/**
	 * Represents the player in the game.
	 */
	private Player p;

	/**
	 * Represents the list of all keys in the game.
	 */
	private List<Key> keys;

	/**
	 * Represents the list of all flags in the game.
	 */
	private List<Flag> flags;

	/**
	 * Represents the list of all doors in the game.
	 */
	private List<Door> doors;

	/**
	 * Represents the state of the game.
	 * If the player is in a "loose" state, the game is over.
	 */
	private boolean loose;


	// === Constructors ===

	/**
	 * Creates a new GridGame object with the specified filename.
	 *
	 * @param filename the filename of the level to load
	 */
	public GridGame(String filename) {
		levelFilename = filename;
		loose = false;
		try {
			initialize(filename);
		} catch (FileNotFoundException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the grid game with the provided filename.
	 *
	 * @param filename the filename of the level to load
	 */
	private void initialize(String filename) throws FileNotFoundException, IllegalArgumentException {
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
			createCells(br);

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
	private void createCells(BufferedReader br) throws IOException {
		String line;
		String[] values;

		tab = new Cell[height][width];
		allPoint = new HashMap<>();
		flags = new ArrayList<>();
		doors = new ArrayList<>();
		keys = new ArrayList<>();

		// Temporary variables for creating the portals
		Map<Color, List<Portal>> portals = new EnumMap<>(Color.class);
		Color color;

		for(int y = 0; y < height; y++) {
			line = br.readLine();
			values = line.split(" ");
			for(int x = 0; x < width; x++) {
				Cell c = Cell.loadCell(this, values[x]);

				// Add the cell to the corresponding list
				if(c instanceof Flag)
					flags.add((Flag) c);
				if(c instanceof Door)
					doors.add((Door) c);
				if(c instanceof Key)
					keys.add((Key) c);
				if(c instanceof Portal) {
					color = ((Portal) c).getColor();
					if(portals.get(color) == null)
						portals.put(color, new ArrayList<>());
					portals.get(color).add((Portal) c);
				}

				tab[y][x] = c;
				allPoint.put(tab[y][x], new Point(x, y));
			}
		}

		// Set the other portal for each portal
		for(List<Portal> list : portals.values()) {
			if(list.size() == 2) {
				list.get(0).setOtherPortal(list.get(1));
				list.get(1).setOtherPortal(list.get(0));
			}
			else
				throw new IllegalArgumentException("Portals must be in pairs.");
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

		for(int y = 0; y < height; y++) {
			line = br.readLine();
			values = line.split(" ");
			for(int x = 0; x < width; x++) {
				Entity e = Entity.loadEntity(values[x], tab[y][x]);
				if(e != null) {
					if(e instanceof Player)
						p = (Player) e;
					tab[y][x].enter(e, Direction.LEFT);
				}
			}
		}
	}


	// === Getters ===

	/**
	 * Returns the filename of the level.
	 *
	 * @return the filename of the level
	 */
	public String getLevelFilename() {
		return levelFilename;
	}

	/**
	 * Returns the height of the grid.
	 *
	 * @return the height of the grid
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the width of the grid.
	 *
	 * @return the width of the grid
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the player object.
	 *
	 * @return the player object
	 */
	public Player getPlayer() {
		return p;
	}

	/**
	 * Checks if the game is in a "loose" state.
	 * 
	 * @return true if the game is in a "loose" state, false otherwise.
	 */
	public boolean isLoose() {
		return loose;
	}

	/**
	 * Returns the cell at the specified point.
	 *
	 * @param p the point representing the coordinates of the cell
	 * @return the cell at the specified point
	 */
	public Cell getCell(Point p) {
		return tab[p.getY()][p.getX()];
	}

	/**
	 * Returns the adjacent cell based on the provided cell and direction.
	 * 
	 * @param cell the cell to get the adjacent cell from
	 * @param dir  the direction of the adjacent cell
	 * @return the adjacent cell based on the provided cell and direction
	 */
	public Cell getCell(Cell cell, Direction dir) {
		Point cellCoordinates = allPoint.get(cell);
		switch(dir) {
			case UP:
				return tab[cellCoordinates.getY() - 1][cellCoordinates.getX()];
			case DOWN:
				return tab[cellCoordinates.getY() + 1][cellCoordinates.getX()];
			case LEFT:
				return tab[cellCoordinates.getY()][cellCoordinates.getX() - 1];
			case RIGHT:
				return tab[cellCoordinates.getY()][cellCoordinates.getX() + 1];
			default:
				return null;
		}
	}


	// === Methods ===

	/**
	 * Set the loose attribute to true.
	 */
	public void gameOver() {
		loose = true;
	}

	/**
	 * Replace a cell by a new cell in the grid.
	 * tab and allPoint are updated at the same time.
	 *
	 * @param cell The cell to be replaced.
	 * @param newCell The new cell to be set.
	 * @param e The entity to be set in the new cell.
	 */
	public void setCell(Cell cell, Cell newCell, Entity e) {
		Point cellCoordinates = allPoint.get(cell);
		tab[cellCoordinates.getY()][cellCoordinates.getX()] = newCell;
		allPoint.remove(cell);
		allPoint.put(newCell, cellCoordinates);
		newCell.setOccupant(e);
		e.setCell(newCell);
	}

	/**
	 * Removes the specified entity from the grid.
	 *
	 * @param e the entity to be removed
	 */
	public void removeEntity(Entity e) {
		Point coordinates = getPosition(e);
		tab[coordinates.getY()][coordinates.getX()].setOccupant(null);
	}

	/**
	 * Get the position of the entity in the grid.
	 * 
	 * @param e the entity to get the position of
	 * @return the position of the entity in the grid
	 */
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

	/**
	 * Checks if the game has been won.
	 * 
	 * @return true if all box buttons are activated, false otherwise
	 */
	public boolean isWin() {
		for(Flag bb : flags)
			if(!bb.isActivated())
				return false;
		return true;
	}

	/**
	 * Updates the state of the doors based on the collected keys.
	 * If all keys have been picked up, all doors will be set to open.
	 */
	public void updateDoors() {
		boolean allKeys = true;
		for(Key k : keys)
			if(!k.isPickedUp())
				allKeys = false;
		for(Door d : doors)
			d.setOpen(allKeys);
	}
}
