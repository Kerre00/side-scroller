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
                final int baseMoneyValueWarrior = 10;
                final int moneyValueMultiplierWarrior = 5;
                moneyValue = baseMoneyValueWarrior + moneyValueMultiplierWarrior * gameProgress.getUpgradeLevel(Upgrades.SKELETON_WARRIOR);
                break;
            case SKELETON_ARCHER:
                final int baseMoneyValueArcher = 20;
                final int moneyValueMultiplierArcher = 10;
                moneyValue = baseMoneyValueArcher + moneyValueMultiplierArcher * gameProgress.getUpgradeLevel(Upgrades.SKELETON_ARCHER);
                break;
            case SKELETON_SPEARMAN:
                final int baseMoneyValueSpearman = 20;
                final int moneyValueMultiplierSpearman = 10;
                moneyValue = baseMoneyValueSpearman + moneyValueMultiplierSpearman * gameProgress.getUpgradeLevel(Upgrades.SKELETON_SPEARMAN);
                break;
            case WANDERER_MAGICIAN:
                final int baseMoneyValueMagician = 80;
                final int moneyValueMultiplierMagician = 40;
                moneyValue = baseMoneyValueMagician + moneyValueMultiplierMagician * gameProgress.getUpgradeLevel(Upgrades.WANDERER_MAGICIAN);
                break;
            case FIRE_WIZARD:
                final int baseMoneyValueWizard = 60;
                final int moneyValueMultiplierWizard = 30;
                moneyValue = baseMoneyValueWizard + moneyValueMultiplierWizard * gameProgress.getUpgradeLevel(Upgrades.FIRE_WIZARD);
                break;
            case LIGHTNING_MAGE:
                final int baseMoneyValueMage = 100;
                final int moneyValueMultiplierMage = 50;
                moneyValue = baseMoneyValueMage + moneyValueMultiplierMage * gameProgress.getUpgradeLevel(Upgrades.LIGHTNING_MAGE);
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
                final int baseSpeedWarrior = 3;
                final int baseHealthWarrior = 5;
                final int baseDamageWarrior = 1;
                final int baseAttackReachWarrior = 100;
                final int attackReachMultiplierWarrior = 20;
                this.setSpeed(baseSpeedWarrior + gameProgress.getUpgradeLevel(Upgrades.SKELETON_WARRIOR));
                this.setMaxHealth(baseHealthWarrior + gameProgress.getUpgradeLevel(Upgrades.SKELETON_WARRIOR));
                this.setDamage(baseDamageWarrior + gameProgress.getUpgradeLevel(Upgrades.SKELETON_WARRIOR));
                this.setAttackReach(baseAttackReachWarrior + gameProgress.getUpgradeLevel(Upgrades.SKELETON_WARRIOR) * attackReachMultiplierWarrior);
                break;
            case SKELETON_ARCHER:
                final int baseSpeedArcher = 5;
                final int baseHealthArcher = 3;
                final int baseDamageArcher = 2;
                final int baseAttackReachArcher = 150;
                final int attackReachMultiplierArcher = 40;
                this.setSpeed(baseSpeedArcher + gameProgress.getUpgradeLevel(Upgrades.SKELETON_ARCHER));
                this.setMaxHealth(baseHealthArcher + gameProgress.getUpgradeLevel(Upgrades.SKELETON_ARCHER));
                this.setDamage(baseDamageArcher + gameProgress.getUpgradeLevel(Upgrades.SKELETON_ARCHER));
                this.setAttackReach(baseAttackReachArcher + gameProgress.getUpgradeLevel(Upgrades.SKELETON_ARCHER) * attackReachMultiplierArcher);
                break;
            case SKELETON_SPEARMAN:
                final int baseSpeedSpearman = 4;
                final int baseHealthSpearman = 4;
                final int baseDamageSpearman = 3;
                final int baseAttackReachSpearman = 200;
                final int attackReachMultiplierSpearman = 40;
                this.setSpeed(baseSpeedSpearman + gameProgress.getUpgradeLevel(Upgrades.SKELETON_SPEARMAN));
                this.setMaxHealth(baseHealthSpearman + gameProgress.getUpgradeLevel(Upgrades.SKELETON_SPEARMAN));
                this.setDamage(baseDamageSpearman + gameProgress.getUpgradeLevel(Upgrades.SKELETON_SPEARMAN));
                this.setAttackReach(baseAttackReachSpearman + gameProgress.getUpgradeLevel(Upgrades.SKELETON_SPEARMAN) * attackReachMultiplierSpearman);
                break;
            case WANDERER_MAGICIAN:
                final int baseSpeedMagician = 5;
                final int baseHealthMagician = 5;
                final int baseDamageMagician = 5;
                final int baseAttackReachMagician = 350;
                final int maxHealthMultiplierMagician = 2;
                final int damageMultiplierMagician = 2;
                final int attackReachMultiplierMagician = 30;
                this.setSpeed(baseSpeedMagician + gameProgress.getUpgradeLevel(Upgrades.WANDERER_MAGICIAN));
                this.setMaxHealth(baseHealthMagician + gameProgress.getUpgradeLevel(Upgrades.WANDERER_MAGICIAN) * maxHealthMultiplierMagician);
                this.setDamage(baseDamageMagician + gameProgress.getUpgradeLevel(Upgrades.WANDERER_MAGICIAN) * damageMultiplierMagician);
                this.setAttackReach(baseAttackReachMagician + gameProgress.getUpgradeLevel(Upgrades.WANDERER_MAGICIAN) * attackReachMultiplierMagician);
                break;
            case FIRE_WIZARD:
                final int baseSpeedWizard = 5;
                final int baseHealthWizard = 6;
                final int baseDamageWizard = 6;
                final int baseAttackReachWizard = 350;
                final int maxHealthMultiplierWizard = 2;
                final int damageMultiplierWizard = 2;
                final int attackReachMultiplierWizard = 30;
                this.setSpeed(baseSpeedWizard + gameProgress.getUpgradeLevel(Upgrades.FIRE_WIZARD));
                this.setMaxHealth(baseHealthWizard + gameProgress.getUpgradeLevel(Upgrades.FIRE_WIZARD) * maxHealthMultiplierWizard);
                this.setDamage(baseDamageWizard + gameProgress.getUpgradeLevel(Upgrades.FIRE_WIZARD) * damageMultiplierWizard);
                this.setAttackReach(baseAttackReachWizard + gameProgress.getUpgradeLevel(Upgrades.FIRE_WIZARD) * attackReachMultiplierWizard);
                break;
            case LIGHTNING_MAGE:
                final int baseSpeedMage = 5;
                final int baseHealthMage = 7;
                final int baseDamageMage = 7;
                final int baseAttackReachMage = 350;
                final int maxHealthMultiplierMage = 2;
                final int damageMultiplierMage = 2;
                final int attackReachMultiplierMage = 30;
                this.setSpeed(baseSpeedMage + gameProgress.getUpgradeLevel(Upgrades.LIGHTNING_MAGE));
                this.setMaxHealth(baseHealthMage + gameProgress.getUpgradeLevel(Upgrades.LIGHTNING_MAGE) * maxHealthMultiplierMage);
                this.setDamage(baseDamageMage + gameProgress.getUpgradeLevel(Upgrades.LIGHTNING_MAGE) * damageMultiplierMage);
                this.setAttackReach(baseAttackReachMage + gameProgress.getUpgradeLevel(Upgrades.LIGHTNING_MAGE) * attackReachMultiplierMage);
                break;
        }
    }

    public GameProgress getGameProgress() {
        return gameProgress;
    }
}
