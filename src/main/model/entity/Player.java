package main.model.entity;

import main.model.grid.Direction;
import main.model.grid.cell.Cell;

public class Player extends Entity {
	public String imagePath = "player.png";

	public Player(Cell cell) {
		super(cell);
	}
	
	public boolean moveTo(Direction dir) {
		Cell newCell = cell.getCell(cell, dir);
		if(newCell.enter(this, dir)) {
			cell = newCell;
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}
}
