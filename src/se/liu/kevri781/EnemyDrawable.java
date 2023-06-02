package se.liu.kevri781;

import java.awt.*;
import java.util.Random;

/**
 * Represents an enemy character that can be drawn on the screen.
 * Implements the Drawable interface to provide a method for drawing the enemy.
 * Extends the Enemy class to inherit the enemy's properties.
 * Uses spriteAnimation to animate the enemy.
 */
public class EnemyDrawable extends Enemy implements Drawable {
//    Represents the player character that can be drawn on the screen.
//    Implements the Drawable interface to provide a method for drawing the player.
    private SpriteAnimation spriteAnimation;
    private int currentAnimationIndex;
    private CharacterType enemyType;
    private String dir = null;
    private Random rnd = new Random();
    private Enemy enemy;
    public EnemyDrawable(Enemy enemy) {
        super(enemy.getEnemyType(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        this.enemy = enemy;
        this.enemyType = enemy.getEnemyType();
        // Set the current animation index to 0 (the idle animation)
        switch (enemyType) {
            case FIRE_WIZARD:
                this.dir = "resources/images/enemy/Fire_Wizard/";
                break;
            case LIGHTNING_MAGE:
                this.dir = "resources/images/enemy/Lightning_Mage/";
                break;
            case SKELETON_ARCHER:
                this.dir = "resources/images/enemy/Skeleton_Archer/";
                break;
            case SKELETON_SPEARMAN:
                this.dir = "resources/images/enemy/Skeleton_Spearman/";
                break;
            case SKELETON_WARRIOR:
                this.dir = "resources/images/enemy/Skeleton_Warrior/";
                break;
            case WANDERER_MAGICIAN:
                this.dir = "resources/images/enemy/Wanderer_Magician/";
                break;
        }
        currentAnimationIndex = 0;
        // Create a sprite animation object for the idle animation
        getAnimation(currentAnimationIndex);
    }

    @Override
    public void draw(Graphics g) {
    	// Draws the enemy character on the screen
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(spriteAnimation.getNextFrame(), enemy.getX(), enemy.getY(), enemy.getWidth() * getScale(), enemy.getHeight() * getScale(), null);
    }

    public void getAnimation(int animationIndex) {
        this.stringDirection = enemy.stringDirection;
        switch (currentAnimationIndex) {
            case 0:
                // Idle animation
                spriteAnimation = new SpriteAnimation(enemy,dir + "Idle" + stringDirection + ".png", 100);
                break;
            case 1:
                // Left running animation
                spriteAnimation = new SpriteAnimation(enemy,dir + "Walk" + stringDirection + ".png", 100);
                break;
            case 2:
                // Right running animation
                spriteAnimation = new SpriteAnimation(enemy,dir + "Run" + stringDirection + ".png", 100);
                break;
            case 3:
                // Jumping animation TODO: Add jumping animation
//                spriteAnimation = new SpriteAnimation(enemy,dir + "Jump" + stringDirection + ".png", 100);
                break;
            case 4:
                // Death animation
                spriteAnimation = new SpriteAnimation(enemy,dir + "Dead" + stringDirection + ".png", 100);
                break;
            case 5:
                // Attack animation 1
                spriteAnimation = new SpriteAnimation(enemy,dir + "Attack_1" + stringDirection + ".png", 100);
                break;
            case 6:
                // Attack animation 2
                spriteAnimation = new SpriteAnimation(enemy,dir + "Attack_2" + stringDirection + ".png", 100);
                break;
            case 7:
                // Attack animation 3
                spriteAnimation = new SpriteAnimation(enemy,dir + "Attack_3" + stringDirection + ".png", 100);
                break;
            case 8:
                // Fall animation TODO: Add fall animation
//                spriteAnimation = new SpriteAnimation(enemy,dir + "Fall" + stringDirection + ".png", 100);
                break;
            case 9:
                // Get hit animation
                spriteAnimation = new SpriteAnimation(enemy,dir + "Hurt" + stringDirection + ".png", 100);
                break;
        }
    }
    public void animationLogic() {
        int lastAnimationIndex = currentAnimationIndex;
        String lastDirection = enemy.stringDirection;
        if (enemy.isDead()) {
            currentAnimationIndex = 4;
        } else if (enemy.isAttacking()) {
            currentAnimationIndex = 5;
        } else if (enemy.isGettingHit()) {
            currentAnimationIndex = 9;
        } else if (enemy.isJumping()) {
            currentAnimationIndex = 3;
        } else if (enemy.isFalling()) {
            currentAnimationIndex = 8;
        } else if (enemy.isMovingLeft()) {
            currentAnimationIndex = 1;
        } else if (enemy.isMovingRight()) {
            currentAnimationIndex = 1;
        } else {
            currentAnimationIndex = 0;
        }

        if (lastAnimationIndex != currentAnimationIndex || !lastDirection.equals(stringDirection)) {
            getAnimation(currentAnimationIndex);
        }
    }
}
