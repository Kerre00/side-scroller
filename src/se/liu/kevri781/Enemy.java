package se.liu.kevri781;

import java.awt.*;

import static se.liu.kevri781.GamePanel.GROUND_LEVEL;

public class Enemy extends Character
{
    // Represents an enemy character.
    // Defines methods for enemy-specific behavior, such as AI and spawning.
    // Define properties common to all enemies
    private CharacterType characterType;
    private int moneyValue;

    public Enemy(final CharacterType characterType, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.characterType = characterType;
        this.setScale(3);
        this.setAttackReach(100);
        this.setSpeed(5);
        this.setGroundCoord(GROUND_LEVEL - getScaledHeight());
        this.setHealth(3);
    }
    public void Ai(Player player) {
        // This represents the AI of the enemy
        // The enemy will move towards the player
        // The enemy will attack the player if it is within attack range
        // The enemy will stop attacking if the player is out of range
        // The enemy will start moving towards the player if the player is out of range
        // The enemy will stop moving if the player is within range
        // The enemy will stop moving if the player is dead
        // The enemy will stop moving if the enemy is dead
        // The enemy will stop moving if the enemy is attacking
        // The enemy will stop attacking if the enemy is dead

        if (health <= 0) {
            setDead(true);
        }
        if (player.isDead() || isDead()) {
            stop();
        } else if (xDistanceTo(player, false) <= attackReach) {
            stop();
            attack();
        } else if (xDistanceTo(player, false) > attackReach) {
            setAttacking(false);
            if (player.getX() < x) {
                moveLeft();
            } else if (player.getX() > x) {
                moveRight();
            }
        }
    }

    @Override public void update() {
        x += velocityX;
        y += velocityY;
        this.applyGravity();
    }
    public CharacterType getEnemyType() {
        return characterType;
    }

    public int getMoneyValue() {
        // Returns the money value of the enemy
        // The money value is the amount of money the player gets for killing the enemy
        // The money value is based on the enemy type
        switch (characterType) {
            case SKELETON_WARRIOR:
                moneyValue = 10;
                break;
            case SKELETON_ARCHER:
                moneyValue = 30;
                break;
            case SKELETON_SPEARMAN:
                moneyValue = 20;
                break;
            case WANDERER_MAGICIAN:
                moneyValue = 60;
                break;
            case FIRE_WIZARD:
                moneyValue = 80;
                break;
            case LIGHTNING_MAGE:
                moneyValue = 100;
                break;
        }
        return moneyValue;
    }
//    public int getRandomDamage() {}

}
