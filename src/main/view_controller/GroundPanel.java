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

//Panneau pour afficher le sol
public class GroundPanel extends JPanel {
	private GridGame gg;
	private Map<String, Image> groundImages;
	
	
	public GroundPanel(GridGame gg) {
		this.gg = gg;
		groundImages = new HashMap<String, Image>();
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //c'est le moment de draw chaques images du sol
        for(int x = 0; x < gg.getWidth(); x++) {
        	for(int y = 0; y < gg.getHeight(); y++) {
            	Cell cellXY= gg.getCell(new Point(x,y));
            	if(!groundImages.containsKey(cellXY.imagePath)) {
            		Image temp = new ImageIcon("asset/" + cellXY.imagePath).getImage();
            		groundImages.put(cellXY.imagePath, temp.getScaledInstance(Frame2.imageSize*Frame2.imageFactor, Frame2.imageSize*Frame2.imageFactor, Image.SCALE_DEFAULT));
            	}
            	Image img = groundImages.get(cellXY.imagePath);
            	int xOnScreen = x*Frame2.imageSize*Frame2.imageFactor;
            	int yOnScreen = y*Frame2.imageSize*Frame2.imageFactor;
            	g.drawImage(img, xOnScreen, yOnScreen, this);
            }
        }
    }
}
