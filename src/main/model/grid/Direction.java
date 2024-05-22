package main.model.grid;


/**
 * Represents the different directions that an entity can take.
 */
public enum Direction {

	// === Constants ===

	/**
	 * The direction up
	 */
	UP,

	/**
	 * The direction right
	 */
	RIGHT,

	/**
	 * The direction down
	 */
	DOWN,

	/**
	 * The direction left
	 */
	LEFT;


	// === Methods ===

	/**
	 * Returns the next direction in the order UP, RIGHT, DOWN, LEFT.
	 * 
	 * @return the next direction
	 */
	public Direction next() {
		switch (this) {
			case UP:
				return RIGHT;
			case RIGHT:
				return DOWN;
			case DOWN:
				return LEFT;
			case LEFT:
				return UP;
			default:
				return null;
		}
	}
}
