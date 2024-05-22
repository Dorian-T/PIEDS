package main.view_controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.model.entity.Entity;
import main.model.grid.GridGame;
import main.model.grid.Point;


/**
 * Panel for displaying the entities.
 */
public class EntityPanel extends BasePanel {

	// === Constructors ===
	
	/**
	 * Represents a panel that displays an entity in a grid game.
	 * Extends the base panel class and initializes it with the given grid game.
	 * 
	 * @param gg the grid game containing the entities to be displayed
	 */
	public EntityPanel(GridGame gg) {
		super(gg);
	}

	/**
	 * Determines whether this panel is opaque or not.
	 * 
	 * @return false because the panel is transparent
	 */
	@Override
	public boolean isOpaque() {
		return false;
	}

	/**
	 * Overrides the paintComponent method to draw the entities on the panel.
	 * 
	 * @param g the Graphics object used for painting
	 */
	@Override
	protected void paintComponent(Graphics g) {
		BufferedImage subImg;
		Entity entityXY;
		String path;

		try {
			super.paintComponent(g);

			for(int y = 0; y < gg.getHeight(); y++) {
				for(int x = 0; x < gg.getWidth(); x++) {
					entityXY = gg.getCell(new Point(x,y)).getOccupant();
					if(entityXY != null) {
						path = getPath(entityXY);

						// If the image is not already loaded, load it
						if(!images.containsKey(path))
							loadImage(path, 3, entityXY.getNumberOfVersions());

						// Draw the image
						subImg = images.get(path);
						if(subImg != null)
							drawImg(g, subImg, x, y, Frame.getAnimationCounter(), entityXY.getVersion());
					}
				}
			}
		}
		catch (Exception e) {
			System.err.println("Error while drawing the entity panel: " + e.getMessage());
		}
	}

	/**
	 * Returns the path of the image associated with the given entity.
	 * If the entity does not have an image path, null is returned.
	 *
	 * @param param the entity object
	 * @return the path of the image, or null if no image path is available
	 */
	@Override
	protected String getPath(Object param) {
		String path = ((Entity) param).getImagePath();
		if(path  == null)
			return null;
		return "data/assets/" + path;
	}
}
