package main.view_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
		addKeyboardListener();
	}

	private void build() {
		JPanel jp = new JPanel(new BorderLayout());
		JPanel jpC = new JPanel(new GridLayout(H, W));
		JPanel jpInfo = new JPanel();
		jp.add(jpC, BorderLayout.CENTER);
		jp.add(jpInfo, BorderLayout.EAST);
		add(jp);
		for(int y = 0; y < H; y++) {
			for(int x = 0; x < W; x++) {
				tabC[y][x] = new JPanel();
				tabImageGroundC[y][x] = new JLabel();
				tabImageEntityC[y][x] = new JLabel();
				
				if(!icons.containsKey(gg.getCell(new Point(x,y)).imagePath))
					icons.put((String) gg.getCell(new Point(x,y)).imagePath, new ImageIcon("asset/" + gg.getCell(new Point(x,y)).imagePath));
				tabImageGroundC[y][x].setIcon(icons.get(gg.getCell(new Point(x,y)).imagePath));
				if(gg.getCell(new Point(x,y)).getOccupant() != null) {
					if(!icons.containsKey(gg.getCell(new Point(x,y)).getOccupant().imagePath))
						icons.put((String) gg.getCell(new Point(x,y)).getOccupant().imagePath, new ImageIcon("asset/" + gg.getCell(new Point(x,y)).getOccupant().imagePath));
					tabImageEntityC[y][x].setIcon(icons.get(gg.getCell(new Point(x,y)).getOccupant().imagePath));
					System.out.println(gg.getCell(new Point(x,y)).getOccupant().imagePath);
				}
				tabC[y][x].add(tabImageGroundC[y][x]);
				tabC[y][x].add(tabImageEntityC[y][x]);
				//tabC[y][x].add(new JLabel(icons.get(gg.getCell(new Point(x,y)).imagePath)));
				//tabC[y][x].add(new JLabel(icons.get(gg.getCell(new Point(x,y)).getOccupant().imagePath)));
				jpC.add(tabC[y][x]);
			}
		}
        setSize(802,451); //le pixel permet de ne pas avoir de bordure impossible a effacer
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void addKeyboardListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Point temp = gg.getPosition(p);
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
				tabImageGroundC[y][x].setIcon(icons.get(gg.getCell(new Point(x,y)).imagePath));
				
				if(gg.getCell(new Point(x,y)).getOccupant() != null) {
					if(!icons.containsKey(gg.getCell(new Point(x,y)).getOccupant().imagePath))
						icons.put((String) gg.getCell(new Point(x,y)).getOccupant().imagePath, new ImageIcon("asset/" + gg.getCell(new Point(x,y)).getOccupant().imagePath));
					tabImageGroundC[y][x].setIcon(icons.get(gg.getCell(new Point(x,y)).getOccupant().imagePath));
					System.out.println(gg.getCell(new Point(x,y)).getOccupant().imagePath);
				}
			}
		}
	}
}