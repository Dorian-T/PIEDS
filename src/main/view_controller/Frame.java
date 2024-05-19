package main.view_controller;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import main.model.entity.Player;
import main.model.grid.Direction;
import main.model.grid.GridGame;


public class Frame extends JFrame implements Observer {
	private static final int W = 16;
	private static final int H = 9;
	public static final int IMAGE_SIZE = 24;
	public static final int IMAGE_FACTOR = 3;
	private GroundPanel gp;
	private EntityPanel ep;
	private Player p;
	private GridGame gg;

	public Frame(GridGame gg) {
		// Charger les images
		this.gg = gg;
		this.p = gg.getPlayer();
		gp = new GroundPanel(gg);
		ep = new EntityPanel(gg);
		// Configurer la fenêtre principale
		build2(gp, ep);

		addKeyboardListener();
		setVisible(true);
	}

	public void build2(GroundPanel gp, EntityPanel ep) {
		setTitle("Image Layers");
		setSize(W*IMAGE_SIZE*IMAGE_FACTOR+50, H*IMAGE_SIZE*IMAGE_FACTOR+50);

		setLayout(new BorderLayout());

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(getSize());

		gp.setBounds(0, 0, W * IMAGE_SIZE*IMAGE_FACTOR, H * IMAGE_SIZE*IMAGE_FACTOR);
		ep.setBounds(0, 0, W * IMAGE_SIZE*IMAGE_FACTOR, H * IMAGE_SIZE*IMAGE_FACTOR);

		layeredPane.add(gp, Integer.valueOf(0));
		layeredPane.add(ep, Integer.valueOf(1));

		add(layeredPane, BorderLayout.CENTER);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void addKeyboardListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_Q:
						p.moveTo(Direction.LEFT);
						System.out.println("player mooves");
						break;
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:
						p.moveTo(Direction.RIGHT);
						System.out.println("player mooves");
						break;
					case KeyEvent.VK_UP:
					case KeyEvent.VK_Z:
						p.moveTo(Direction.UP);
						break;
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_S:
						p.moveTo(Direction.DOWN);
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
		System.out.println("Update !");
		if(gg.isWin()) {
			System.out.println("Win");
			dispose();
		}
		repaint();
	}
}
