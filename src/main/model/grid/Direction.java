package main.model.grid;


/**
 * Represents the different directions that an entity can take.
 */
public enum Direction {

	// === Constants ===

	UP, DOWN, LEFT, RIGHT;


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
