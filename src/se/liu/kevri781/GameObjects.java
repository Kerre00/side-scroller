package se.liu.kevri781;

/**
 * The GameObjects class represents any object that can appear in the game, including the player, enemies, and obstacles.
 * It contains properties such as position, velocity, and collision detection. It is an abstract class and is extended by
 * the Player and Enemy classes.
 */
public abstract class GameObjects
{
    protected int x, y;
    protected int velocityX, velocityY;
    protected int width, height;
    protected int scale = 1;
    private boolean isOnGround;
    private boolean isSyncedWithBackground = false;
    protected int groundCoordSpriteOffset;
    protected int groundLevel;

    protected GameObjects(int x, int y, int width, int height, int groundLevel) {
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
        this.width = width;
        this.height = height;
        this.groundLevel = groundLevel;
        isOnGround = false;
        this.groundCoordSpriteOffset = groundLevel - height;
    }

    protected void applyGravity() {
        if (y < groundCoordSpriteOffset) {
            double gravity = 1;
            velocityY += (int) gravity;
        } else if (y > groundCoordSpriteOffset) {
            setOnGround();
        }
    }
    private void setOnGround() {
        if (y > groundCoordSpriteOffset) {
            y = groundCoordSpriteOffset;
            velocityY = 0;
            isOnGround = true;
        } else {
            isOnGround = false;
        }
    }
    protected void setGroundCoordSpriteOffset(int groundCoordSpriteOffset) {
        this.groundCoordSpriteOffset = groundCoordSpriteOffset;
    }
    protected abstract void update();
    protected int xDistanceTo(GameObjects other, boolean enableDirection) {
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
    protected void moveWithBackground(GameBackground background) {
        this.velocityX -= background.getGroundSpeed();
        if (!isSyncedWithBackground) {
            isSyncedWithBackground = true;
        }
    }
    protected int getScale() { return scale; }
    protected void setScale(int scale) { this.scale = scale; }
    protected int getX() { return x; }
    protected int getY() { return y; }
    protected int getWidth() { return width; }
    protected int getHeight() { return height; }
    protected int getScaledWidth() { return width * scale; }
    protected int getScaledHeight() { return height * scale; }
    protected void setX(int x) { this.x = x; }
    protected void setY(int y) { this.y = y; }

}
