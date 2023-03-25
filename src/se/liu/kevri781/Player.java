package se.liu.kevri781;

import static se.liu.kevri781.GamePanel.GROUND_LEVEL;

public class Player extends Character
{
    private boolean recovering;
    private long recoverTimer;
    private final int spaceBelowSprite = 49; // The space below the sprite that is not part of the sprite
    public Player(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.recovering = false;
        this.setScale(5);
        this.setSpeed(5);
        this.setDirection(Direction.RIGHT);
        this.setGroundCoord(GROUND_LEVEL - (height - spaceBelowSprite) * scale);
    }

    @Override public void update() {
        // Moves the player
        x += velocityX;
        y += velocityY;
        applyGravity();
//        System.out.println("player velocityX: " + velocityX + " velocityY: " + velocityY);

        // Sets recovering to false if the player has been recovering for 2 seconds
        if (recovering) {
            long elapsed = (System.nanoTime() - recoverTimer) / 1000000;
            if (elapsed > 2000) {
                recovering = false;
                recoverTimer = 0;
            }
        }

        // If the player collides with an enemy, the player loses a life
        for (int i = 0; i < GamePanel.enemies.size(); i++) {
            Enemy e = GamePanel.enemies.get(i);
            if (this.xDistanceTo(e, false) < 100 && !recovering) {
                health--;
                recovering = true;
                recoverTimer = System.nanoTime();
//                System.out.println("You have been hit! You have " + health + " lives left.");
            }
        }

        // Checks if the player has died
        if (health <= 0) {
            setDead(true);
        }
    }
}
