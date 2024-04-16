package main.view_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.model.Cell;
import main.model.Direction;


public class Frame extends JFrame implements Observer {
	private static final int W = 16;
	private static final int H = 9;
	private JPanel[][] tabC;
	private Cell c;

	public Frame(Cell c) {
		this.c = c;
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
	}

	private void addKeyboardListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tabC[c.getY()][c.getX()].setBackground(Color.LIGHT_GRAY);
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						System.out.println("LEFT"); // TODO: remove debug output
						c.move(Direction.LEFT);
						break;
					case KeyEvent.VK_RIGHT:
						System.out.println("RIGHT"); // TODO: remove debug output
						c.move(Direction.RIGHT);
						break;
					case KeyEvent.VK_UP:
						System.out.println("UP"); // TODO: remove debug output
						c.move(Direction.UP);
						break;
					case KeyEvent.VK_DOWN:
						System.out.println("DOWN"); // TODO: remove debug output
						c.move(Direction.DOWN);
						break;
				}
				tabC[c.getY()][c.getX()].setBackground(Color.YELLOW);
			}
		});
		requestFocus();
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		for(int y = 0; y < tabC.length; y++) {
			for(int x = 0; x < tabC[y].length; x++) {
				if(y == c.getY() && x == c.getX()) {
					tabC[y][x].setBackground(Color.LIGHT_GRAY);
				}else {
					tabC[y][x].setBackground(Color.CYAN);
				}
			}
		}
	}
}