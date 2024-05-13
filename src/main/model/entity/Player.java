package main.model.entity;

import main.model.grid.cell.Cell;
import main.model.grid.Direction;


public class Player extends Entity {
	public Player(Cell cell) {
		super(cell);
		imagePath = "player.png";
	}

	@Override
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
