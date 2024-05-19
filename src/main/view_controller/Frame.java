package main.view_controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import main.model.entity.Player;
import main.model.grid.Direction;
import main.model.grid.GridGame;


public class Frame extends JFrame implements Observer {
	private int width;
	private int height;
	public static final int IMAGE_SIZE = 24;
	public static final int IMAGE_FACTOR = 3;
	private CellPanel cellPanel;
	private EntityPanel entityPanel;
	private Player player;
	private GridGame gg;
	private static int animationCounter = 0;

	public Frame(GridGame gg, int width, int height) {
		this.gg = gg;
		this.width = width;
		this.height = height;
		this.player = gg.getPlayer();
		cellPanel = new CellPanel(gg);
		entityPanel = new EntityPanel(gg);

		build(cellPanel, entityPanel);
		addKeyboardListener();
		setVisible(true);
	}

	public void build(CellPanel gp, EntityPanel ep) {
		setTitle("Image Layers");

		setLayout(new BorderLayout());

		JLayeredPane layeredPane = new JLayeredPane();

		int windoWidth = width * IMAGE_SIZE * IMAGE_FACTOR;
		int windowHeight = height * IMAGE_SIZE * IMAGE_FACTOR;
		gp.setBounds(0, 0, windoWidth, windowHeight);
		ep.setBounds(0, 0, windoWidth, windowHeight);

		layeredPane.setPreferredSize(new Dimension(windoWidth, windowHeight));

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
						System.out.println("player mooves");
						break;
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:
						player.moveTo(Direction.RIGHT);
						System.out.println("player mooves");
						break;
					case KeyEvent.VK_UP:
					case KeyEvent.VK_Z:
						player.moveTo(Direction.UP);
						break;
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_S:
						player.moveTo(Direction.DOWN);
						break;
					// case KeyEvent.VK_R: // TODO: debug
					// 	gg.reset();
					// 	player = gg.getPlayer();
					// 	break;
					default:
						break;
				}
			}
		});
		requestFocus();
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		System.out.println("Update !");
		gg.updateDoors();
		if(gg.isWin()) {
			System.out.println("Win");
			dispose();
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
}
