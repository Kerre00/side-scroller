package se.liu.kevri781;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The SpriteAnimation class is responsible for animating the different sprites in the game.
 * It takes a sprite sheet and splits it into individual frames.
 * It then returns the next frame in the animation.
 */
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
    public boolean freezeAnimation = false;
    private GameObjects object;

    public SpriteAnimation(GameObjects object, String filePath, int animationDelay) {
	try {
	    spriteSheet = ImageIO.read(new File(filePath)); // TODO Get resources check examples
	} catch (IOException e) {
	    e.printStackTrace();
	}
	this.spriteWidth = spriteSheet.getHeight();
	this.spriteHeight = spriteSheet.getHeight();
	this.numFrames = spriteSheet.getWidth() / spriteWidth;
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
	int x = (currentFrame % (spriteSheet.getWidth() / spriteWidth)) * spriteWidth;
	int y = (currentFrame / (spriteSheet.getWidth() / spriteWidth)) * spriteHeight;
	return spriteSheet.getSubimage(x, y, spriteWidth, spriteHeight);
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
		GamePanel.enemies.remove(object);
	    } if (filePath.contains("Attack")) {
		((Enemy) object).setAttacking(false);
	    }
	}
    }
}
