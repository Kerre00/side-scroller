package se.liu.kevri781;

/**
 * Represents an enemy in the game. Contains the logic for enemy AI. It also contains the information for the enemy's
 * type, stats and position etc. Extends the Character class to inherit the methods for movement, attacking and taking
 * damage.
 */
public class Enemy extends Character {
    private CharacterType characterType;
    private int moneyValue;
    private GameProgress gameProgress;

    public Enemy(final CharacterType characterType, final int x, final int y, final int width, final int height, GameProgress gameProgress, int groundLevel) {
        super(x, y, width, height);
        this.gameProgress = gameProgress;
        this.setScale(3); // Scale of enemy sprites
        this.setGroundCoordSpriteOffset(groundLevel - getScaledHeight());
        this.characterType = characterType;
        setEnemyStats(characterType); // Set enemy stats based on upgrade level
    }
    @Override public void update() {
        /**
         * Updates the enemy's position and applies gravity.
         */
        x += velocityX;
        y += velocityY;
        this.applyGravity();
    }
    public void decisionLogic(Player player) {
        /**
         * Contains the logic for the enemy AI. The enemy will move towards the player and attack if the player is within
         * range.
         */
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
    public CharacterType getCharacterType() {
        return characterType;
    }
    public int getMoneyValue() {
        /**
         * Returns the money value of the enemy of a specific enemytype.
         */
        switch (characterType) {
            case SKELETON_WARRIOR:
                moneyValue = 10 + 5 * gameProgress.getUpgradeLevel(Upgrades.SKELETON_WARRIOR);
                break;
            case SKELETON_ARCHER:
                moneyValue = 30 + 15 * gameProgress.getUpgradeLevel(Upgrades.SKELETON_ARCHER);
                break;
            case SKELETON_SPEARMAN:
                moneyValue = 20 + 10 * gameProgress.getUpgradeLevel(Upgrades.SKELETON_SPEARMAN);
                break;
            case WANDERER_MAGICIAN:
                moneyValue = 80 + 40 * gameProgress.getUpgradeLevel(Upgrades.WANDERER_MAGICIAN);
                break;
            case FIRE_WIZARD:
                moneyValue = 60 + 30 * gameProgress.getUpgradeLevel(Upgrades.FIRE_WIZARD);
                break;
            case LIGHTNING_MAGE:
                moneyValue = 100 + 50 * gameProgress.getUpgradeLevel(Upgrades.LIGHTNING_MAGE);
                break;
        }
        return moneyValue;
    }
    private void setEnemyStats(CharacterType enemytype) {
        /**
         * Sets the enemy stats based on the enemytype and the upgrade level of the enemytype.
         */
        switch (enemytype) {
            case SKELETON_WARRIOR:
                this.setSpeed(3 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_WARRIOR));
                this.setMaxHealth(5 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_WARRIOR));
                this.setDamage(1 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_WARRIOR));
                this.setAttackReach(100 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_WARRIOR) * 20);
                break;
            case SKELETON_ARCHER:
                this.setSpeed(5 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_ARCHER));
                this.setMaxHealth(3 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_ARCHER));
                this.setDamage(2 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_ARCHER));
                this.setAttackReach(300 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_ARCHER) * 40);
                break;
            case SKELETON_SPEARMAN:
                this.setSpeed(4 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_SPEARMAN));
                this.setMaxHealth(4 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_SPEARMAN));
                this.setDamage(3 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_SPEARMAN));
                this.setAttackReach(200 + gameProgress.getUpgradeLevel(Upgrades.SKELETON_SPEARMAN) * 40);
                break;
            case WANDERER_MAGICIAN:
                this.setSpeed(5 + gameProgress.getUpgradeLevel(Upgrades.WANDERER_MAGICIAN));
                this.setMaxHealth(5 + gameProgress.getUpgradeLevel(Upgrades.WANDERER_MAGICIAN) * 2);
                this.setDamage(5 + gameProgress.getUpgradeLevel(Upgrades.WANDERER_MAGICIAN) * 2);
                this.setAttackReach(350 + gameProgress.getUpgradeLevel(Upgrades.WANDERER_MAGICIAN) * 30);
                break;
            case FIRE_WIZARD:
                this.setSpeed(5 + gameProgress.getUpgradeLevel(Upgrades.FIRE_WIZARD));
                this.setMaxHealth(6 + gameProgress.getUpgradeLevel(Upgrades.FIRE_WIZARD) * 2);
                this.setDamage(6 + gameProgress.getUpgradeLevel(Upgrades.FIRE_WIZARD) * 2);
                this.setAttackReach(350 + gameProgress.getUpgradeLevel(Upgrades.FIRE_WIZARD) * 30);
                break;
            case LIGHTNING_MAGE:
                this.setSpeed(5 + gameProgress.getUpgradeLevel(Upgrades.LIGHTNING_MAGE));
                this.setMaxHealth(5 + gameProgress.getUpgradeLevel(Upgrades.LIGHTNING_MAGE) * 2);
                this.setDamage(7 + gameProgress.getUpgradeLevel(Upgrades.LIGHTNING_MAGE) * 2);
                this.setAttackReach(350 + gameProgress.getUpgradeLevel(Upgrades.LIGHTNING_MAGE) * 30);
                break;
        }
    }

    public GameProgress getGameProgress() {
        return gameProgress;
    }
}
