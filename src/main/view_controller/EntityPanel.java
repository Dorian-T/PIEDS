package main.view_controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.model.entity.Entity;
import main.model.entity.Player;
import main.model.grid.GridGame;
import main.model.grid.Point;

//Panneau pour afficher les entités
public class EntityPanel extends BasePanel {

	// === Constructors ===
	
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

	@Override
	protected void paintComponent(Graphics g) {
		BufferedImage subImg;
		Entity entityXY;
		String path;

		try {
			super.paintComponent(g);

			for(int x = 0; x < gg.getWidth(); x++) {
				for(int y = 0; y < gg.getHeight(); y++) {
					entityXY = gg.getCell(new Point(x,y)).getOccupant();
					if(entityXY != null) {
						path = getPath(entityXY);

						// If the image is not already loaded, load it
						if(!images.containsKey(path)) {
							loadImage(path, 3, (entityXY instanceof Player) ? 4 : 1); // TODO: remove the instanceof
						}

						// Draw the image
						subImg = images.get(path);
						if(subImg != null) {
							drawImg(g, subImg, x, y, Frame.getAnimationCounter(), entityXY.getVersion());
						}
					}
				}
			}
		}
		catch (Exception e) {
			System.err.println("Error while drawing the entity panel: " + e.getMessage());
		}
	}

	@Override
	protected String getPath(Object param) {
		String path = ((Entity) param).getImagePath();
		if(path  == null)
			return null;
		return "data/assets/" + path;
	}
}
