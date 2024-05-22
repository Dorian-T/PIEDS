package main.view_controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.model.grid.GridGame;


/**
 * Represents the menu of the game.
 * The menu contains buttons for selecting the levels.
 */
public class Menu extends JFrame {

	// === Constructors ===

	/**
	 * Represents the menu of the game.
	 * The menu contains buttons for selecting the levels.
	 */
	public Menu() {
		try {
			setTitle("PIEDS");
			setLayout(new BorderLayout());
			getContentPane().setBackground(new Color(19, 23, 30));
			
			addTitle();
			addLevelsButtons();
			addControls();
			
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			pack();
			setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds the title to the menu.
	 
	 */
	private void addTitle() {
		// Create panel
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(titlePanel, BorderLayout.NORTH);

		// Create title
		JLabel titleLabel = new JLabel("Sélectionnez un niveau");
		titleLabel.setFont(titleLabel.getFont().deriveFont(36.0f));
		titleLabel.setForeground(Color.WHITE);
		titlePanel.add(titleLabel);

		pack();
	}

	/**
	 * Adds the buttons for selecting the levels to the menu.
	 */
	private void addLevelsButtons() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(0, 60, 20, 0));
		add(panel, BorderLayout.WEST);

		List<String> levelPaths = getLevels();
		List<String> levelNames = new ArrayList<>(levelPaths);
		cleanText(levelNames);

		for (int i = 0; i < levelPaths.size(); i++) {
			final int index = i;
			JButton button = new JButton(levelNames.get(i));

			// Style
			button.setContentAreaFilled(false);
			button.setOpaque(true);
			button.setBackground(new Color(41, 49, 65));
			button.setForeground(Color.WHITE);

			// Listener
			button.addActionListener(e -> {
				GridGame g = new GridGame("data/levels/" + levelPaths.get(index));
				Frame f = new Frame(g, g.getWidth(), g.getHeight());
				g.getPlayer().addObserver(f);
				f.setVisible(true);
				dispose();
			});

			panel.add(button);
			panel.add(Box.createVerticalStrut(2));
		}

		pack();
	}

	/**
	 * Adds the controls to the menu.
	 */
	private void addControls() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(0, 0, 20, 60));
		add(panel, BorderLayout.EAST);

		// Label which explain controls
		JLabel label = new JLabel("<html>↑ ou Z : Haut<br>← ou Q : Gauche<br>↓ ou S : Bas<br>→ ou D : Droite<br>R : Recommencer</html>");
		label.setFont(label.getFont().deriveFont(18.0f));
		label.setForeground(Color.WHITE);
		panel.add(label);

		// Space
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Quit button
		JButton button = new JButton("Quitter");
		button.setContentAreaFilled(false);
		button.setOpaque(true);
		button.setBackground(new Color(41, 49, 65));
		button.setForeground(Color.WHITE);
		button.addActionListener(e -> dispose());
		panel.add(button);

		pack();
	}


	// === Methods ===

	/**
	 * Returns the list of levels available in the data/levels folder.
	 * 
	 * @return the list of levels
	 */
	private List<String> getLevels() {
		List<String> levels = new ArrayList<>();
		try (Stream<Path> paths = Files.walk(Paths.get("data/levels"))) {
            levels = paths
                .filter(Files::isRegularFile)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
            } catch (IOException e) {
            e.printStackTrace();
        }
        return levels;
	}

	private static void cleanText(List<String> levels) {
		for (int i = 0; i < levels.size(); i++) {
			levels.set(i, levels.get(i).replace("_", " "));
			levels.set(i, levels.get(i).replace(".txt", ""));
		}
	}
}
