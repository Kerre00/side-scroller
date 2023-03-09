package se.liu.kevri781;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static se.liu.kevri781.GameViewer.screenSize;

public class SpriteAnimation {
    private BufferedImage spriteSheet = null;
    private int spriteWidth;
    private int spriteHeight;
    private int currentFrame;
    private int numFrames;
    private int animationDelay;
    private long lastUpdate;
    public SpriteAnimation(String filePath, int spriteWidth, int spriteHeight, int numFrames, int animationDelay) {
	try {
	    spriteSheet = ImageIO.read(new File(filePath));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	this.spriteWidth = spriteWidth;
	this.spriteHeight = spriteHeight;
	this.numFrames = numFrames;
	this.animationDelay = animationDelay;
	currentFrame = 0;
	lastUpdate = System.currentTimeMillis();
    }
    public BufferedImage getNextFrame() {
	if (System.currentTimeMillis() - lastUpdate >= animationDelay) {
	    currentFrame++;
	    if (currentFrame >= numFrames) {
		currentFrame = 0;
		System.out.println("Animation reset");
	    }
	    lastUpdate = System.currentTimeMillis();
	}
	int x = (currentFrame % (spriteSheet.getWidth() / spriteWidth)) * spriteWidth;
	int y = (currentFrame / (spriteSheet.getWidth() / spriteWidth)) * spriteHeight;
	return spriteSheet.getSubimage(x, y, spriteWidth, spriteHeight);
    }
    public Image getCurrentFrame() {
	return getNextFrame();
    }
}
