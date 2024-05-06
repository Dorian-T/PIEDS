package main.model.grid;

/**
 * coordonnées dans le tableau
 */
public class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

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
}

