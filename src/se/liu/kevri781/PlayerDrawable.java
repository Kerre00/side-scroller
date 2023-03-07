package se.liu.kevri781;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PlayerDrawable extends Player implements Drawable {
    private SpriteAnimation spriteAnimation;
    private int currentAnimationIndex;
    private Random rnd = new Random();
    private int minRnd = 5;
    private int maxRnd = 7;

    public PlayerDrawable(int x, int y, int width, int height) {
        super(x, y, width, height);
        // Set the current animation index to 0 (the idle animation)
        this.spriteAnimation = new SpriteAnimation("resources/images/player/Idle.png", 135, 135, 10, 100);
        currentAnimationIndex = 0;

        // Create a sprite animation object for the idle animation
        getAnimation(currentAnimationIndex);
    }

    @Override
    public void draw(Graphics g) {
        // Draw the current frame of the current animation
        g.drawImage(spriteAnimation.getCurrentFrame(), x, y, width * 5, height * 5, null);
    }
    @Override
    public void collidesWith() {
        // Check for collision with other objects
        // ...

        // If collision occurs, take appropriate action
        // ...
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            // Move the player left (left running animation)
            System.out.println("hello");
            currentAnimationIndex = 1;
            getAnimation(currentAnimationIndex);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            // Move the player right (right running animation)
            currentAnimationIndex = 2;
            getAnimation(currentAnimationIndex);
        } else if (keyCode == KeyEvent.VK_UP) {
            // Move the player up (up running animation)
            currentAnimationIndex = 3;
            getAnimation(currentAnimationIndex);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            // Move the player down (down running animation)
            currentAnimationIndex = 4;
            getAnimation(currentAnimationIndex);
        } else if (keyCode == KeyEvent.VK_SPACE) {
            // Use a special ability
            getAnimation(currentAnimationIndex);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Stop the player's movement when key is released
        // Also stop the running animations and set the player to the standing animation
        currentAnimationIndex = 0;
        // ...
    }

    public void getAnimation(int animationIndex) {
        switch (currentAnimationIndex) {
                case 0:
                        // Idle animation
                        spriteAnimation = new SpriteAnimation("resources/images/player/Idle.png", 135, 135, 10, 100);
                        break;
                case 1:
                        // Left running animation
                        spriteAnimation = new SpriteAnimation("resources/images/player/Run_left.png", 135, 135, 6, 100);
                        break;
                case 2:
                        // Right running animation
                        spriteAnimation = new SpriteAnimation("resources/images/player/Run_right.png", 135, 135, 6, 100);
                        break;
                case 3:
                        // Jumping animation
                        spriteAnimation = new SpriteAnimation("resources/images/player/Jump.png", 135, 135, 2, 100);
                        break;
                case 4:
                        // Death animation
                        spriteAnimation = new SpriteAnimation("resources/images/player/Death.png", 135, 135, 10, 100);
                        break;
                case 5:
                        // Attack animation
                        spriteAnimation = new SpriteAnimation("resources/images/player/Attack1.png", 135, 135, 10, 100);
                        break;
                case 6:
                        // Attack animation
                        spriteAnimation = new SpriteAnimation("resources/images/player/Attack2.png", 135, 135, 10, 100);
                        break;
                case 7:
                        // Attack animation
                        spriteAnimation = new SpriteAnimation("resources/images/player/Attack3.png", 135, 135, 10, 100);
                        break;
        }

    }

    public void setSpriteAnimation(Controls dir) {
        switch (dir) {
//		case UP:
//		    bgSpeed.set(i, speed);
//		    bgSpeed2.set(i, speed);
//		    break;
            case ATTACK:
                this.currentAnimationIndex = rnd.nextInt(maxRnd - minRnd) + minRnd;
                break;
            case RUN_LEFT:
                this.currentAnimationIndex = 1;
                break;
            case RUN_RIGHT:
                this.currentAnimationIndex = 2;
                break;

        }
        getAnimation(currentAnimationIndex);
    }
    public void setSpriteAnimation() {
        this.currentAnimationIndex = 0;
        getAnimation(currentAnimationIndex);
    }

    public void updateAnimation() {
        // Update the current animation frame based on the current animation index
        spriteAnimation.getNextFrame();
    }


}