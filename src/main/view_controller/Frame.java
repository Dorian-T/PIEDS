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


public class Frame extends JFrame implements Observer {
	private static final int W = 16;
	private static final int H = 9;
	private JLayeredPane layeredPane;
	private JPanel[][] tabC;
	private JLabel[][] tabImageGroundC, tabImageEntityC; //tableau des images des cases (les labels pour les afficher)
	private Player p;
	private GridGame gg;
	
	private Map<String, ImageIcon> icons;

	public Frame(GridGame gg) {
		this.gg = gg;
		this.p = gg.getPlayer();
		tabC = new JPanel[H][W];
		tabImageGroundC = new JLabel[H][W];
		tabImageEntityC = new JLabel[H][W];
		
		this.icons = new HashMap<String, ImageIcon>();
		build();
		for (JLabel[] tab : tabImageGroundC) {
            for (JLabel label : tab) {
                label.setOpaque(false);
            }
        }
        for (JLabel[] tab : tabImageEntityC) {
            for (JLabel label : tab) {
                label.setOpaque(false);
            }
        }
		addKeyboardListener();
	}

	private void build() {
		
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		for(int y = 0; y < H; y++) {
			for(int x = 0; x < W; x++) {
				tabC[y][x] = new JPanel();
				tabC[y][x].setLayout(new BorderLayout());
				tabC[y][x].setBounds(x * 50, y * 50, 50, 50);
				
				tabImageGroundC[y][x] = new JLabel();
				tabImageEntityC[y][x] = new JLabel();
				
				
                
				if(!icons.containsKey(gg.getCell(new Point(x,y)).imagePath))
					icons.put((String) gg.getCell(new Point(x,y)).imagePath, new ImageIcon("asset/" + gg.getCell(new Point(x,y)).imagePath));
					//chat gpt me dit de rajouter ça:
				/*
				 * try {
		    BufferedImage groundImage = ImageIO.read(new File("asset/" + gg.getCell(new Point(x,y)).imagePath));
		    tabImageGroundC[y][x].setIcon(new ImageIcon(groundImage));
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
				 * */
				Image image = icons.get(gg.getCell(new Point(x, y)).imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				
				tabImageGroundC[y][x].setIcon(new ImageIcon(image));
				
				if(gg.getCell(new Point(x,y)).getOccupant() != null) {
					if(!icons.containsKey(gg.getCell(new Point(x,y)).getOccupant().imagePath))
						icons.put((String) gg.getCell(new Point(x,y)).getOccupant().imagePath, new ImageIcon("asset/" + gg.getCell(new Point(x,y)).getOccupant().imagePath));
					
					image = icons.get(gg.getCell(new Point(x, y)).getOccupant().imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
					
					tabImageEntityC[y][x].setIcon(new ImageIcon(image));

					System.out.println(gg.getCell(new Point(x,y)).getOccupant().imagePath);
				}

				tabC[y][x].add(tabImageGroundC[y][x], BorderLayout.CENTER);
				tabC[y][x].add(tabImageEntityC[y][x], BorderLayout.CENTER);
                
                layeredPane.add(tabC[y][x], JLayeredPane.DEFAULT_LAYER);

			}
		}
        setSize(802,451); //le pixel permet de ne pas avoir de bordure impossible a effacer
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
		for(int y = 0; y < tabC.length; y++) {
			for(int x = 0; x < tabC[y].length; x++) {
				if(!icons.containsKey(gg.getCell(new Point(x,y)).imagePath))
					icons.put((String) gg.getCell(new Point(x,y)).imagePath, new ImageIcon("asset/" + gg.getCell(new Point(x,y)).imagePath));

				
                Image image = icons.get(gg.getCell(new Point(x, y)).imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                
                ImageIcon scaledIcon = new ImageIcon(image);
                tabImageGroundC[y][x].setIcon(scaledIcon);
				
				tabImageEntityC[y][x].setIcon(null);
				if(gg.getCell(new Point(x,y)).getOccupant() != null) {
					if(!icons.containsKey(gg.getCell(new Point(x,y)).getOccupant().imagePath))
						icons.put((String) gg.getCell(new Point(x,y)).getOccupant().imagePath, new ImageIcon("asset/" + gg.getCell(new Point(x,y)).getOccupant().imagePath));
					
					image = icons.get(gg.getCell(new Point(x, y)).getOccupant().imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	                
	                scaledIcon = new ImageIcon(image);
					tabImageEntityC[y][x].setIcon(scaledIcon);
					System.out.println(gg.getCell(new Point(x,y)).getOccupant().imagePath);

				}
			}
		}
	}
}