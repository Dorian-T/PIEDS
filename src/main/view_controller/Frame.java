package main.view_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import main.model.entity.Player;
import main.model.grid.Direction;
import main.model.grid.GridGame;
import main.model.grid.cell.Cell;


public class Frame extends JFrame implements Observer {
	private static final int W = 16;
	private static final int H = 9;
	private JPanel[][] tabC;
	private Player p;
	private GridGame gg;
	
	private Map<String, ImageIcon> icons;

	public Frame(GridGame gg) {
		this.p = gg.getPlayer();
		tabC = new JPanel[H][W];
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
				tabC[gg.getPosition(p).y][gg.getPosition(p).x].setBackground(Color.LIGHT_GRAY);
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						p.moveTo(gg.getCible(gg.getPosition(p), Direction.LEFT), Direction.LEFT);
						break;
					case KeyEvent.VK_RIGHT:
						p.moveTo(gg.getCible(gg.getPosition(p), Direction.RIGHT), Direction.RIGHT);
						break;
					case KeyEvent.VK_UP:
						p.moveTo(gg.getCible(gg.getPosition(p), Direction.UP), Direction.UP);
						break;
					case KeyEvent.VK_DOWN:
						p.moveTo(gg.getCible(gg.getPosition(p), Direction.DOWN), Direction.DOWN);
						break;
				}
				tabC[gg.getPosition(p).y][gg.getPosition(p).x].setBackground(Color.YELLOW);
			}
		});
		requestFocus();
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		for(int y = 0; y < tabC.length; y++) {
			for(int x = 0; x < tabC[y].length; x++) {
				tabC[y][x].add(new JLabel(icons.get(gg.getCell(x,y).imagePath)));
				tabC[y][x].add(new JLabel(icons.get(gg.getCell(x,y).getOccupant().imagePath)));
				if(y == gg.getPosition(p).y && x == gg.getPosition(p).x) {
					
					tabC[y][x].setBackground(Color.LIGHT_GRAY);
					
				}else {
					tabC[y][x].setBackground(Color.CYAN);
				}
			}
		}
	}
}