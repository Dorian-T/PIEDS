package main.view_controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import main.App;
import main.model.entity.Player;
import main.model.grid.Direction;
import main.model.grid.GridGame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;


public class Frame extends JFrame implements Observer {
	private int xSize;
	private int ySize;
	public static final int IMAGE_SIZE = 24;
	public static final int IMAGE_FACTOR = 3;
	private CellPanel cellPanel;
	private EntityPanel entityPanel;
	private Player player;
	private GridGame gg;
	private static int animationCounter = 0;

	public Frame(GridGame gg, int width, int height) {
		this.gg = gg;
		this.xSize = width;
		this.ySize = height;
		this.player = gg.getPlayer();
		cellPanel = new CellPanel(gg);
		entityPanel = new EntityPanel(gg);

		build(cellPanel, entityPanel);
		addKeyboardListener();
		setVisible(true);
	}

	public void build(CellPanel gp, EntityPanel ep) {
		setTitle("PIEDS");

		setLayout(new BorderLayout());

		JLayeredPane layeredPane = new JLayeredPane();

		int windowWidth = xSize * IMAGE_SIZE * IMAGE_FACTOR;
		int windowHeight = ySize * IMAGE_SIZE * IMAGE_FACTOR;
		gp.setBounds(0, 0, windowWidth, windowHeight);
		ep.setBounds(0, 0, windowWidth, windowHeight);

		layeredPane.setPreferredSize(new Dimension(windowWidth, windowHeight));

		layeredPane.add(gp, Integer.valueOf(0));
		layeredPane.add(ep, Integer.valueOf(1));

		add(layeredPane, BorderLayout.CENTER);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		pack();
	}

	private void addKeyboardListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_Q:
						player.moveTo(Direction.LEFT);
						break;
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:
						player.moveTo(Direction.RIGHT);
						break;
					case KeyEvent.VK_UP:
					case KeyEvent.VK_Z:
						player.moveTo(Direction.UP);
						break;
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_S:
						player.moveTo(Direction.DOWN);
						break;
					case KeyEvent.VK_R: // TODO: debug
						GridGame g = new GridGame(gg.getLevelFilename());
						Frame f = new Frame(g, g.getWidth(), g.getHeight());
						g.getPlayer().addObserver(f);
						f.setVisible(true);
						dispose();
					 	break;
					default:
						break;
				}
			}
		});
		requestFocus();
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		gg.updateDoors();
		if(gg.isWin()) {
			System.out.println("Win");
			showEndScreen();
		}
		animationCounter = (animationCounter + 1) % 3;
		repaint();
	}

	/**
	 * Returns the value of the animation counter.
	 *
	 * @return the value of the animation counter
	 */
	public static int getAnimationCounter() {
		return animationCounter;
	}

	/**
	 * Displays the end screen of the game.
	 * This method repaints the frame, loads an image, waits for the image to be loaded,
	 * creates a buffered image, and draws the image on the graphics object.
	 * It then sleeps for certain durations and finally disposes the frame.
	 */
	private void showEndScreen() {
		repaint();

		// Load the image
		Image win = new ImageIcon("data/assets/win.png").getImage();
		win = win.getScaledInstance(Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR * 3, Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR, Image.SCALE_DEFAULT);

		// Wait for the image to be loaded
		MediaTracker tracker = new MediaTracker(new Component() {});
		tracker.addImage(win, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Create a buffered image
		BufferedImage bufferedWin = new BufferedImage(win.getWidth(null), win.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics temp = bufferedWin.createGraphics();
		temp.drawImage(win, 0, 0, null);
		temp.dispose();

		Graphics g = getGraphics();

		try {
			drawWin(g, bufferedWin, 1, 1, 1);
			Thread.sleep(500);
			drawWin(g, bufferedWin, 0, 1, 3);
			Thread.sleep(500);
			drawWin(g, bufferedWin, 0, 2, 3);
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		App.openMenu();
		dispose();
	}

	/**
	 * Draws a portion of an image on the graphics context.
	 *
	 * @param g The graphics context to draw on.
	 * @param img The image to draw.
	 * @param startLetter The index of the starting letter.
	 * @param endLetter The index of the ending letter.
	 * @param n The number of letters.
	 */
	private void drawWin(Graphics g, BufferedImage img, int startLetter, int endLetter, int n) {
		img = img.getSubimage(
			startLetter * Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR,
			0,
			(endLetter - startLetter + 1) * Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR,
			Frame.IMAGE_SIZE * Frame.IMAGE_FACTOR
		);

		int x = (getWidth() - n * IMAGE_SIZE * IMAGE_FACTOR) / 2;
		int y = (getHeight() - IMAGE_SIZE * IMAGE_FACTOR) / 2;
		g.drawImage(img, x, y, this);
	}
}
