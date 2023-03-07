package se.liu.kevri781;

import java.awt.*;

public abstract class GameObjects
{
//    Represents any object that can appear in the game, including the player, enemies, and obstacles.
//    Contains properties such as position, velocity, and collision detection.
    protected int x, y;
    protected int velocityX, velocityY;
    protected int width, height;
    private double gravity;
    private boolean isOnGround;


    // Constructor
    public GameObjects(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
        this.width = width;
        this.height = height;
        gravity = 0.5;
        isOnGround = false;
    }

    public GameObjects() {
        this(0, 0, 0, 0);
    }

    // Getter and setter methods for the gravity field

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    // Gravity-related methods
    public void applyGravity() {
        if (!isOnGround) {
            velocityY += gravity;
            y += velocityY;
        }
    }

    public void setOnGround(boolean isOnGround) {
        this.isOnGround = isOnGround;
    }

    public boolean isOnGround() {
        return isOnGround;
    }


    // Abstract methods for handling game logic
    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract boolean collidesWith(GameObjects other);

    // Common methods for all game objects
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    public int getVelocityX() { return velocityX; }
    public int getVelocityY() { return velocityY; }

    public void setVelocityX(int vx) { this.velocityX = vx; }
    public void setVelocityY(int vy) { this.velocityY = vy; }

    protected Rectangle getBounds() {
        return null;
    }
}
