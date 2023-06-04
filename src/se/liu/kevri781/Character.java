package se.liu.kevri781;

/** Represents a character in the game.
 *  Used to create characters in the game such as the player and enemies.
 *  Extends the GameObjects class to inherit the methods for collision detection.
 *  Also contains methods for movement, attacking and taking damage, as well as
 *  getters and setters for the character's stats.
 */
public abstract class Character extends GameObjects
{
    protected int health;
    protected int maxHealth;
    protected int level;
    protected int damage;
    protected int speed;
    protected int jumpHeight;
    protected int attackReach;
    protected boolean dead = false;
    protected boolean attacking = false;
    protected boolean gettingHit = false;
    protected boolean recovering = false;
    protected long recoverTimer = 0;
    protected long maxRecoveryTime = 1000;
    protected String stringDirection = "_right";
    protected Character(final int x, final int y, final int width, final int height) {
        super(x, y, width, height, 0);
        this.level = 1;
        this.damage = 1;
        this.speed = 1;
        this.maxHealth = 3;
        this.health = maxHealth;
        this.attackReach = 100;
        this.jumpHeight = 10;
    }

    // ----------------------------------
    // -------Methods for movement-------
    // ----------------------------------
    protected void moveLeft() {
        setDirection(Direction.LEFT);
        velocityX = -speed;
    }
    protected void moveRight() {
        setDirection(Direction.RIGHT);
        velocityX = speed;
    }
    protected void jump() {
        if (!isAboveGround()) {
            velocityY -= jumpHeight;
        }
    }
    protected void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }
    protected void stop() {
        velocityX = 0;
    }
    protected void setDirection(Direction direction) {
        switch (direction) {
            case LEFT:
                stringDirection = "_left";
                break;
            case RIGHT:
                stringDirection = "_right";
                break;
        }
    }
    protected boolean isMovingLeft() {
        if (velocityX < 0) {
            return true;
        } else {
            return false;
        }
    }
    protected boolean isMovingRight() {
        if (velocityX > 0) {
            return true;
        } else {
            return false;
        }
    }
    protected boolean isJumping() {
        if (velocityY < 0) {
            return true;
        } else {
            return false;
        }
    }
    protected boolean isFalling() {
        if (velocityY > 0) {
            return true;
        } else {
            return false;
        }
    }
    protected boolean isAboveGround() {
        if (y < groundCoordSpriteOffset) {
            return true;
        } else {
            return false;
        }
    }

    // -----------------------------------
    // -------Methods for attacking-------
    // -----------------------------------

    protected void attack() {
        attacking = true;
    }
    protected void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
    protected boolean isAttacking() {
        return attacking;
    }
    protected void setAttackReach(int attackReach) {
        this.attackReach = attackReach;
    }
    protected void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }
    protected void setSpeed(int speed) {
        this.speed = speed;
    }
    protected void setDamage(int damage) {
        this.damage = damage;
    }
    protected int getDamage() {
        return damage;
    }
    protected int getHealth() {
        return health;
    }
    protected void setDead(boolean dead) {
        this.dead = dead;
    }
    protected boolean isDead() {
        return dead;
    }
    protected void reduceHealth(int damage) {
        health -= damage;
        setGettingHit(true);
    }
    protected boolean isGettingHit() {
        return gettingHit;
    }
    protected void setGettingHit(boolean gettingHit) {
        this.gettingHit = gettingHit;
    }
    protected void setMaxRecoveryTime(long maxRecoveryTime) {
        this.maxRecoveryTime = maxRecoveryTime;
    }

    protected boolean isRecovering() {
        return recovering;
    }

    protected long getRecoverTimer() {
        return recoverTimer;
    }

    protected long setRecoverTimer(long recoverTimer) {
        return this.recoverTimer = recoverTimer;
    }

    protected void setRecovering(boolean recovering) {
        this.recovering = recovering;
    }

    protected void resetRecoveryTimer() {
        recoverTimer = 0;
    }

    protected int getAttackReach() {
        return attackReach;
    }
}
