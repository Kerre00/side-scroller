package se.liu.kevri781;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    // Represents the main menu panel

    private PanelManager panelManager;
    private GamePanel gamePanel;
    public MenuPanel(PanelManager panelManager) {
	this.panelManager = panelManager;

	// Set the background color of the panel
	setBackground(Color.DARK_GRAY);

	// Set the layout manager for the panel
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	add(Box.createVerticalStrut(300));
	// Create the title label and add it to the panel
	JLabel titleLabel = new JLabel("Main Menu");
	titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
	titleLabel.setForeground(Color.WHITE);
	titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(titleLabel);

	// Add spacing between the title and the buttons
	add(Box.createVerticalStrut(50));

	// Create the buttons and add them to the panel
	JButton newGameButton = new JButton("New Game");
//	newGameButton.setIcon(new ImageIcon("resources/images/hud/hp_100.png"));
//	newGameButton.setOpaque(false);
//	newGameButton.setContentAreaFilled(false);
//	newGameButton.setBorderPainted(false);

	newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(newGameButton);
	newGameButton.addActionListener(e -> {
	    panelManager.switchToGame();
	});

	add(Box.createVerticalStrut(25));

	// Load game button
	JButton loadGameButton = new JButton("Load Game");
	loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(loadGameButton);
	loadGameButton.addActionListener(e -> {
	    panelManager.switchToGame();
	});

	add(Box.createVerticalStrut(25));


	// Create upgrades button
	JButton upgradesButton = new JButton("Upgrades");
	upgradesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(upgradesButton);
	upgradesButton.addActionListener(e -> {
//	    panelManager.switchToUpgrades();
	});

	add(Box.createVerticalStrut(25));


	JButton optionsButton = new JButton("Options");
	optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(optionsButton);

	add(Box.createVerticalStrut(25));

	JButton exitButton = new JButton("Exit");
	exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);;
	add(exitButton);
	exitButton.addActionListener(e -> {
	    int result = JOptionPane.showConfirmDialog(this,
						       "Are you sure you want to exit?", "Confirm Exit",
						       JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	    if (result == JOptionPane.YES_OPTION) {
		System.exit(0);
	    }
	});

	// Add some spacing between the buttons and the bottom of the panel
	add(Box.createVerticalGlue());
    }
}
