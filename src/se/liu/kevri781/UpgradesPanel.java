package se.liu.kevri781;

import javax.swing.*;
import java.awt.*;

public class UpgradesPanel extends JPanel
{
    private PanelManager panelManager;

    public UpgradesPanel(final PanelManager panelManager) {
	this.panelManager = panelManager;
	setBackground(Color.DARK_GRAY);

	setLayout(new GridBagLayout());

	GridBagConstraints c = new GridBagConstraints();
	c.insets = new Insets(50, 10, 10, 10); //
	c.gridx = 0;
	c.gridy = 0;
	c.gridwidth = GridBagConstraints.REMAINDER; // spans across all columns
	c.anchor = GridBagConstraints.NORTH; // aligns to the top
	c.weighty = 1.0; // gives vertical weight to the component

	JLabel upgradesLabel = new JLabel("Upgrades");
	upgradesLabel.setFont(new Font("Times New Roman", Font.BOLD, 60)); // sets the font and size
	upgradesLabel.setForeground(Color.WHITE); // sets the color
	add(upgradesLabel, c);

	c.gridx = 0;
	c.gridy = 1;
	c.gridwidth = GridBagConstraints.REMAINDER; // spans across all columns
	c.anchor = GridBagConstraints.NORTH; // aligns to the top
	c.weighty = 0; // resets the vertical weight

	JTextField textField = new JTextField("Balance: 420$");
	textField.setFont(new Font("Times New Roman", Font.BOLD, 40));
	textField.setForeground(Color.WHITE);
	textField.setBackground(Color.DARK_GRAY);
	textField.setBorder(null);
	textField.setEditable(false);
	add(textField, c);

	c.gridx = 0;
	c.gridy = 2;
	c.gridwidth = 1;
	c.anchor = GridBagConstraints.NORTH; // aligns to the center
	c.weighty = 0; // resets the vertical weight
	c.insets = new Insets(15, 10, 10, 10); // resets the insets

	JTextField damageTextField = new JTextField("Max Damage");
	damageTextField.setFont(new Font("Times New Roman", Font.BOLD, 25));
	damageTextField.setForeground(Color.WHITE);
	damageTextField.setBackground(Color.DARK_GRAY);
	damageTextField.setBorder(null);
	damageTextField.setEditable(false);
	add(damageTextField, c);


	c.gridy++;

	JTextField currentDamageTextField = new JTextField("Current LVL: 10");
	currentDamageTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	currentDamageTextField.setForeground(Color.WHITE);
	currentDamageTextField.setBackground(Color.DARK_GRAY);
	currentDamageTextField.setBorder(null);
	currentDamageTextField.setEditable(false);
	add(currentDamageTextField, c);

	c.gridy--;

	c.gridx++;

	JTextField healthTextField = new JTextField("Max Health");
	healthTextField.setFont(new Font("Times New Roman", Font.BOLD, 25));
	healthTextField.setForeground(Color.WHITE);
	healthTextField.setBackground(Color.DARK_GRAY);
	healthTextField.setBorder(null);
	healthTextField.setEditable(false);
	add(healthTextField, c);

	c.gridy++;

	JTextField currentHealthTextField = new JTextField("Current LVL: 100");
	currentHealthTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	currentHealthTextField.setForeground(Color.WHITE);
	currentHealthTextField.setBackground(Color.DARK_GRAY);
	currentHealthTextField.setBorder(null);
	currentHealthTextField.setEditable(false);
	add(currentHealthTextField, c);

	c.gridy--;

	c.gridx++;

	JTextField speedTextField = new JTextField("Speed");
	speedTextField.setFont(new Font("Times New Roman", Font.BOLD, 25));
	speedTextField.setForeground(Color.WHITE);
	speedTextField.setBackground(Color.DARK_GRAY);
	speedTextField.setBorder(null);
	speedTextField.setEditable(false);
	add(speedTextField, c);

	c.gridy++;

	JTextField currentSpeedTextField = new JTextField("Current LVL: 10");
	currentSpeedTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	currentSpeedTextField.setForeground(Color.WHITE);
	currentSpeedTextField.setBackground(Color.DARK_GRAY);
	currentSpeedTextField.setBorder(null);
	currentSpeedTextField.setEditable(false);
	add(currentSpeedTextField, c);

	c.gridy--;

	c.gridx++;

	JTextField jumpTextField = new JTextField("Jump Height");
	jumpTextField.setFont(new Font("Times New Roman", Font.BOLD, 25));
	jumpTextField.setForeground(Color.WHITE);
	jumpTextField.setBackground(Color.DARK_GRAY);
	jumpTextField.setBorder(null);
	jumpTextField.setEditable(false);
	add(jumpTextField, c);

	c.gridy++;

	JTextField currentJumpTextField = new JTextField("Current LVL: 10");
	currentJumpTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	currentJumpTextField.setForeground(Color.WHITE);
	currentJumpTextField.setBackground(Color.DARK_GRAY);
	currentJumpTextField.setBorder(null);
	currentJumpTextField.setEditable(false);
	add(currentJumpTextField, c);

	c.gridy--;

	c.gridx++;

	JTextField fireRateTextField = new JTextField("Attack Rate");
	fireRateTextField.setFont(new Font("Times New Roman", Font.BOLD, 25));
	fireRateTextField.setForeground(Color.WHITE);
	fireRateTextField.setBackground(Color.DARK_GRAY);
	fireRateTextField.setBorder(null);
	fireRateTextField.setEditable(false);
	add(fireRateTextField, c);

	c.gridy++;

	JTextField currentFireRateTextField = new JTextField("Current LVL: 10");
	currentFireRateTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	currentFireRateTextField.setForeground(Color.WHITE);
	currentFireRateTextField.setBackground(Color.DARK_GRAY);
	currentFireRateTextField.setBorder(null);
	currentFireRateTextField.setEditable(false);
	add(currentFireRateTextField, c);

	c.gridy--;

	c.gridx++;

	JTextField currentMoneyTextField = new JTextField("Recovery Time");
	currentMoneyTextField.setFont(new Font("Times New Roman", Font.BOLD, 25));
	currentMoneyTextField.setForeground(Color.WHITE);
	currentMoneyTextField.setBackground(Color.DARK_GRAY);
	currentMoneyTextField.setBorder(null);
	currentMoneyTextField.setEditable(false);
	add(currentMoneyTextField, c);

	c.gridy++;

	JTextField currentRecoveryTimeTextField = new JTextField("Current LVL: 10");
	currentRecoveryTimeTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	currentRecoveryTimeTextField.setForeground(Color.WHITE);
	currentRecoveryTimeTextField.setBackground(Color.DARK_GRAY);
	currentRecoveryTimeTextField.setBorder(null);
	currentRecoveryTimeTextField.setEditable(false);
	add(currentRecoveryTimeTextField, c);

	c.gridx = 0;
	c.gridy = 4;
	c.gridwidth = 1;
	c.weighty = 0; // resets the vertical weight

	JButton damageUpgrade = new JButton("100$ (+1 dmg)");
	damageUpgrade.setPreferredSize(new Dimension(200, 50));
	damageUpgrade.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(damageUpgrade, c);

	c.gridx++;

	JButton healthUpgrade = new JButton("100$ (+10 hp)");
	healthUpgrade.setPreferredSize(new Dimension(200, 50));
	healthUpgrade.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(healthUpgrade, c);

	c.gridx++;

	JButton speedUpgrade = new JButton("100$ (+1 spd)");
	speedUpgrade.setPreferredSize(new Dimension(200, 50));
	speedUpgrade.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(speedUpgrade, c);

	c.gridx++;

	JButton jumpUpgrade = new JButton("100$ (+1 jum)");
	jumpUpgrade.setPreferredSize(new Dimension(200, 50));
	jumpUpgrade.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(jumpUpgrade, c);

	c.gridx++;

	JButton fireRateUpgrade = new JButton("100$ (+1 fr)");
	fireRateUpgrade.setPreferredSize(new Dimension(200, 50));
	fireRateUpgrade.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(fireRateUpgrade, c);

	c.gridx++;

	JButton recoveryTimeUpgrade = new JButton("100$ (+1 rt)");
	recoveryTimeUpgrade.setPreferredSize(new Dimension(200, 50));
	recoveryTimeUpgrade.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(recoveryTimeUpgrade, c);

	c.gridx = 0;
	c.gridy = 5;
	c.gridwidth = GridBagConstraints.REMAINDER;
	c.weighty = 0; // Resets the vertical weight

	JTextField buyEnemyTextField = new JTextField("Buy new enemies");
	buyEnemyTextField.setFont(new Font("Times New Roman", Font.BOLD, 25));
	buyEnemyTextField.setForeground(Color.WHITE);
	buyEnemyTextField.setBackground(Color.DARK_GRAY);
	buyEnemyTextField.setBorder(null);
	buyEnemyTextField.setEditable(false);
	add(buyEnemyTextField, c);

	c.gridwidth = 1;
	c.gridy++;
	c.gridx = 0;

	JTextField skeletonWarriorTextField = new JTextField("Skeleton Warrior");
	skeletonWarriorTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	skeletonWarriorTextField.setForeground(Color.WHITE);
	skeletonWarriorTextField.setBackground(Color.DARK_GRAY);
	skeletonWarriorTextField.setBorder(null);
	skeletonWarriorTextField.setEditable(false);
	add(skeletonWarriorTextField, c);

	c.gridy++;

	ImageIcon skeletonWarriorIcon = new ImageIcon("resources/images/upgrades/skeleton_warrior.png");
	JLabel skeletonWarriorLabel = new JLabel(skeletonWarriorIcon);
	add(skeletonWarriorLabel, c);

	c.gridy--;

	c.gridx++;

	JTextField spearmanTextField = new JTextField("Skeleton Spearman");
	spearmanTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	spearmanTextField.setForeground(Color.WHITE);
	spearmanTextField.setBackground(Color.DARK_GRAY);
	spearmanTextField.setBorder(null);
	spearmanTextField.setEditable(false);
	add(spearmanTextField, c);

	c.gridy++;

	ImageIcon spearmanIcon = new ImageIcon("resources/images/upgrades/skeleton_spearnan.png");
	JLabel spearmanLabel = new JLabel(spearmanIcon);
	add(spearmanLabel, c);

	c.gridy--;

	c.gridx++;

	JTextField archerTextField = new JTextField("Skeleton Archer");
	archerTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	archerTextField.setForeground(Color.WHITE);
	archerTextField.setBackground(Color.DARK_GRAY);
	archerTextField.setBorder(null);
	archerTextField.setEditable(false);
	add(archerTextField, c);

	c.gridy++;

	ImageIcon archerIcon = new ImageIcon("resources/images/upgrades/skeleton_archer.png");
	JLabel archerLabel = new JLabel(archerIcon);
	add(archerLabel, c);

	c.gridy--;

	c.gridx++;

	JTextField fireWizardTextField = new JTextField("Fire Wizard");
	fireWizardTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	fireWizardTextField.setForeground(Color.WHITE);
	fireWizardTextField.setBackground(Color.DARK_GRAY);
	fireWizardTextField.setBorder(null);
	fireWizardTextField.setEditable(false);
	add(fireWizardTextField, c);

	c.gridy++;

	ImageIcon fireWizardIcon = new ImageIcon("resources/images/upgrades/fire_wizard.png");
	JLabel fireWizardLabel = new JLabel(fireWizardIcon);
	add(fireWizardLabel, c);

	c.gridy--;

	c.gridx++;

	JTextField magicianTextField = new JTextField("Wanderer Magician");
	magicianTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	magicianTextField.setForeground(Color.WHITE);
	magicianTextField.setBackground(Color.DARK_GRAY);
	magicianTextField.setBorder(null);
	magicianTextField.setEditable(false);
	add(magicianTextField, c);

	c.gridy++;

	ImageIcon magicianIcon = new ImageIcon("resources/images/upgrades/wanderer_magician.png");
	JLabel magicianLabel = new JLabel(magicianIcon);
	add(magicianLabel, c);

	c.gridy--;

	c.gridx++;

	JTextField lightningMageTextField = new JTextField("Lightning Mage");
	lightningMageTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	lightningMageTextField.setForeground(Color.WHITE);
	lightningMageTextField.setBackground(Color.DARK_GRAY);
	lightningMageTextField.setBorder(null);
	lightningMageTextField.setEditable(false);
	add(lightningMageTextField, c);

	c.gridy++;

	ImageIcon lightningMageIcon = new ImageIcon("resources/images/upgrades/lightning_mage.png");
	JLabel lightningMageLabel = new JLabel(lightningMageIcon);
	add(lightningMageLabel, c);

	c.gridy++;
	c.gridx = 0;

	JTextField skeletonWarriorLevelTextField = new JTextField("Current LVL: 1");
	skeletonWarriorLevelTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	skeletonWarriorLevelTextField.setForeground(Color.WHITE);
	skeletonWarriorLevelTextField.setBackground(Color.DARK_GRAY);
	skeletonWarriorLevelTextField.setBorder(null);
	skeletonWarriorLevelTextField.setEditable(false);
	add(skeletonWarriorLevelTextField, c);

	c.gridy++;

	JButton buySkeletonWarrior = new JButton("100$");
	buySkeletonWarrior.setFont(new Font("Times New Roman", Font.BOLD, 20));
	buySkeletonWarrior.setPreferredSize(new Dimension(100, 50));
	add(buySkeletonWarrior, c);

	c.gridy--;

	c.gridx++;

	JTextField spearmanLevelTextField = new JTextField("Current LVL: 1");
	spearmanLevelTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	spearmanLevelTextField.setForeground(Color.WHITE);
	spearmanLevelTextField.setBackground(Color.DARK_GRAY);
	spearmanLevelTextField.setBorder(null);
	spearmanLevelTextField.setEditable(false);
	add(spearmanLevelTextField, c);

	c.gridy++;

	JButton buySpearman = new JButton("100$");
	buySpearman.setFont(new Font("Times New Roman", Font.BOLD, 20));
	buySpearman.setPreferredSize(new Dimension(100, 50));
	add(buySpearman, c);

	c.gridy--;

	c.gridx++;

	JTextField archerLevelTextField = new JTextField("Current LVL: 1");
	archerLevelTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	archerLevelTextField.setForeground(Color.WHITE);
	archerLevelTextField.setBackground(Color.DARK_GRAY);
	archerLevelTextField.setBorder(null);
	archerLevelTextField.setEditable(false);
	add(archerLevelTextField, c);

	c.gridy++;

	JButton buyArcher = new JButton("100$");
	buyArcher.setFont(new Font("Times New Roman", Font.BOLD, 20));
	buyArcher.setPreferredSize(new Dimension(100, 50));
	add(buyArcher, c);

	c.gridy--;

	c.gridx++;

	JTextField fireWizardLevelTextField = new JTextField("Current LVL: 1");
	fireWizardLevelTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	fireWizardLevelTextField.setForeground(Color.WHITE);
	fireWizardLevelTextField.setBackground(Color.DARK_GRAY);
	fireWizardLevelTextField.setBorder(null);
	fireWizardLevelTextField.setEditable(false);
	add(fireWizardLevelTextField, c);

	c.gridy++;

	JButton buyFireWizard = new JButton("100$");
	buyFireWizard.setFont(new Font("Times New Roman", Font.BOLD, 20));
	buyFireWizard.setPreferredSize(new Dimension(100, 50));
	add(buyFireWizard, c);

	c.gridy--;

	c.gridx++;

	JTextField magicianLevelTextField = new JTextField("Current LVL: 1");
	magicianLevelTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	magicianLevelTextField.setForeground(Color.WHITE);
	magicianLevelTextField.setBackground(Color.DARK_GRAY);
	magicianLevelTextField.setBorder(null);
	magicianLevelTextField.setEditable(false);
	add(magicianLevelTextField, c);

	c.gridy++;

	JButton buyMagician = new JButton("100$");
	buyMagician.setFont(new Font("Times New Roman", Font.BOLD, 20));
	buyMagician.setPreferredSize(new Dimension(100, 50));
	add(buyMagician, c);

	c.gridy--;

	c.gridx++;

	JTextField lightningMageLevelTextField = new JTextField("Current LVL: 1");
	lightningMageLevelTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
	lightningMageLevelTextField.setForeground(Color.WHITE);
	lightningMageLevelTextField.setBackground(Color.DARK_GRAY);
	lightningMageLevelTextField.setBorder(null);
	lightningMageLevelTextField.setEditable(false);
	add(lightningMageLevelTextField, c);

	c.gridy++;

	JButton buyLightningMage = new JButton("100$");
	buyLightningMage.setFont(new Font("Times New Roman", Font.BOLD, 20));
	buyLightningMage.setPreferredSize(new Dimension(100, 50));
	add(buyLightningMage, c);

	c.gridx = 0;
	c.gridy++;
	c.gridwidth = GridBagConstraints.REMAINDER; // spans across all columns
	c.insets = new Insets(10, 10, 10, 10); // adds padding to the bottom

	JTextField enemyNote = new JTextField("Note: When upgrading enemies their stats will be increased, including their money reward");
	enemyNote.setFont(new Font("Times New Roman", Font.BOLD, 20));
	enemyNote.setForeground(Color.WHITE);
	enemyNote.setBackground(Color.DARK_GRAY);
	enemyNote.setBorder(null);
	enemyNote.setEditable(false);
	add(enemyNote, c);

	c.gridx = 0;
	c.gridy++;
	c.gridwidth = GridBagConstraints.REMAINDER; // spans across all columns
	c.anchor = GridBagConstraints.SOUTH; // aligns to the top
	c.weighty = 100; // gives vertical weight to the component
	c.insets = new Insets(10, 10, 50, 10); // adds padding to the bottom

	JButton backButton = new JButton("Back");
	backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	backButton.setPreferredSize(new Dimension(400, 50));
	add(backButton, c);
	backButton.addActionListener(e -> {
	    panelManager.switchToMainMenu();
	});
    }
}
