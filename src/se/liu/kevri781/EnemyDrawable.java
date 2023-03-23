package se.liu.kevri781;

import java.awt.*;
import java.util.Random;

public class EnemyDrawable extends Enemy implements Drawable {
//    Represents the player character that can be drawn on the screen.
//    Implements the Drawable interface to provide a method for drawing the player.
    private SpriteAnimation spriteAnimation;
    private int currentAnimationIndex;
    private CharacterType enemyType;
    private String dir = null;
    private Random rnd = new Random();
    private Enemy enemy;
    public EnemyDrawable(final int x, final int y, final int width, final int height, Enemy enemy) {
        super(enemy.getEnemyType(), x, y, width, height);
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
//        System.out.println(dir + "Idle" + stringDirection + ".png");
        switch (currentAnimationIndex) {
            case 0:
                // Idle animation
                spriteAnimation = new SpriteAnimation(dir + "Idle" + stringDirection + ".png", 100);
                break;
            case 1:
                // Left running animation
                spriteAnimation = new SpriteAnimation(dir + "Walk" + stringDirection + ".png", 100);
                break;
            case 2:
                // Right running animation
                spriteAnimation = new SpriteAnimation(dir + "Run" + stringDirection + ".png", 100);
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
        if (enemy.getHealth() <= 0) {
            currentAnimationIndex = 4;
        } else if (enemy.isAttacking()) {
            currentAnimationIndex = 5;
        } else if (enemy.isJumping()) {
            currentAnimationIndex = 3;
        } else if (enemy.isFalling()) {
            currentAnimationIndex = 8;
        } else if (enemy.isMovingLeft()) {
            this.setDirection(Direction.LEFT);
            currentAnimationIndex = 1;
        } else if (enemy.isMovingRight()) {
            this.setDirection(Direction.RIGHT);
            currentAnimationIndex = 1;
        } else if (enemy.isGettingHit()) {
            currentAnimationIndex = 9;
        } else {
            currentAnimationIndex = 0;
        }
        getAnimation(currentAnimationIndex);
    }
    public int getScale() {
    	return scale;
    }
}
