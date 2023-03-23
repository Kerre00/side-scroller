package se.liu.kevri781;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SpriteAnimation {
    private BufferedImage spriteSheet = null;
    private int spriteWidth;
    private int spriteHeight;
    private int currentFrame;
    private int numFrames;
    private int animationDelay;
    private long lastUpdate;
    private boolean reverse;
    private String filePath;
    private GamePanel gamePanel;

    public SpriteAnimation(String filePath, int animationDelay) {
	try {
	    spriteSheet = ImageIO.read(new File(filePath));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	this.spriteWidth = spriteSheet.getHeight();
	this.spriteHeight = spriteSheet.getHeight();
	this.numFrames = spriteSheet.getWidth() / spriteWidth;
	this.animationDelay = animationDelay;
	this.filePath = filePath;
	System.out.println(filePath);

	if (filePath.contains("_left")) {
	    this.reverse = true;
	} else {
	    this.reverse = false;
	}

	if (reverse) {
	    currentFrame = numFrames - 1;
	} else {
	    currentFrame = 0;
	}

	lastUpdate = System.currentTimeMillis();
    }
    public BufferedImage getNextFrame() {
	if (playerDead()) {
	    int x = (currentFrame % (spriteSheet.getWidth() / spriteWidth)) * spriteWidth;
	    int y = (currentFrame / (spriteSheet.getWidth() / spriteWidth)) * spriteHeight;
	    return spriteSheet.getSubimage(x, y, spriteWidth, spriteHeight);
	}
	else if (reverse) {
	    if (System.currentTimeMillis() - lastUpdate >= animationDelay) {
		currentFrame--;
		if (isFinished()) {
		    currentFrame = numFrames - 1;
		}
		lastUpdate = System.currentTimeMillis();
	    }
	} else {
	    if (System.currentTimeMillis() - lastUpdate >= animationDelay) {
		currentFrame++;
		if (isFinished()) {
		    currentFrame = 0;
		}
		lastUpdate = System.currentTimeMillis();
	    }
	}

	int x = (currentFrame % (spriteSheet.getWidth() / spriteWidth)) * spriteWidth;
	int y = (currentFrame / (spriteSheet.getWidth() / spriteWidth)) * spriteHeight;
	return spriteSheet.getSubimage(x, y, spriteWidth, spriteHeight);
    }
    public boolean isFinished() {
	if (reverse)
	    return currentFrame == 0;
	else
	    return currentFrame == numFrames;
    }
    public boolean playerDead() {
	return Objects.equals(filePath, "resources/images/player/Death.png") && currentFrame >= numFrames - 1;
    }

    public BufferedImage getSpriteSheet() {
	return spriteSheet;
    }
}
