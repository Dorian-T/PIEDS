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

import main.model.entity.Box;
import main.model.grid.GridGame;
import main.model.grid.Point;
import main.model.grid.cell.BoxButton;
import main.model.grid.cell.Cell;


//Panneau pour afficher le sol
public class GroundPanel extends JPanel {
	private GridGame gg;
	private Map<String, BufferedImage> groundImages;
	private int n;

	public GroundPanel(GridGame gg) {
		this.gg = gg;
		groundImages = new HashMap<>();
		n = 0;
	}

	@Override
	protected void paintComponent(Graphics g) {
		BufferedImage subImg;
		int xOnScreen, yOnScreen;

		try {
			super.paintComponent(g);

			for(int x = 0; x < gg.getWidth(); x++) {
				for(int y = 0; y < gg.getHeight(); y++) {
					Cell cellXY = gg.getCell(new Point(x,y));

					// If the image is not already loaded, load it
					if(!groundImages.containsKey(getPath(cellXY))) {
						loadImage(cellXY);
					}

					// Draw the image
					System.out.println(
						groundImages.get(getPath(cellXY)).getWidth() + " " + groundImages.get(getPath(cellXY)).getHeight()
						+ " - n: " + n
						+ " x: " + n * Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR + " y: " + 0 + " w: " + Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR + " h: " + Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR
						+ " - x+width: " + n * Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR + Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR + " getWidth(): " + groundImages.get(getPath(cellXY)).getWidth()
					);
					subImg = groundImages.get(getPath(cellXY)).getSubimage(n * Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR, 0, Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR, Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR);
					xOnScreen = x * Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR;
					yOnScreen = y * Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR;
					g.drawImage(subImg, xOnScreen, yOnScreen, this);
				}
			}

			// Counter
			n = n == 2 ? 0 : n + 1;
		}
		catch (Exception e) {
			System.err.println("Error while drawing the ground panel: " + e.getMessage());
		}
	}

	/**
	 * Loads an image from the specified path and stores it in the groundImages map.
	 * The loaded image is scaled to a specific size and converted to a buffered image.
	 * 
	 * @param path the path of the image file to be loaded
	 * @throws InterruptedException if the image loading process is interrupted
	 */
	private void loadImage(Cell cell) throws InterruptedException {
		// Load the image
		String path = getPath(cell);
		System.out.println("Loading image: " + path); // DEBUG
		Image loadedImage = new ImageIcon(path).getImage();
		loadedImage = loadedImage.getScaledInstance(Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR * 3, Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR, Image.SCALE_DEFAULT);
	
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
		groundImages.put(path, bufferedImage);
	}

	/**
	 * Returns the path of the image file to be loaded.
	 * 
	 * @param cell the cell containing the image path
	 * @return the path of the image file to be loaded
	 */
	private String getPath(Cell cell) {
		if(cell instanceof BoxButton)
			return "asset/" + ((BoxButton) cell).getColor() + "-" + cell.imagePath;
		return "asset/" + cell.imagePath;
	}
}
