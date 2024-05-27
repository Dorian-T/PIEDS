package main.model.grid.cell;

import main.model.entity.Box;
import main.model.entity.Entity;
import main.model.entity.Player;
import main.model.grid.Direction;
import main.model.grid.GridGame;

/**
 * Represents a wall in the game. The wall is a cell that cannot be entered by
 * any entity.
 *
 * @see Cell
 */
public class Rail extends Cell {

	// === Variables ===

	/**
	 * Represents the directions in which a rail cell has openings.
	 */
	private Direction[] openings;
	
	/**
	 * The type of the rail.
	 */
	private int type;


	// === Constructors ===

	/**
	 * Constructor of the class Wall
	 *
	 * @param grid the grid where the wall is
	 * @param type the type of the rail
	 */
	public Rail(GridGame grid, int type) {
		super(grid);
		imagePath = "rail.png";
		this.type = type;
		switch (type) {
		case 0:
			openings = new Direction[] { Direction.LEFT, Direction.RIGHT, };
			break;
		case 1:
			openings = new Direction[] { Direction.UP, Direction.DOWN };
			break;
		case 2:
			openings = new Direction[] { Direction.UP, Direction.LEFT };
			break;
		case 3:
			openings = new Direction[] { Direction.UP, Direction.RIGHT };
			break;
		case 4:
			openings = new Direction[] { Direction.RIGHT, Direction.DOWN };
			break;
		case 5:
			openings = new Direction[] { Direction.DOWN, Direction.LEFT };
			break;
		case 6:
			openings = new Direction[] { Direction.RIGHT, Direction.DOWN, Direction.LEFT };
			break;
		case 7:
			openings = new Direction[] { Direction.UP, Direction.DOWN, Direction.LEFT };
			break;
		case 8:
			openings = new Direction[] { Direction.UP, Direction.RIGHT, Direction.LEFT };
			break;
		case 9:
			openings = new Direction[] { Direction.UP, Direction.RIGHT, Direction.DOWN };
			break;
		default:
			openings = new Direction[] { Direction.RIGHT, Direction.LEFT };
		}
	}

	/**
	 * Allows an entity to enter the rail cell from a specific direction.
	 * Checks if the entity enter by the right direction
	 *
	 * @param e The entity trying to enter the rail cell.
	 * @param dir The direction from which the entity is trying to enter.
	 * @return True if the entity successfully enters the rail cell, false otherwise.
	 */
	@Override
	public boolean enter(Entity e, Direction dir) {
		if (occupant != null) {
			if (e instanceof Box)
				return false;
			else {
				if(canExit(dir)) {
					Cell newCell = grid.getCell(this, dir);
					if (!newCell.enter(occupant, dir)) {
						return false;
					}
				}else {
					return false;
				}
			}
		}

		Direction inverse;
		switch (dir) {
		case LEFT:
			inverse = Direction.RIGHT;
			break;
		case RIGHT:
			inverse = Direction.LEFT;
			break;
		case UP:
			inverse = Direction.DOWN;
			break;
		case DOWN:
			inverse = Direction.UP;
			break;
		default:
			System.out.println("Impossible direction");
			return false;
		}
		boolean isOpened = false;
		if (e instanceof Player) {
			isOpened = true;
		}
		for (int i = 0; i < openings.length; i++) {
			if (openings[i].equals(inverse)) {
				isOpened = true;
			}
		}

		if(isOpened) {
			e.setDirection(dir);
			e.getCell().setOccupant(null);
			occupant = e;
			e.setCell(this);
		}
		return isOpened;
	}

	/**
	 * Checks if a player or an object can exit the rail in the specified direction.
	 *
	 * @param isPlayer true if the entity is a player, false if it's an object
	 * @param dir the direction in which the entity wants to exit
	 * @return true if the entity can exit, false otherwise
	 */
	@Override
	public boolean canExit(boolean isPlayer, Direction dir) {
		boolean isOpened = isPlayer; // if it's the player he can go through, otherwise it's false so it needs to be checked
		for (int i = 0; i < openings.length; i++) {
			if (openings[i].equals(dir)) {
				isOpened = true;
			}
		}
		return isOpened;
	}

	/**
	 * Checks if the train can exit the rail in the specified direction.
	 *
	 * @param dir the direction in which the train wants to exit
	 * @return true if the train can exit in the specified direction, false otherwise
	 */
	@Override
	public boolean canExit(Direction dir) {
		return canExit(false, dir);
	}

	/**
	 * Returns the number of versions for this rail.
	 *
	 * @return the number of versions
	 */
	@Override
	public int getNumberOfVersions() {
		return 10;
	}

	/**
	 * Returns the version of the rail. The version is the type of the rail.
	 *
	 * @return the version of the box
	 */
	@Override
	public int getVersion() {
		return type;
	}
}
