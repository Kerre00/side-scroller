package se.liu.kevri781;

import java.awt.*;
import java.util.Random;

/**
 * Responsible for drawing the player.
 * Uses the Player class to get the player's position and size.
 * Uses the SpriteAnimation class to get the player's current animation image.
 * Implements the Drawable interface to be able to draw the player.
 * Extends the Player class to be able to access the player's position and size.
 */
public class PlayerDrawable extends Player implements Drawable {
    private SpriteAnimation spriteAnimation = null;
    private int currentAnimationIndex;
    private static Random rnd = new Random();
    private Player player;
    private String dir;
    public PlayerDrawable(Player player) {
        super(player.getX(), player.getY(), player.getWidth(), player.getHeight(), null, player.getGameProgress(),
              player.groundLevel);
        // Set the current animation index to 0 (the idle animation)
        this.currentAnimationIndex = 0;
        this.player = player;
        this.dir = "images/player/";
        // Create a sprite animation object for the idle animation
        getAnimation();
    }

    @Override
    public void draw(Graphics g) {
        // Draw the next frame of the current player animation
//        if (spriteAnimation.freezeAnimation) { // TODO: Fix freeze animation
//            return;
//        }
        g.drawImage(spriteAnimation.getNextFrame(), player.x, player.y, player.getScaledWidth(), player.getScaledHeight(), null);
    }
    private void getAnimation() {
        final int animationDelay = 100;
        switch (currentAnimationIndex) {
                case 0:
                        // Idle animation
                        spriteAnimation = new SpriteAnimation(player, dir + "Idle" + stringDirection + ".png", animationDelay);
                        break;
                case 1:
                        // Running animation
                        spriteAnimation = new SpriteAnimation(player, dir + "Run" + stringDirection + ".png", animationDelay);
                        break;
                case 2:
                        // TODO: Add crouch animation, currently no sprite sheet for it
                        break;
                case 3:
                        // Jumping animation
                        spriteAnimation = new SpriteAnimation(player, dir + "Jump" + stringDirection + ".png", animationDelay);
                        break;
                case 4:
                        // Death animation
                        spriteAnimation = new SpriteAnimation(player, dir + "Death" + stringDirection + ".png", animationDelay);
                        break;
                case 5:
                        // Attack animation 1
                        spriteAnimation = new SpriteAnimation(player, dir + "Attack_1" + stringDirection + ".png", animationDelay);
                        break;
                case 6:
                        // Attack animation 2
                        spriteAnimation = new SpriteAnimation(player, dir + "Attack_2" + stringDirection + ".png", animationDelay);
                        break;
                case 7:
                        // Attack animation 3
                        spriteAnimation = new SpriteAnimation(player, dir + "Attack_3" + stringDirection + ".png", animationDelay);
                        break;
                case 8:
                        // Fall animation
                        spriteAnimation = new SpriteAnimation(player, dir + "Fall" + stringDirection + ".png", animationDelay);
                        break;
                case 9:
                        // Get hit animation
                        spriteAnimation = new SpriteAnimation(player, dir + "Get_Hit" + stringDirection + ".png", animationDelay);
                        break;
            }
    }

    public void animationLogic(GameBackground gameBackground) {
        int lastAnimationIndex = currentAnimationIndex;
        if (player.isDead()) {
            this.currentAnimationIndex = 4;
        } else if (player.isJumping()) {
            this.currentAnimationIndex = 3;
        } else if (player.isAttacking()) {
            final int maxRnd = 8;
            final int minRnd = 5;
            int rndInt = rnd.nextInt(maxRnd - minRnd) + minRnd;
            this.currentAnimationIndex = rndInt;
        } else if (player.isGettingHit()) {
            this.currentAnimationIndex = 9;
        } else if (player.isFalling()) {
            this.currentAnimationIndex = 8;
        } else if (gameBackground.getGroundSpeed() != 0) {
            this.currentAnimationIndex = 1;
        } else {
            this.currentAnimationIndex = 0;
        } if (lastAnimationIndex != currentAnimationIndex) {
            if (!player.isAttacking()) {
                getAnimation();
            }
        }
    }

    public void stopSpriteAnimation() {
        if (currentAnimationIndex < 4) {
            this.currentAnimationIndex = 0;
        }
        getAnimation();
    }
}