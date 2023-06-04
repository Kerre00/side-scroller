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

        this.setScale(PLAYER_SCALE); // Scale of player sprite
        this.background = background;
        this.gameProgress = gameProgress;

        // Sets the player's stats based on the upgrades
        this.setDamage(gameProgress.getUpgradeLevel(Upgrades.MAX_DAMAGE));
        this.setMaxHealth(10 * gameProgress.getUpgradeLevel(Upgrades.MAX_HEALTH));
        this.setSpeed(4 + gameProgress.getUpgradeLevel(Upgrades.SPEED));
        this.setJumpHeight(10 + 5 * gameProgress.getUpgradeLevel(Upgrades.JUMP_HEIGHT));
        this.setMaxRecoveryTime(1000 + 500L * gameProgress.getUpgradeLevel(Upgrades.RECOVERY_TIME));
        this.setAttackReach(100 + 50 * gameProgress.getUpgradeLevel(Upgrades.ATTACK_REACH));

        // Sets the player's direction and ground coordinate
        this.setDirection(Direction.RIGHT);
        this.setGroundCoordSpriteOffset(groundLevel - (height - SPRITE_EMPTY_SPACE) * scale);
    }

    @Override public void update() {
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
