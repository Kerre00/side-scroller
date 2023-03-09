package se.liu.kevri781;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PlayerDrawable extends Player implements Drawable {
    private SpriteAnimation spriteAnimation;
    private int currentAnimationIndex;
    private Random rnd = new Random();

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
                        spriteAnimation = new SpriteAnimation("resources/images/player/Death.png", 135, 135, 9, 100);
                        break;
                case 5:
                        // Attack animation 1
                        spriteAnimation = new SpriteAnimation("resources/images/player/Attack1.png", 135, 135, 5, 100);
                        break;
                case 6:
                        // Attack animation 2
                        spriteAnimation = new SpriteAnimation("resources/images/player/Attack2.png", 135, 135, 5, 100);
                        break;
                case 7:
                        // Attack animation 3
                        spriteAnimation = new SpriteAnimation("resources/images/player/Attack3.png", 135, 135, 5, 100);
                        break;
                case 8:
                        // Fall animation
                        spriteAnimation = new SpriteAnimation("resources/images/player/Fall.png", 135, 135, 2, 100);
                        break;
                case 9:
                        // Get hit animation
                        spriteAnimation = new SpriteAnimation("resources/images/player/Get_Hit.png", 135, 135, 3, 100);
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
                final int maxRnd = 8;
                final int minRnd = 5;
                int rndInt = rnd.nextInt(maxRnd - minRnd) + minRnd;
                System.out.println(rndInt);
                this.currentAnimationIndex = rndInt;
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

//    public void updateAnimation() {
//        // Update the current animation frame based on the current animation index
//        spriteAnimation.getNextFrame();
//    }


}