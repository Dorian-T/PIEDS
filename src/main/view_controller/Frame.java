package main.view_controller;

import java.awt.BorderLayout;
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
		build();
		addKeyboardListener();
		tabC = new JPanel[H][W];
	}

	private void build() {
		JPanel jp = new JPanel(new BorderLayout());
		JPanel jpC = new JPanel(new GridLayout(W, H));
		JPanel jpInfo = new JPanel();
		jp.add(jpC, BorderLayout.CENTER);
		jp.add(jpInfo, BorderLayout.EAST);
		add(jp);
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				tabC[i][j] = new JPanel();
				jpC.add(tabC[i][j]);
			}
		}
	}

	private void addKeyboardListener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						System.out.println("LEFT"); // TODO: remove debug output
						c.move(Direction.LEFT);
						break;
				}
			}
		});
		requestFocus();
	}

	@Override
	public void update(java.util.Observable o, Object arg) {
		// TODO Auto-generated method stub
	}
}