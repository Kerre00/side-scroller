package se.liu.kevri781;

import java.util.EnumMap;
import java.util.Map;

public class GameProgress {
    private static int money;
    private static Map<Upgrades, Integer> upgradeLevels;

    static {
	money = 0;
	upgradeLevels = new EnumMap<>(Upgrades.class);
	setUpgradeLevels();
    }
    private static void setUpgradeLevels() {
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

    private GameProgress() {}

    public static void addMoney(int amount) {
	money += amount;
    }

    public static int getMoney() {
	return money;
    }

    public static int getUpgradeLevel(Upgrades upgradeName) {
	return upgradeLevels.getOrDefault(upgradeName, 0);
    }

    public static void setUpgradeLevel(Upgrades upgradeName, int level) {
	upgradeLevels.put(upgradeName, level);
    }
}