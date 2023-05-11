package se.liu.kevri781;

import static se.liu.kevri781.GamePanel.GROUND_LEVEL;

public class Player extends Character {
    /**
     * Represents the player character. Contains methods for updating the player's position, information
     * about player stats, and checking for collisions with enemies.
     */
    private final static int SPRITE_EMPTY_SPACE = 49; // The space below the sprite that is empty
    private final static int PLAYER_SCALE = 5;
    public GameBackground background;
    private int money = 0;
    public Player(final int x, final int y, final int width, final int height, GameBackground background) {
        super(x, y, width, height);
        this.setScale(PLAYER_SCALE); // Scale of player sprite
        this.background = background;

        // Sets the player's stats based on the upgrades
        this.setDamage(GameProgress.getUpgradeLevel(Upgrades.MAX_DAMAGE));
        this.setMaxHealth(10 * GameProgress.getUpgradeLevel(Upgrades.MAX_HEALTH));
        this.setSpeed(4 + GameProgress.getUpgradeLevel(Upgrades.SPEED));
        this.setJumpHeight(10 + 5 * GameProgress.getUpgradeLevel(Upgrades.JUMP_HEIGHT));
        this.setInvincibilityTimer(1000 + 500L * GameProgress.getUpgradeLevel(Upgrades.RECOVERY_TIME));
        this.setAttackReach(100 + 50 * GameProgress.getUpgradeLevel(Upgrades.ATTACK_REACH));

        // Sets the player's direction and ground coordinate
        this.setDirection(Direction.RIGHT);
        this.setGroundCoord(GROUND_LEVEL - (height - SPRITE_EMPTY_SPACE) * scale);
    }

    @Override public void update() {
        /**
         * Updates the player's position and applies gravity. And also checks for collisions with enemies.
         */

        // Updates the "player's" (background) position
        if (background != null) {background.moveBackground(velocityX);}
        y += velocityY;
        applyGravity();

        // Checks if the player is recovering and sets recovering to false if the player has been
        // recovering for longer than the invincibility timer.
        if (recovering) {
            long elapsed = (System.nanoTime() - recoverTimer) / 1000000;
            if (elapsed > invincibilityTimer) {
                recovering = false;
                recoverTimer = 0;
            }
        }

        for (int i = 0; i < GamePanel.enemies.size(); i++) {
            Enemy e = GamePanel.enemies.get(i);
            int dist = this.xDistanceTo(e, false);

            // Checks if the enemy is recovering and sets recovering to false if the enemy has been
            // recovering for longer than the invincibility timer.
            if (e.recovering) {
                long elapsed = (System.nanoTime() - e.recoverTimer) / 1000000;
                if (elapsed > e.invincibilityTimer) {
                    e.recovering = false;
                    e.recoverTimer = 0;
                }
            }

            // If the enemy is attacking and the player is close enough, the player takes damage
            if (e.isAttacking() && !e.isDead()) {
                if (dist <= e.attackReach && !recovering) {
                    reduceHealth(e.getDamage());
                    recovering = true;
                    recoverTimer = System.nanoTime();
                }
            }

            // If the player is close enough to an enemy, the player loses a life
            if (dist < 100 && !recovering && !e.isDead()) {
                    reduceHealth(1);
                    recovering = true;
                    recoverTimer = System.nanoTime();
            }

                // If the player is attacking and the enemy is close enough, the enemy takes damage
            if (this.isAttacking() && dist <= attackReach && !e.recovering) {
                e.reduceHealth(this.getDamage());
                e.recovering = true;
                e.recoverTimer = System.nanoTime();
                if (e.getHealth() <= 0 && !e.isDead()) {
                    addMoney(e.getMoneyValue());
                    e.setDead(true);
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
