package main.model.grid;


/**
 * Represents a point in the grid.
 */
public class Point {

	// === Variables ===

	/**
	 * Represents the x-coordinate of the point.
	 */
	private int x;

	/**
	 * Represents the y-coordinate of the point.
	 */
	private int y;


	// === Constructors ===

	/**
	 * Constructs a point with the given x and y coordinates.
	 *
	 * @param x The x-coordinate of the point.
	 * @param y The y-coordinate of the point.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructs a point with the given x and y coordinates and a direction.
	 *
	 * @param x The x-coordinate of the point.
	 * @param y The y-coordinate of the point.
	 * @param dir The direction to move in.
	 */
	public Point(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		switch(dir) {
			case LEFT:
				this.x--;
				break;
			case RIGHT:
				this.x++;
				break;
			case UP:
				this.y--;
				break;
			case DOWN:
				this.y++;
				break;
		}
	}


	// === Getters ===

	/**
	 * Returns the x-coordinate of the point.
	 *
	 * @return The x-coordinate of the point.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y-coordinate of the point.
	 *
	 * @return The y-coordinate of the point.
	 */
	public int getY() {
		return y;
	}
}

