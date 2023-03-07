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
    private boolean firing;


    public Character(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.health = 1;
        this.dead = false;
        this.level = 1;
        this.damage = 1;
        this.speed = 1;
        this.maxHealth = 1;
        this.firing = false;
    }

    public void moveLeft() {
        velocityX = -speed;
    }
    public void moveRight() {
        velocityX = speed;
    }
    public void moveUp() {
        velocityY = -speed;
    }
    public void moveDown() {
            velocityY = speed;
    }
    public void moveCharacter() {
        x += velocityX;
        y += velocityY;
    }
    public void attack() {
        firing = true;
    }
    public void stop() {
        velocityX = 0;
        velocityY = 0;
    }
    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public boolean isFiring() {
        return firing;
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

//        public void setMaxSpeed(int maxSpeed) {
//                this.maxSpeed = maxSpeed;
//        }
//
//        public int getMaxSpeed() {
//                return maxSpeed;
//        }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
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
}
