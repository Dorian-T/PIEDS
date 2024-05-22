package main.view_controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.model.grid.GridGame;
import main.model.grid.Point;
import main.model.grid.cell.Cell;


/**
 * Panel for displaying the ground.
 * The ground is composed of cells that can be of different types.
 * Each cell has an image associated with it.
 * The images are loaded from the asset folder and displayed on the panel.
 * The images are animated to create a visual effect.
 * 
 * @see JPanel
 */
public class CellPanel extends BasePanel {

	// === Constructors ===

	/**
	 * Constructor of the class GroundPanel
	 * 
	 * @param gg the grid game containing the cells to be displayed
	 */
	public CellPanel(GridGame gg) {
		super(gg);
		setBackground(new Color(19, 23, 30));
	}

	/**
	 * Draws the ground panel.
	 * The images are loaded from the asset folder and displayed on the panel.
	 * The images are animated to create a visual effect.
	 * 
	 * @param g the graphics object used to draw the panel
	 */
	@Override
	protected void paintComponent(Graphics g) {
		BufferedImage subImg;
		Cell cellXY;
		String path;

		try {
			super.paintComponent(g);

			for(int y = 0; y < gg.getHeight(); y++) {
				for(int x = 0; x < gg.getWidth(); x++) {
					cellXY = gg.getCell(new Point(x,y));
					path = getPath(cellXY);

					// If the image is not already loaded, load it
					if(!images.containsKey(path))
						loadImage(path, 3, cellXY.getNumberOfVersions());

					// Draw the image
					subImg = images.get(path);
					if(subImg != null)
						drawImg(g, subImg, x, y, Frame.getAnimationCounter(), cellXY.getVersion());
				}
			}
		}
		catch (Exception e) {
			System.err.println("Error while drawing the cell panel: " + e.getMessage());
		}
	}

	/**
	 * Returns the path of the image file to be loaded.
	 * If the cell is a BoxButton, the path is constructed using the color of the button.
	 * If the cell does not have an image path, null is returned.
	 * 
	 * @param param the cell to be drawn
	 * @return the path of the image file to be loaded or null if the cell does not have an image path
	 */
	@Override
	protected String getPath(Object param) {
		String path = ((Cell) param).getImagePath();
		if(path  == null)
			return null;
		return "data/assets/" + path;
	}
}
