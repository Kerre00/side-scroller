package se.liu.kevri781;

import java.awt.*;
import java.util.Random;

public class PlayerDrawable extends Player implements Drawable {
    private SpriteAnimation spriteAnimation;
    private int currentAnimationIndex;
    private Random rnd = new Random();
    private Player player;
    private String dir = null;
    public PlayerDrawable(int x, int y, int width, int height, Player player) {
        super(x, y, width, height);
        // Set the current animation index to 0 (the idle animation)
        this.currentAnimationIndex = 0;
        this.player = player;
        this.dir = "resources/images/player/";
        // Create a sprite animation object for the idle animation
        this.stringDirection = "_right";
        getAnimation(currentAnimationIndex);
    }

    @Override
    public void draw(Graphics g) {
        // Draw the next frame of the current player animation
        g.drawImage(spriteAnimation.getNextFrame(), x, y, width * getScale(), height * getScale(), null);
    }
    public void getAnimation(int animationIndex) {
        switch (currentAnimationIndex) {
                case 0:
                        // Idle animation
                        spriteAnimation = new SpriteAnimation(dir + "Idle" + stringDirection + ".png", 100);
                        break;
                case 1:
                        // Running animation
                        spriteAnimation = new SpriteAnimation(dir + "Run_left" + stringDirection + ".png", 100);
                        break;
                case 2:
                        // Right running animation
                        spriteAnimation = new SpriteAnimation(dir + "Run_right" + stringDirection + ".png", 100);
                        break;
                case 3:
                        // Jumping animation
                        spriteAnimation = new SpriteAnimation(dir + "Jump" + stringDirection + ".png", 100);
                        break;
                case 4:
                        // Death animation
                        spriteAnimation = new SpriteAnimation(dir + "Death" + stringDirection + ".png", 100);
                        break;
                case 5:
                        // Attack animation 1
                        spriteAnimation = new SpriteAnimation(dir + "Attack_1" + stringDirection + ".png", 100);
                        break;
                case 6:
                        // Attack animation 2
                        spriteAnimation = new SpriteAnimation(dir + "Attack_2" + stringDirection + ".png", 100);
                        break;
                case 7:
                        // Attack animation 3
                        spriteAnimation = new SpriteAnimation(dir + "Attack_3" + stringDirection + ".png", 100);
                        break;
                case 8:
                        // Fall animation
                        spriteAnimation = new SpriteAnimation(dir + "Fall" + stringDirection + ".png", 100);
                        break;
                case 9:
                        // Get hit animation
                        spriteAnimation = new SpriteAnimation(dir + "Get_Hit" + stringDirection + ".png", 100);
                        break;
            }
    }

    public void animationLogic() {
        if (player.isDead()) {
            this.currentAnimationIndex = 4;
        } else if (player.isJumping()) {
            this.currentAnimationIndex = 3;
        } else if (player.isAttacking()) {
            final int maxRnd = 8;
            final int minRnd = 5;
            int rndInt = rnd.nextInt(maxRnd - minRnd) + minRnd;
            this.currentAnimationIndex = rndInt;
        } else if (player.isHurt()) {
            this.currentAnimationIndex = 9;
        } else if (player.isFalling()) {
            this.currentAnimationIndex = 8;
        } else if (player.isMoving()) {
            System.out.println("Moving");
            this.currentAnimationIndex = 1;
        } else {
            this.currentAnimationIndex = 0;
        }
        getAnimation(currentAnimationIndex);
    }

    public void stopSpriteAnimation() {
        if (player.isDead()) {this.currentAnimationIndex = 4;}
        if (currentAnimationIndex < 4) {
            this.currentAnimationIndex = 0;
        }
        getAnimation(currentAnimationIndex);
    }
}