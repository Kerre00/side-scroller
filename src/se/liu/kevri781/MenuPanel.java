package se.liu.kevri781;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    // Represents the main menu panel

    private PanelManager panelManager;
    private Dimension menuButtonSize = new Dimension(300, 100);
    private GamePanel gamePanel;
    public MenuPanel(PanelManager panelManager) {
	this.panelManager = panelManager;

	// Set the background color of the panel
	setBackground(Color.DARK_GRAY);

	// Set the layout manager for the panel
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	// Add spacing between the title and the top of the panel
	add(Box.createVerticalStrut(250));

	// Create the title label and add it to the panel
	JLabel titleLabel = new JLabel("Main Menu");
	titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
	titleLabel.setForeground(Color.WHITE);
	titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(titleLabel);

	add(Box.createVerticalStrut(100));

	// Create the buttons and add them to the panel
	JButton newGameButton = new JButton("New Game");
	newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	newGameButton.setMaximumSize(menuButtonSize);
	newGameButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(newGameButton);
	newGameButton.addActionListener(e -> {
	    panelManager.switchToGame();
	});

	add(Box.createVerticalStrut(25));

	// Load game button TODO: Implement game saving
	JButton loadGameButton = new JButton("<html><s>Load Game</s></html>");
	loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	loadGameButton.setMaximumSize(menuButtonSize);
	loadGameButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(loadGameButton);
//	loadGameButton.addActionListener(e -> {
//	    panelManager.switchToGame();
//	});

	add(Box.createVerticalStrut(25));


	// Create upgrades button
	JButton upgradesButton = new JButton("Upgrades");
	upgradesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	upgradesButton.setMaximumSize(menuButtonSize);
	upgradesButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(upgradesButton);
	upgradesButton.addActionListener(e -> {
	    panelManager.switchToUpgrades();
	});

	add(Box.createVerticalStrut(25));


	JButton optionsButton = new JButton("<html><s>Options</s></html>");
	optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	optionsButton.setMaximumSize(menuButtonSize);
	optionsButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(optionsButton);
	optionsButton.addActionListener(e -> {
	    panelManager.switchToOptions();
	});

	add(Box.createVerticalStrut(25));

	JButton exitButton = new JButton("Exit");
	exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	exitButton.setMaximumSize(menuButtonSize);
	exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(exitButton);
	exitButton.addActionListener(e -> {
	    int result = JOptionPane.showConfirmDialog(this,
						       "Are you sure you want to exit?", "Confirm Exit",
						       JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

	    if (result == JOptionPane.YES_OPTION) {
		System.exit(0);
	    }
	});

	// Add some spacing between the buttons and the bottom of the panel
	add(Box.createVerticalGlue());

    }
}


// How to add icons to buttons:
//	newGameButton.setIcon(new ImageIcon("resources/images/hud/hp_100.png"));
//	newGameButton.setOpaque(false);
//	newGameButton.setContentAreaFilled(false);
//	newGameButton.setBorderPainted(false);
