package se.liu.kevri781;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    // Represents the main menu panel
    private PanelManager panelManager;
    public MenuPanel() {
	// Set the layout manager for the panel
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	// Create the title label and add it to the panel
	JLabel titleLabel = new JLabel("Main Menu");
	titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(titleLabel);

	// Add spacing between the title and the buttons
	add(Box.createVerticalStrut(20));

	// Create the buttons and add them to the panel
	JButton newGameButton = new JButton("New Game");
	newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(newGameButton);
	newGameButton.addActionListener(e -> {
	    GamePanel gamePanel = new GamePanel();
	    panelManager.switchToGame();
	});

	JButton optionsButton = new JButton("Options");
	optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(optionsButton);

	JButton exitButton = new JButton("Exit");
	exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(exitButton);

	// Add some spacing between the buttons and the bottom of the panel
	add(Box.createVerticalGlue());
    }
}
