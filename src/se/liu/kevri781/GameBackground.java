package se.liu.kevri781;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.ComponentEvent;

import static se.liu.kevri781.PanelManager.SCREEN_SIZE;
import static se.liu.kevri781.PanelManager.frameSize;

/**
 * The GameBackground class represents the background of the game.
 * It is responsible for drawing the background and updating the background,
 * resizing of the background. It is used by the GamePanel class.
 */
public class GameBackground implements Drawable {

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
	    bgXpos2.add(bgImages.get(i).getWidth(null));

	    bgYpos.add(- (int) (screenSize.height/1.05));
	    bgYpos2.add(- (int) (screenSize.height/1.05));

	    bgSpeed.add(0);
	    bgSpeed2.add(0);
	}
    }

    public void componentResized(ComponentEvent e) {
	// Resize the background images
	for (int i = 0; i < numLayers; i++) {
	    bgImages.set(i, bgImages.get(i).getScaledInstance((int) (SCREEN_SIZE.width * 0.66) * 2, SCREEN_SIZE.height * 2, Image.SCALE_SMOOTH));
	    bgImages2.set(i, bgImages2.get(i).getScaledInstance((int) (SCREEN_SIZE.width * 0.66) * 2, SCREEN_SIZE.height * 2, Image.SCALE_SMOOTH));
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
    public void moveBackground(int speed) {
	speed /= 5;
	for (int i = 0; i < numLayers; i++) {
	    bgSpeed.set(i, speed * i);
	    bgSpeed2.set(i, speed * i);
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

}

