package se.liu.kevri781;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * The SpriteAnimation class is responsible for animating the different sprites in the game.
 * It takes a sprite sheet and splits it into individual frames.
 * It then returns the next frame in the animation.
 */
public class SpriteAnimation {
    private BufferedImage spriteSheet = null;
    private boolean freezeAnimation = false;
    private int spriteWidth;
    private int spriteHeight;
    private int currentFrame;
    private int numFrames;
    private int animationDelay;
    private long lastUpdate;
    private boolean reverse;
    private String filePath;
    private GameObjects object;
    public SpriteAnimation(GameObjects object, String filePath, int animationDelay) {

	try {
	    URL spriteSheetURL = ClassLoader.getSystemResource(filePath);
	    this.spriteSheet = ImageIO.read(spriteSheetURL);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	if (spriteSheet != null) {
	    this.spriteWidth = spriteSheet.getHeight();
	    this.spriteHeight = spriteSheet.getHeight();
	    this.numFrames = spriteSheet.getWidth() / spriteWidth;
	} else {
	    this.spriteWidth = 0;
	    this.spriteHeight = 0;
	    this.numFrames = 0;
	}
	this.animationDelay = animationDelay;
	this.filePath = filePath;
	this.object = object;

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
	 if (!freezeAnimation) {
	     if (reverse) {
		if (System.currentTimeMillis() - lastUpdate >= animationDelay) {
		    currentFrame--;
		    if (isFinished()) {
			currentFrame = numFrames - 1;
			stopPlayerAnimation();
			stopEnemyAnimation();
		    }
		    lastUpdate = System.currentTimeMillis();
		}
	    } else {
		if (System.currentTimeMillis() - lastUpdate >= animationDelay) {
		    currentFrame++;
		    if (isFinished()) {
			currentFrame = 0;
			stopPlayerAnimation();
			stopEnemyAnimation();
		    }
		    lastUpdate = System.currentTimeMillis();
		}
	    }
    	}
	if (spriteSheet != null) {
	    int x = (currentFrame % (spriteSheet.getWidth() / spriteWidth)) * spriteWidth;
	    int y = (currentFrame / (spriteSheet.getWidth() / spriteWidth)) * spriteHeight;
	    return spriteSheet.getSubimage(x, y, spriteWidth, spriteHeight);
	}
	return null;
    }
    private boolean isFinished() {
	if (reverse) {
	    return this.currentFrame == 0;}
	else {
	    return this.currentFrame == this.numFrames - 1;
	}
    }
    private void setFreezeAnimation(boolean freezeAnimation) {
	this.freezeAnimation = freezeAnimation;
    }
    private void stopPlayerAnimation() {
	if (object instanceof Player) {
	    if (filePath.contains("Attack")) {
		((Player) object).setAttacking(false);
	    } if (((Player) object).isGettingHit()) {
		((Player) object).setGettingHit(false);
		((Player) object).stop();
	    } if (filePath.contains("Death")) {
		setFreezeAnimation(true);
	    }
	}
    }
    private void stopEnemyAnimation() {
	if (object instanceof Enemy) {
	    if (((Enemy) object).isGettingHit()) {
		((Enemy) object).setGettingHit(false);
		((Enemy) object).stop();
	    } if (filePath.contains("Death")) {
		setFreezeAnimation(true);
	    } if (filePath.contains("Attack")) {
		((Enemy) object).setAttacking(false);
	    }
	}
    }
}
