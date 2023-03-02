package se.liu.kevri781;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameBackground extends JPanel implements Runnable {

    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<Integer> x = new ArrayList<>();
    private ArrayList<Integer> y = new ArrayList<>();
    private ArrayList<Integer> speed = new ArrayList<>();
    private ArrayList<Image> images2 = new ArrayList<>();
    private ArrayList<Integer> x2 = new ArrayList<>();
    private ArrayList<Integer> y2 = new ArrayList<>();
    private ArrayList<Integer> speed2 = new ArrayList<>();
    private int numLayers;
    private boolean imagesLoaded = false;

    public GameBackground(int numLayers) {
	this.numLayers = numLayers;
	for (int i = 0; i < numLayers; i++) {
	    images.add(null);
	    images2.add(null);
	    x.add(0);
	    x2.add(881);
	    y.add(0);
	    y2.add(0);
	    speed.add(i/4);
	    speed2.add(i/4);
	}
	Thread t = new Thread(this);


	// Wait for all images to be loaded before starting the game loop
	MediaTracker tracker = new MediaTracker(this);
	for (int i = 0; i < numLayers; i++) {
	    String imagePath = "layer" + i + ".png";
	    images.set(i, new ImageIcon(imagePath).getImage());
	    images2.set(i, new ImageIcon(imagePath).getImage());
	    tracker.addImage(images.get(i), i);
	    tracker.addImage(images2.get(i), i);
	}
	try {
	    tracker.waitForAll();
	    imagesLoaded = true;
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	t.start();
    }

    public void setImagesPath(String directory) {
	for (int i = 0; i < numLayers; i++) {
	    String imagePath = directory + "/layer" + i + ".png";
	    images.set(i, new ImageIcon(imagePath).getImage());
	    images2.set(i, new ImageIcon(imagePath).getImage());
	}
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;
	for (int i = 0; i < numLayers; i++) {
	    g2d.drawImage(images.get(i), x.get(i), y.get(i), null);
	    g2d.drawImage(images2.get(i), x2.get(i), y2.get(i), null);
	}
    }

    public void run() {
	while (true) {
	    for (int i = 0; i < numLayers; i++) {
		int width = images.get(i).getWidth(null);
		x.set(i, x.get(i) - speed.get(i));
		if (x.get(i) < -width) {
		    x.set(i, x.get(i) + width * 2);
		}
		int width2 = images2.get(i).getWidth(null);
		x2.set(i, x2.get(i) - speed2.get(i));
		if (x2.get(i) < -width2 + 2) {
		    x2.set(i, x2.get(i) + width2 * 2);
		}
	    }
	    repaint();
	    try {
		Thread.sleep(10);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }


}

//	if (x[i] < -images[i].getWidth(null)) {
//	x[i] += images[i].getWidth(null) * 2;
