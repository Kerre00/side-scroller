package se.liu.kevri781;

import javax.swing.*;

/**
 * The UpgradesManager class is responsible for managing the upgrades.
 * It is responsible for calculating the price of an upgrade, upgrading
 * the player, and determining which enemies are unlocked.
 */
public class UpgradesManager
{
    public int getUpgradePrice(Upgrades upgrade, GameProgress gameProgress)
    {
	switch (upgrade) {
	    case MAX_DAMAGE, MAX_HEALTH, RECOVERY_TIME, ATTACK_REACH, JUMP_HEIGHT, SPEED, SKELETON_WARRIOR:
		return 100 * gameProgress.getUpgradeLevel(upgrade);
	    case SKELETON_SPEARMAN:
		if (gameProgress.getUpgradeLevel(upgrade) == 0) {
		    return 200;
		}
		return 200 * gameProgress.getUpgradeLevel(upgrade);
	    case SKELETON_ARCHER:
		if (gameProgress.getUpgradeLevel(upgrade) == 0) {
		    return 300;
		}
		return 300 * gameProgress.getUpgradeLevel(upgrade);
	    case LIGHTNING_MAGE:
		if (gameProgress.getUpgradeLevel(upgrade) == 0) {
		    return 600;
		}
		return 600 * gameProgress.getUpgradeLevel(upgrade);
	    case WANDERER_MAGICIAN:
		if (gameProgress.getUpgradeLevel(upgrade) == 0) {
		    return 500;
		}
		return 500 * gameProgress.getUpgradeLevel(upgrade);
	    case FIRE_WIZARD:
		if (gameProgress.getUpgradeLevel(upgrade) == 0) {
		    return 400;
		}
		return 400 * gameProgress.getUpgradeLevel(upgrade);
	    default:
		return 0;
	}
    }
    public void upgrade(Upgrades upgrade, GameProgress gameProgress) {
	if (gameProgress.getMoney() >= getUpgradePrice(upgrade, gameProgress)) {
	    gameProgress.addMoney(-getUpgradePrice(upgrade, gameProgress));
	    gameProgress.setUpgradeLevel(upgrade, gameProgress.getUpgradeLevel(upgrade) + 1);
    	} else {
	    JOptionPane.showMessageDialog(null, "You don't have enough money to buy this upgrade!");
	}
    }
    public CharacterType[] getUnlockedEnemies(GameProgress gameProgress) {
	CharacterType[] unlockedEnemies = new CharacterType[6];
	int unlockedEnemiesIndex = 0;
	for (CharacterType enemy : CharacterType.values()) {
	    if (enemy != CharacterType.PLAYER) {
		unlockedEnemies[unlockedEnemiesIndex] = enemy;
		unlockedEnemiesIndex++;
//		System.out.println(enemy.toString() + " is unlocked!");
	    }
    	}
	return unlockedEnemies;
    }
}
