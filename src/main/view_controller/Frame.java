package main.view_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.model.Case;
import main.model.Direction;


public class Frame extends JFrame implements Observer {
	private static final int W = 16;
	private static final int H = 9;
	private JPanel[][] tabC;
	private Case c;

	public Frame(Case c) {
		this.c = c;
		tabC = new JPanel[H][W];
		build();
		addKeyboardListener();
		
	}

	private void build() {
		JPanel jp = new JPanel(new BorderLayout());
		JPanel jpC = new JPanel(new GridLayout(W, H));
		JPanel jpInfo = new JPanel();
		jp.add(jpC, BorderLayout.CENTER);
		jp.add(jpInfo, BorderLayout.EAST);
		add(jp);
		for(int j = 0; j < W; j++) {
				for(int i = 0; i < H; i++) {
				tabC[i][j] = new JPanel();
				jpC.add(tabC[i][j]);
			}
		}
	}

	private void addKeyboardListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tabC[c.getX()][c.getY()].setBackground(Color.LIGHT_GRAY);
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
				tabC[c.getX()][c.getY()].setBackground(Color.YELLOW);
			}
		});
		requestFocus();
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		for(int i = 0; i < tabC.length; i++) {
			for(int j = 0; j < tabC[i].length; j++) {
				if(i == c.getX() && j == c.getY()) {
					tabC[i][j].setBackground(Color.LIGHT_GRAY);
				}else {
					tabC[i][j].setBackground(Color.CYAN);
				}
			}
		}
	}
}