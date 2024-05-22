package main.view_controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.model.grid.GridGame;


/**
 * Panel for displaying the ground.
 * The ground is composed of cells that can be of different types.
 * Each cell has an image associated with it.
 * The images are loaded from the asset folder and displayed on the panel.
 * The images are animated to create a visual effect.
 * 
 * @see JPanel
 */
public abstract class BasePanel extends JPanel {

	// === Attributes ===

	/**
	 * The grid game containing the objects to be displayed on the panel.
	 */
	protected GridGame gg;

	/**
	 * A map that stores images associated with their corresponding string keys.
	 */
	protected Map<String, BufferedImage> images;

	/**
	 * Constructor of the class GroundPanel
	 * 
	 * @param gg the grid game containing the cells to be displayed
	 */
	protected BasePanel(GridGame gg) {
		this.gg = gg;
		images = new HashMap<>();
	}

	/**
	 * Loads an image from the specified path and stores it in the groundImages map.
	 * The loaded image is scaled to a specific size and converted to a buffered image.
	 * 
	 * @param path the path of the image file to be loaded
	 * @throws InterruptedException if the image loading process is interrupted
	 */
	protected void loadImage(String path, int sizeX, int sizeY) throws InterruptedException {
		if(path == null)
			images.put(null, null);
		else {
			// Load the image
			Image loadedImage = new ImageIcon(path).getImage();
			loadedImage = loadedImage.getScaledInstance(Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR * sizeX, Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR * sizeY, Image.SCALE_DEFAULT);
		
			// Wait for the image to be loaded
			MediaTracker tracker = new MediaTracker(new Component() {});
			tracker.addImage(loadedImage, 0);
			try {
				tracker.waitForID(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Create a buffered image
			BufferedImage bufferedImage = new BufferedImage(loadedImage.getWidth(null), loadedImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics graphics = bufferedImage.createGraphics();
			graphics.drawImage(loadedImage, 0, 0, null);
			graphics.dispose();
			images.put(path, bufferedImage);
		}
	}

	/**
	 * Returns the path of the image file to be loaded.
	 * 
	 * @param param the parameter used to determine the path of the image file
	 * @return the path of the image file to be loaded
	 */
	protected abstract String getPath(Object param);

	/**
	 * Draws the image on the panel.
	 * 
	 * @param g the graphics object used to draw the image
	 * @param img the image to be drawn
	 * @param x the x coordinate of the image
	 * @param y the y coordinate of the image
	 */
	protected void drawImg(Graphics g, BufferedImage img, int x, int y, int offsetX, int offsetY) {
		img = img.getSubimage(
			offsetX * Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR,
			offsetY * Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR,
			Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR,
			Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR
		);
		int xOnScreen = x * Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR;
		int yOnScreen = y * Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR;
		g.drawImage(img, xOnScreen, yOnScreen, this);
	}
}
