package main.view_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import main.model.entity.Player;
import main.model.grid.Direction;
import main.model.grid.GridGame;
import main.model.grid.Point;
import main.model.grid.cell.Cell;


public class Frame2 extends JFrame implements Observer {
	private static final int W = 16;
	private static final int H = 9;
	public static final int imageSize = 24;
	public static final int imageFactor = 3;
	private GroundPanel gp;
	private EntityPanel ep;
	private Player p;
	private GridGame gg;
	
	public Frame2(GridGame gg) {
        // Charger les images
        //groundImage = new ImageIcon("path_to_ground_image.png").getImage();
        //entityImage = new ImageIcon("path_to_entity_image.png").getImage();
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
        setSize(W*imageSize*imageFactor+50, H*imageSize*imageFactor+50);
        
        setLayout(new BorderLayout());
        
        /*add(gp, BorderLayout.CENTER);
        add(ep, BorderLayout.CENTER);*/

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(getSize());

        gp.setBounds(0, 0, W * imageSize*imageFactor, H * imageSize*imageFactor);
        ep.setBounds(0, 0, W * imageSize*imageFactor, H * imageSize*imageFactor);
        
        
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
						break;
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:
						p.moveTo(Direction.RIGHT);
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
		/*if(gg.isWin()) {
			System.out.println("Win");
			dispose();
		}*/
		repaint();
	}
}
