package main.view_controller;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.model.grid.GridGame;
import main.model.grid.Point;
import main.model.grid.cell.Cell;

//Panneau pour afficher les entités
public class EntityPanel extends JPanel {
	
	private GridGame gg;
	private Map<String, Image> entityImages;
	
	public EntityPanel(GridGame gg) {
		this.gg = gg;
		entityImages = new HashMap<String, Image>();
	}
	
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        //c'est le moment de draw chaques images du sol
        //g.drawImage(groundImage, 0, 0, this);
        for(int x = 0; x < gg.getWidth; x++) {
        	for(int y = 0; y < gg.getHeight; y++) {
            	Cell cellXY= gg.getCell(new Point(x,y));
            	if(!entityImages.containsKey(cellXY.imagePath)) {
            		Image temp = new ImageIcon("asset/" + cellXY.imagePath).getImage();
            		entityImages.put(cellXY.imagePath, temp.getScaledInstance(Frame2.imageSize, Frame2.imageSize, Image.SCALE_DEFAULT));
            	}
            	Image img = entityImages.get(cellXY.imagePath);
            	int xOnSceen = x*Frame2.imageSize;
            	int yOnSceen = y*Frame2.imageSize;
            	g.drawImage(img, x, y, this);
            }
        }
    }

    @Override
    public boolean isOpaque() {
        return false; // Pour rendre le panneau transparent
    }
}
