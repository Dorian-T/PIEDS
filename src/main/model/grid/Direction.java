package main.model.grid;

public enum Direction {
	UP, DOWN, LEFT, RIGHT;

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
