package main.model.grid.cell;

import main.model.Color;
import main.model.entity.Entity;
import main.model.grid.Direction;
import main.model.grid.GridGame;


/**
 * A portal is a cell that can be used to teleport the player to another portal of the same color.
 */
public class Portal extends Cell {
	
	// === Variables ===

	/**
	 * The color of the portal.
	 */
	private Color color;

	/**
	 * The reference to the other portal of the same color.
	 */
	private Portal otherPortal;


	// === Constructors ===

	/**
	 * Create a new portal.
	 *
	 * @param gg The grid game.
	 * @param color The color of the portal.
	 */
	public Portal(GridGame gg, Color color) {
		super(gg);
		this.color = color;
		imagePath = "portal.png";
	}

	public Portal(GridGame gg) {
		this(gg, Color.RED);
	}


	// === Getters ===

	/**
	 * Get the color of the portal.
	 *
	 * @return The color of the portal.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Returns the number of versions for this portal.
	 *
	 * @return the number of versions
	 */
	@Override
	public int getNumberOfVersions() {
		return 6;
	}

	/**
	 * Returns the version of the portal as an integer.
	 * The version is the color of the portal.
	 *
	 * @return the version of the portal
	 */
	@Override
	public int getVersion() {
		return color.toInt();
	}


	// === Setters ===

	/**
	 * Set the other portal of the same color.
	 *
	 * @param otherPortal The other portal.
	 */
	public void setOtherPortal(Portal otherPortal) {
		this.otherPortal = otherPortal;
	}


	// === Methods ===

	/**
	 * Check if the portal can be crossed by the player.
	 *
	 * @return True if the portal can be crossed, false otherwise.
	 */
	@Override
	public boolean enter(Entity e, Direction dir) {
		if(occupant == null) {
			Cell destination;
			int i = 0;
			boolean found = false;
			Direction newDir = dir;
			do {
				newDir = newDir.next();
				destination = grid.getCell(otherPortal, newDir);
				found = destination != null && destination.enter(e, newDir);
			} while(!found && i++ < 4);
			if(found)
				return true;
		}
		return super.enter(e, dir);
	}
}
