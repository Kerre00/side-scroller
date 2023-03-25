package se.liu.kevri781;

public abstract class Character extends GameObjects
{
//    Represents a character in the game, which includes the player and enemies.
//    Contains properties such as health, attack power, and movement speed.
//    Defines methods for movement, attacking, and taking damage.
    public int health;
    public boolean dead;
    public int level;
    public int damage;
    public int speed;
    public int maxHealth;
    public int attackReach;
    private boolean attacking;
    public String stringDirection;

    public Character(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.health = 3;
        this.dead = false;
        this.level = 1;
        this.damage = 1;
        this.speed = 1;
        this.maxHealth = 1;
        this.attacking = false;
        this.stringDirection = "_right";
    }

    // ----------------------------------
    // -------Methods for movement-------
    // ----------------------------------
    public void moveLeft() {
        velocityX = -speed;
    }
    public void moveRight() {
        velocityX = speed;
    }
    public void jump() {
        // This method is used to make the character jump
        // the character can only jump once the velocityY is 0
        if (!isAboveGround()) {
            velocityY -= 20;
        }
    }
    public void stop() {
        velocityX = 0;
        velocityY = 0;
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


    protected boolean isGettingHit() {
        return false;
    }

    protected boolean isHurt() {
        return false;
    }

}
