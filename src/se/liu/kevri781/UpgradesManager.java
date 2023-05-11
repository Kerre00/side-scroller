package se.liu.kevri781;

import javax.swing.*;

public class UpgradesManager
{
    public int getUpgradePrice(Upgrades upgrade)
    {
	switch (upgrade) {
	    case MAX_DAMAGE, MAX_HEALTH, RECOVERY_TIME, ATTACK_REACH, JUMP_HEIGHT, SPEED, SKELETON_WARRIOR:
		return 100 * GameProgress.getUpgradeLevel(upgrade);
	    case SKELETON_SPEARMAN:
		if (GameProgress.getUpgradeLevel(upgrade) == 0) {
		    return 200;
		}
		return 200 * GameProgress.getUpgradeLevel(upgrade);
	    case SKELETON_ARCHER:
		if (GameProgress.getUpgradeLevel(upgrade) == 0) {
		    return 300;
		}
		return 300 * GameProgress.getUpgradeLevel(upgrade);
	    case LIGHTNING_MAGE:
		if (GameProgress.getUpgradeLevel(upgrade) == 0) {
		    return 600;
		}
		return 600 * GameProgress.getUpgradeLevel(upgrade);
	    case WANDERER_MAGICIAN:
		if (GameProgress.getUpgradeLevel(upgrade) == 0) {
		    return 500;
		}
		return 500 * GameProgress.getUpgradeLevel(upgrade);
	    case FIRE_WIZARD:
		if (GameProgress.getUpgradeLevel(upgrade) == 0) {
		    return 400;
		}
		return 400 * GameProgress.getUpgradeLevel(upgrade);
	    default:
		return 0;
	}
    }
    public void upgrade(Upgrades upgrade) {
	if (GameProgress.getMoney() >= getUpgradePrice(upgrade)) {
	    GameProgress.addMoney(-getUpgradePrice(upgrade));
	    GameProgress.setUpgradeLevel(upgrade, GameProgress.getUpgradeLevel(upgrade) + 1);
    	} else {
	    JOptionPane.showMessageDialog(null, "You don't have enough money to buy this upgrade!");
	}
    }
    public CharacterType[] getUnlockedEnemies() {
	CharacterType[] unlockedEnemies = new CharacterType[6];
	int unlockedEnemiesIndex = 0;
	for (CharacterType enemy : CharacterType.values()) {
	    if (enemy != CharacterType.PLAYER && GameProgress.getUpgradeLevel(Upgrades.valueOf(enemy.toString())) > 0) {
		unlockedEnemies[unlockedEnemiesIndex] = enemy;
		unlockedEnemiesIndex++;
		System.out.println(enemy.toString() + " is unlocked!");
	    }
    	}
	return unlockedEnemies;
    }
}
