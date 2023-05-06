package se.liu.kevri781;

import static se.liu.kevri781.GamePanel.GROUND_LEVEL;

public class Player extends Character
{
    private final int spaceBelowSprite = 49; // The space below the sprite that is not part of the sprite
    private int money = 0;
    public GameBackground background;
    public Player(final int x, final int y, final int width, final int height, GameBackground background) {
        super(x, y, width, height);
        this.background = background;
        this.setAttackReach(200);
        this.setScale(5);
        this.setSpeed(5);
        this.setMaxHealth(10);
        this.setDirection(Direction.RIGHT);
        this.setGroundCoord(GROUND_LEVEL - (height - spaceBelowSprite) * scale);
    }

    @Override public void update() {
        // Moves the player
        if (background != null) {
            background.moveBackground(velocityX);
        }
        y += velocityY;
        applyGravity();

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
            int dist = this.xDistanceTo(e, false);

            if (e.recovering) {
                long elapsed = (System.nanoTime() - e.recoverTimer) / 1000000;
                if (elapsed > 2000) {
                    e.recovering = false;
                    e.recoverTimer = 0;
                }
            }

            if (e.isAttacking()) {
                if (dist <= e.attackReach && !recovering) {
                    reduceHealth(1);
                    recovering = true;
                    recoverTimer = System.nanoTime();
                }
            }

            if (dist < 100 && !recovering && !e.isDead()) {
                    reduceHealth(1);
                    recovering = true;
                    recoverTimer = System.nanoTime();
            }

            if (this.isAttacking() && dist <= attackReach && !e.recovering) {
                e.reduceHealth(1);
                e.recovering = true;
                e.recoverTimer = System.nanoTime();
                if (e.getHealth() <= 0) {
                    e.setDead(true);
                    addMoney(e.getMoneyValue());
                }
            }
        }

        // Checks if the player has died
        if (health <= 0) {
            setDead(true);
        }
    }
    public int getMoney() {
        return money;
    }
    public void addMoney(int amount) {
        money += amount;
    }
}
