package se.liu.kevri781;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.ComponentEvent;

import static se.liu.kevri781.GameViewer.screenSize;
import static se.liu.kevri781.GameViewer.frameSize;

public class GameBackground {

    private ArrayList<Image> bgImages = new ArrayList<>();
    private ArrayList<Image> bgImages2 = new ArrayList<>();
    private ArrayList<Integer> bgXPos = new ArrayList<>();
    private ArrayList<Integer> bgXpos2 = new ArrayList<>();
    private ArrayList<Integer> bgYpos = new ArrayList<>();
    private ArrayList<Integer> bgYpos2 = new ArrayList<>();
    private ArrayList<Integer> bgSpeed = new ArrayList<>();
    private ArrayList<Integer> bgSpeed2 = new ArrayList<>();
    private int numLayers;

    public GameBackground() {
	this.numLayers = new File("resources/images/gamebackgrounds").list().length;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	for (int i = 0; i < numLayers; i++) {

	    String imagePath = "resources/images/gamebackgrounds" + "/layer" + i + ".png";

	    bgImages.add(new ImageIcon(imagePath).getImage());
	    bgImages2.add(new ImageIcon(imagePath).getImage());

	    bgXPos.add(0);
	    bgXpos2.add(928);

	    bgYpos.add(- (int) (screenSize.height/1.05));
	    bgYpos2.add(- (int) (screenSize.height/1.05));

	    bgSpeed.add(0);
	    bgSpeed2.add(0);
	}
    }

    public void componentResized(ComponentEvent e) {
	// Resize the background images
	for (int i = 0; i < numLayers; i++) {
	    bgImages.set(i, bgImages.get(i).getScaledInstance((int) (screenSize.width * 0.66) * 2, screenSize.height * 2, Image.SCALE_SMOOTH));
	    bgImages2.set(i, bgImages2.get(i).getScaledInstance((int) (screenSize.width * 0.66) * 2, screenSize.height * 2, Image.SCALE_SMOOTH));
	}
    }

    public void draw(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	for (int i = 0; i < numLayers - 1; i++) {
	    g2d.drawImage(bgImages.get(i), bgXPos.get(i), bgYpos.get(i), null); // - 950
	    g2d.drawImage(bgImages2.get(i), bgXpos2.get(i), bgYpos2.get(i), null); // - 950
	}
    }

    public void update() {
	for (int i = 0; i < numLayers; i++) {
	    int width = bgImages.get(i).getWidth(null);
	    bgXPos.set(i, bgXPos.get(i) - bgSpeed.get(i));
	    bgXpos2.set(i, bgXPos.get(i) + width - bgSpeed2.get(i));
	    if (bgXPos.get(i) < -width) {
		bgXPos.set(i, width);
	    } else if (bgXPos.get(i) > width) {
		bgXPos.set(i, -width);
	    }
	    if (bgXPos.get(i) > 0) {
		bgXpos2.set(i, bgXPos.get(i) - width);
	    } else if (bgXPos.get(i) < 0) {
		bgXpos2.set(i, bgXPos.get(i) + width);
	    }
	}
    }
    public void moveBackground(final int speed, PlayerAction dir) {
	for (int i = 0; i < numLayers; i++) {
	    switch (dir) {
		case RUN_LEFT:
		    bgSpeed.set(i, -speed * i / 5);
		    bgSpeed2.set(i, -speed * i / 5);
		    break;
		case RUN_RIGHT:
		    bgSpeed.set(i, speed * i / 5);
		    bgSpeed2.set(i, speed * i / 5);
		    break;
	    }
	}
    }
    public void drawForeGround(Graphics g) {
	int lastLayerIndex = numLayers - 1;
	Image lastLayerImage = bgImages.get(lastLayerIndex);
	Image lastLayerImage2 = bgImages2.get(lastLayerIndex);
	int x1 = bgXPos.get(lastLayerIndex);
	int y1 = bgYpos.get(lastLayerIndex);
	g.drawImage(lastLayerImage, x1, y1, null);
	int x2 = bgXpos2.get(lastLayerIndex);
	int y2 = bgYpos2.get(lastLayerIndex);
	g.drawImage(lastLayerImage, x2, y2, null);
    }

    public int getGroundSpeed() {
	return bgSpeed.get(bgSpeed.size() - 2);
    }
    public void stopBackground() {
	for (int i = 0; i < numLayers; i++) {
	    bgSpeed.set(i, 0);
	    bgSpeed2.set(i, 0);
	}
    }
}

