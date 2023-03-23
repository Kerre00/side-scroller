package se.liu.kevri781;

import java.awt.*;

public abstract class GameObjects
{
//    Represents any object that can appear in the game, including the player, enemies, and obstacles.
//    Contains properties such as position, velocity, and collision detection.
    protected int x, y;
    protected int velocityX, velocityY;
    protected int width, height;
    protected int scale = 1;
    private double gravity;
    private boolean isOnGround;
    private Point centerCoordinate;

    // Constructor
    public GameObjects(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
        this.width = width;
        this.height = height;
        this.centerCoordinate = new Point(x + width / 2, y + height / 2);
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
        this.velocityX += -(background.getGroundSpeed());
//        x = -this.velocityX;
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
