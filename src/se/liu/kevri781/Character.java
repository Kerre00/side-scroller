package se.liu.kevri781;

public abstract class Character extends GameObjects
{
    //    Represents a character in the game, which includes the player and enemies.
    //    Contains properties such as health, attack power, and movement speed.
    //    Defines methods for movement, attacking, and taking damage.
    //    Also contains methods for getting and setting the character's stats.
    public int health;
    public int maxHealth;
    public boolean dead;
    public int level;
    public int damage;
    public int speed;
    public int jumpHeight;
    public int attackReach;
    private boolean attacking = false;
    private boolean isGettingHit = false;
    public long recoverTimer = 0;
    public boolean recovering = false;
    public long invincibilityTimer = 1000;
    public String stringDirection = "_right";

    public Character(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.level = 1;
        this.damage = 1;
        this.speed = 1;
        this.maxHealth = 3;
        this.health = maxHealth;
        this.dead = false;
        this.attackReach = 100;
        this.jumpHeight = 10;
    }

    // ----------------------------------
    // -------Methods for movement-------
    // ----------------------------------
    public void moveLeft() {
        setDirection(Direction.LEFT);
        velocityX = -speed;
    }

    public void moveRight() {
        setDirection(Direction.RIGHT);
        velocityX = speed;
    }

    public void jump() {
        if (!isAboveGround()) {
            velocityY -= jumpHeight;
        }
    }
    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public void stop() {
        velocityX = 0;
    }

    public String getDirection() {
        return stringDirection;
    }

    public void setDirection(Direction direction) {
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

    protected boolean isMoving() {
        if (velocityX != 0 || velocityY != 0) {
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
        if (y < groundCoord) {
            return true;
        } else {
            return false;
        }
    }

    // -----------------------------------
    // -------Methods for attacking-------
    // -----------------------------------

    public void attack() {
        attacking = true;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttackReach(int attackReach) {
        this.attackReach = attackReach;
    }

    public void stopAttack() {
        attacking = false;
    }

    public int getAttackReach() {
        return attackReach;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }


    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }

    public void reduceHealth(int damage) {
        health -= damage;
        setGettingHit(true);
//        knockBack();
    }
    protected boolean isGettingHit() {
        return isGettingHit;
    }
    protected void setGettingHit(boolean gettingHit) {
        isGettingHit = gettingHit;
    }
    protected void knockBack() {
        if (getDirection().equals("_left")) {
            velocityX = 10;
            velocityY = -10;
        } else if (getDirection().equals("_right")) {
            velocityX = -10;
            velocityY = -10;
        }
    }
    protected void setInvincibilityTimer(long invincibilityTimer) {
        this.invincibilityTimer = invincibilityTimer;
    }
}
