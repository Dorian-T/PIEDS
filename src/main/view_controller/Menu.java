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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

			addTitle();

			addLevelsButtons();

			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			pack();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Adds the title to the menu.
	 
	 */
	private void addTitle() {
		// Create panel
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(500, 64));
		titlePanel.setBackground(Color.GREEN);
		add(titlePanel, BorderLayout.NORTH);

		// Create title
		JLabel titleLabel = new JLabel("Sélectionnez un niveau");
		titleLabel.setFont(titleLabel.getFont().deriveFont(36.0f));
		titlePanel.add(titleLabel);
	}

	/**
	 * Adds the buttons for selecting the levels to the menu.
	 */
	private void addLevelsButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		add(panel, BorderLayout.CENTER);

		for (String level : getLevels()) {
			JButton button = new JButton(level);
			button.addActionListener(e -> {
				GridGame g = new GridGame("data/levels/" + level);
				Frame f = new Frame(g, g.getWidth(), g.getHeight());
				g.getPlayer().addObserver(f);
				f.setVisible(true);
				dispose();
			});
			panel.add(button);
		}

		pack();
	}


	// === Methods ===

	/**
	 * Returns the list of levels available in the data/levels folder.
	 * 
	 * @return the list of levels
	 */
	private List<String> getLevels() {
		List<String> levels = new ArrayList<String>();
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
}
