package main.model.entity;

import main.model.grid.cell.Cell;

public class Player extends Entity {
	public static final Object imagePath = "player.png";

	public Player(Cell cell) {
		super(cell);
	}
}
