package se.liu.kevri781;

/**
 * Represents the different upgrades the player can buy.
 * Used to determine which upgrade to buy.
 * Also used to determine which upgrades the player has bought.
 */
public enum Upgrades {
    MAX_DAMAGE(true), MAX_HEALTH(true), SPEED(true), JUMP_HEIGHT(true), ATTACK_REACH(true),
    RECOVERY_TIME(true), SKELETON_WARRIOR(false), SKELETON_SPEARMAN(false), SKELETON_ARCHER(false),
    LIGHTNING_MAGE(false), WANDERER_MAGICIAN(false), FIRE_WIZARD(false);

    private final boolean isPlayerUpgrade;

    Upgrades(boolean isPlayerUpgrade) {
        this.isPlayerUpgrade = isPlayerUpgrade;
    }

    public boolean checkIfPlayerUpgrade() {
        return isPlayerUpgrade;
    }

    public CharacterType getCharacterType(Upgrades upgrade) {
        switch (upgrade) {
            case SKELETON_WARRIOR:
                return CharacterType.SKELETON_WARRIOR;
            case SKELETON_SPEARMAN:
                return CharacterType.SKELETON_SPEARMAN;
            case SKELETON_ARCHER:
                return CharacterType.SKELETON_ARCHER;
            case LIGHTNING_MAGE:
                return CharacterType.LIGHTNING_MAGE;
            case WANDERER_MAGICIAN:
                return CharacterType.WANDERER_MAGICIAN;
            case FIRE_WIZARD:
                return CharacterType.FIRE_WIZARD;
            default:
                return null;
        }
    }
}
