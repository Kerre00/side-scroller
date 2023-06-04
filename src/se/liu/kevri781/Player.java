package se.liu.kevri781;

/**
 * Represents the player character. Contains methods for updating
 * the player's position, information about player stats, and
 * checking for collisions with enemies.
 */
public class Player extends Character {

    private final static int SPRITE_EMPTY_SPACE = 49;
    private final static int PLAYER_SCALE = 5;
    private GameBackground background;
    private GameProgress gameProgress;
    private int money = 0;

    public Player(final int x, final int y, final int width, final int height, GameBackground background, GameProgress gameProgress, int groundLevel) {
        super(x, y, width, height);

        this.setScale(PLAYER_SCALE);
        this.background = background;
        this.gameProgress = gameProgress;

        // Sets the player's stats based on the upgrades
        this.setDamage(gameProgress.getUpgradeLevel(Upgrades.MAX_DAMAGE));
        final int baseMaxHealth = 10;
        this.setMaxHealth(baseMaxHealth * gameProgress.getUpgradeLevel(Upgrades.MAX_HEALTH));
        final int baseSpeed = 4;
        this.setSpeed(baseSpeed + gameProgress.getUpgradeLevel(Upgrades.SPEED));
        final int baseJumpHeight = 10;
        this.setJumpHeight(baseJumpHeight + 5 * gameProgress.getUpgradeLevel(Upgrades.JUMP_HEIGHT));
        final int baseMaxRecoveryTime = 1000;
        final long recoveryTimeMultiplier = 500;
        this.setMaxRecoveryTime(baseMaxRecoveryTime + recoveryTimeMultiplier * gameProgress.getUpgradeLevel(Upgrades.RECOVERY_TIME));
        final int baseAttackReach = 100;
        final int attackReachMultiplier = 50;
        this.setAttackReach(baseAttackReach + attackReachMultiplier * gameProgress.getUpgradeLevel(Upgrades.ATTACK_REACH));

        // Sets the player's direction and ground coordinate
        this.setDirection(Direction.RIGHT);
        this.setGroundCoordSpriteOffset(groundLevel - (height - SPRITE_EMPTY_SPACE) * scale);
    }

    public void update() {
        /**
         * Updates the player's position and applies gravity.
         */
        if (background != null) {background.moveBackground(velocityX);}
        y += velocityY;
        this.applyGravity();
    }
    public int getMoney() {
        return money;
    }
    public void addMoney(int amount) {
        money += amount;
    }
    public GameProgress getGameProgress() {
        return gameProgress;
    }
}
