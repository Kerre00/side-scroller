package se.liu.kevri781;

import java.awt.*;

/**
 * The GameObjects class represents any object that can appear in the game, including the player, enemies, and obstacles.
 * It contains properties such as position, velocity, and collision detection. It is an abstract class and is extended by
 * the Player and Enemy classes.
 */
public abstract class GameObjects
{
//    Represents any object that can appear in the game, including the player, enemies, and obstacles.
//    Contains properties such as position, velocity, and collision detection.
    protected int x, y;
    protected int velocityX, velocityY;
    protected int width, height;
    protected int scale = 1; // Scale of the object when resized
    private double gravity = 1; // Gravity in pixels per second per second
    private boolean isOnGround;
    private Point centerCoordinate;
    private boolean isSyncedWithBackground = false;
    protected int groundCoordSpriteOffset;
    protected int groundLevel;

    // Constructor
    protected GameObjects(int x, int y, int width, int height, int groundLevel) {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
        this.width = width;
        this.height = height;
        this.groundLevel = groundLevel;
        this.centerCoordinate = new Point(x + getScaledWidth() / 2, y + getScaledHeight() / 2);
        isOnGround = false;
        this.groundCoordSpriteOffset = groundLevel - height;
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
        if (y < groundCoordSpriteOffset) {
            velocityY += (int) gravity;
        } else if (y > groundCoordSpriteOffset) {
            setOnGround();
        }
    }
    public void setOnGround() {
        if (y > groundCoordSpriteOffset) {
            y = groundCoordSpriteOffset;
            velocityY = 0;
            isOnGround = true;
        } else {
            isOnGround = false;
        }
    }
    public void setGroundCoordSpriteOffset(int groundCoordSpriteOffset) {
        this.groundCoordSpriteOffset = groundCoordSpriteOffset;
    }

    public boolean isOnGround() {
        if (y == groundCoordSpriteOffset) {
            return true;
        } else {
            return false;
        }
    }
    public abstract void update();
    public int xDistanceTo(GameObjects other, boolean enableDirection) {
        int xMid = x + this.getScaledWidth() / 2;
        int xMidOther = other.getX() + other.getScaledWidth() / 2;
        int xDistance;
        if (enableDirection) {
            xDistance = xMid - xMidOther;
        } else {
            xDistance = Math.abs(xMid - xMidOther);
        }
        return xDistance;
    }
    public void moveWithBackground(GameBackground background) {
        this.velocityX -= background.getGroundSpeed();
        if (!isSyncedWithBackground) {
            isSyncedWithBackground = true;
        }
    }
    public Point getCenterCoordinate() {
        return centerCoordinate;
    }
    public void setCenterCoordinate(Point centerCoordinate) {
        this.centerCoordinate = centerCoordinate;
    }
    public int getScale() { return scale; }
    public void setScale(int scale) { this.scale = scale; }

    // Common methods for all game objects
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getScaledWidth() { return width * scale; }
    public int getScaledHeight() { return height * scale; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    public int getVelocityX() { return velocityX; }
    public int getVelocityY() { return velocityY; }

    public void setVelocityX(int vx) { this.velocityX = vx; }
    public void setVelocityY(int vy) { this.velocityY = vy; }

}
