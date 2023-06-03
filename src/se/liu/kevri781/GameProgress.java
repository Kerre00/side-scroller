package se.liu.kevri781;

import java.util.EnumMap;
import java.util.Map;

/**
 * Represents the game progress.
 * Used to keep track of the player's money and upgrade levels.
 */
public class GameProgress {
    private int money;
    private Map<Upgrades, Integer> upgradeLevels = new EnumMap<>(Upgrades.class);
    private void GameProgress() {
	money = 0;
//	setUpgradeLevels();
    }
    public void setUpgradeLevels() {
	upgradeLevels.put(Upgrades.MAX_DAMAGE, 1);
	upgradeLevels.put(Upgrades.MAX_HEALTH, 1);
	upgradeLevels.put(Upgrades.SPEED, 1);
	upgradeLevels.put(Upgrades.JUMP_HEIGHT, 1);
	upgradeLevels.put(Upgrades.ATTACK_REACH, 1);
	upgradeLevels.put(Upgrades.RECOVERY_TIME, 1);
	upgradeLevels.put(Upgrades.SKELETON_WARRIOR, 1);
	upgradeLevels.put(Upgrades.SKELETON_SPEARMAN, 0);
	upgradeLevels.put(Upgrades.SKELETON_ARCHER, 0);
	upgradeLevels.put(Upgrades.LIGHTNING_MAGE, 0);
	upgradeLevels.put(Upgrades.WANDERER_MAGICIAN, 0);
	upgradeLevels.put(Upgrades.FIRE_WIZARD, 0);
    }

    public void addMoney(int amount) {
	money += amount;
    }

    public int getMoney() {
	return money;
    }

    public int getUpgradeLevel(Upgrades upgradeName) {
	return upgradeLevels.getOrDefault(upgradeName, 0);
    }

    public void setUpgradeLevel(Upgrades upgradeName, int level) {
	upgradeLevels.put(upgradeName, level);
    }
}