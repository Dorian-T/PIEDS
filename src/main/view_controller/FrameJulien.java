package main.view_controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
// Importation des classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Model.Bloc;
import Model.Case;
import Model.Direction;
import Model.Entity;
import Model.Grille;
import Model.Hero;
import Model.Mur;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

// Classe qui est un Model qui sert à afficher la fenêtre
public class FrameJulien extends JFrame implements Observer { // implements Observer

    private final int HAUTEUR = 100;
    private final int LARGEUR = 100;
    private Grille grille;
    private JPanel[][] tabH; // Tableau pour a partir de i et j obtenir la case
    private int gridSize, cellSize;
    private JPanel grid;
    private String levelPath;
    private KeyAdapter keyAdapter;
    private JLabel compteurPas;

    // Stockage du niveau
    private int numNiveau = 0;
    private String basePath = "src/level/level";

    public Frame(String levelPath, Grille grille) {
        this.levelPath = levelPath;
        this.grille = grille;
        initiation();
    }

    public void loadLevel(int numNiveau) {
        this.levelPath = basePath + numNiveau + ".txt";
        Model.Level level = new Model.Level(levelPath);
        this.grille = level.getGrille();
        initiation();
    }

    public void initiation() {
        this.gridSize = grille.getGridSize();
        this.cellSize = grille.getCellSize();
        this.tabH = new JPanel[LARGEUR][HAUTEUR];
        build();
        addKeyListener();
        grille.addObserver(this);
        update(null, null);
    }

    public void resetFrame() {
        grille.deleteObserver(this);
        grille = grille.resetNiveau(levelPath);
        initiation();
    }

    public void niveauSuivant() {
        numNiveau++;
        loadLevel(numNiveau);
    }

    public void precedent() {
        numNiveau--;
        if (numNiveau < 0) {
            numNiveau = 0;
        }
        loadLevel(numNiveau);
    }

    public void suivant() {
        numNiveau++;
        loadLevel(numNiveau);
    }

    public void build() {
        getContentPane().removeAll();

        setTitle("Jeu - 2D");
        setSize(gridSize * cellSize + 5, gridSize * cellSize + 95);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        grid = new JPanel(new GridLayout(gridSize, gridSize));
        JMenuBar menu = new JMenuBar();
        JMenu menu1 = new JMenu("Menu");
        JMenuItem item = new JMenuItem("Quitter");
        JMenuItem item2 = new JMenuItem("Précédent");
        JMenuItem item3 = new JMenuItem("Suivant");
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("Reset");
        compteurPas = new JLabel("Steps : " + grille.getPas());
        compteurPas.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        // ajout du bouton reset
        buttonPanel.add(button);

        // Ajout menu
        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item);
        menu.add(menu1);
        menu.add(Box.createHorizontalGlue());
        menu.add(compteurPas);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(menu, BorderLayout.NORTH);
        panel.add(grid, BorderLayout.CENTER);

        button.setFocusable(false);
        item.setFocusable(false);

        item.addActionListener(new Exit(this));
        item2.addActionListener(new Precedent(this));
        item3.addActionListener(new Suivant(this));
        button.addActionListener(new Reset(this));

        grilleAffichage();

        this.getContentPane().add(panel);
    }

    public void grilleAffichage() {
        for (int i = 0; i < gridSize; i++) { // Boucle qui permet de créer la grille
            for (int j = 0; j < gridSize; j++) {
                tabH[i][j] = new JPanel();
                tabH[i][j].setPreferredSize(new Dimension(cellSize, cellSize));
                grid.add(tabH[i][j]);
            }
        }
    }

    public void addKeyListener() { // sert à déplacer la case avec les flèches du clavier
        if (keyAdapter != null) {
            this.removeKeyListener(keyAdapter);
        }
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        grille.deplacerHeros(Direction.HAUT);
                        break;
                    case KeyEvent.VK_DOWN:
                        grille.deplacerHeros(Direction.BAS);
                        break;
                    case KeyEvent.VK_LEFT:
                        grille.deplacerHeros(Direction.GAUCHE);
                        break;
                    case KeyEvent.VK_RIGHT:
                        grille.deplacerHeros(Direction.DROITE);
                        break;
                }
            }
        };
        addKeyListener(keyAdapter);
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void update(Observable o, Object arg) {
        compteurPas.setText("Steps : " + grille.getPas());
        updateGrille();
        if (grille.verifierVictoire()) {
            jouerMusiqueVictoire();
            displayVictoryDialog();
        }
        revalidate();
        repaint();

        // StringBuilder output = new StringBuilder();
        // for (int i = 0; i < gridSize; i++) {
        // for (int j = 0; j < gridSize; j++) {
        // Case caseActuelle = grille.getCase(i, j);
        // if (caseActuelle instanceof Mur) {
        // output.append("\u001B[47m\u001B[30m \u001B[0m");
        // } else {
        // Entity ent = caseActuelle.getEntity();
        // if (ent instanceof Hero) {
        // output.append("\u001B[32mH \u001B[0m");
        // } else if (ent instanceof Bloc) {
        // output.append("\u001B[31mB \u001B[0m");
        // } else if (caseActuelle.estCible()) {
        // output.append("\u001B[34m* \u001B[0m");
        // } else if (caseActuelle.estIce()) {
        // output.append("\u001B[36mI \u001B[0m");
        // } else {
        // output.append(" ");
        // }
        // }
        // }
        // output.append("\n");
        // }
        // output.append("--------------------\n");
        // System.out.println(output.toString());
    }

    private void updateGrille() {
        for (int i = 0; i < grille.getGridSize(); i++) {
            for (int j = 0; j < grille.getGridSize(); j++) {
                Case caseActuelle = grille.getCase(i, j);
                Entity entite = caseActuelle.getEntity();
                tabH[i][j].removeAll();
                tabH[i][j].setLayout(new BorderLayout());

                String imagePath = cheminImage(caseActuelle, entite);
                afficherIcone(tabH[i][j], imagePath);

                tabH[i][j].revalidate();
            }
        }
    }

    private String cheminImage(Case caseActuelle, Entity entite) {
        if (entite instanceof Hero) {
            if (caseActuelle.estIce()) {
                return "/Image/playerice.png";
            } else if (caseActuelle.estTeleporteur()) {
                return "/Image/playerOnPortal.png";
            }
            return "/Image/playerdef.png";
        } else if (entite instanceof Bloc) {
            return caseActuelle.estCible() ? "/Image/cratemarked.png" : "/Image/crate.png";
        } else if (caseActuelle.estCible()) {
            return "/Image/blankmarked.png";
        } else if (caseActuelle.estIce()) {
            return "/Image/ice.png";
        } else if (caseActuelle.estTeleporteur()) {
            return "/Image/portal.png";
        } else if (caseActuelle instanceof Mur) {
            return "/Image/Mur.jpg";
        } else {
            return "/Image/blank.png";
        }
    }

    private void afficherIcone(JPanel panel, String pathIcone) {
        ImageIcon icon = new ImageIcon(getClass().getResource(pathIcone));
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        JLabel label = new JLabel(icon);
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
    }

    public void jouerMusiqueVictoire() {
        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(getClass().getResource("/Musiques/victoire.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(0.1) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
            }
        } catch (Exception e) {
            System.err.println("Erreur fichier audio");
        }
    }

    private void displayVictoryDialog() {
        JDialog victoryDialog = new JDialog(this, "Menu", true);
        victoryDialog.setLayout(new BorderLayout());
        victoryDialog.setSize(300, 100);
        victoryDialog.setLocationRelativeTo(this);

        JLabel message = new JLabel("Victoire !", SwingConstants.CENTER);
        victoryDialog.add(message, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);

        JButton replayButton = new JButton("Rejouer");
        replayButton.addActionListener(e -> {
            resetFrame();
            victoryDialog.dispose();
        });
        buttonPanel.add(replayButton);

        JButton nextLevelButton = new JButton("Niveau Suivant");
        nextLevelButton.addActionListener(e -> {
            niveauSuivant();
            victoryDialog.dispose();
        });
        buttonPanel.add(nextLevelButton);

        victoryDialog.add(buttonPanel, BorderLayout.CENTER);

        victoryDialog.setVisible(true);
    }

}