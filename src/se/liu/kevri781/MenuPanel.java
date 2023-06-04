package se.liu.kevri781;

import javax.swing.*;
import java.awt.*;

/**
 * The MenuPanel class represents the main menu panel.
 * It is responsible for drawing the main menu panel using Swing.
 * It uses the PanelManager class to switch to other panels.
 * It is the first panel that is shown when the game is started.
 */
public class MenuPanel extends JPanel {

    public MenuPanel(PanelManager panelManager) {
	setBackground(Color.DARK_GRAY);

	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	add(Box.createVerticalStrut(250));

	JLabel titleLabel = new JLabel("Main Menu");
	titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
	titleLabel.setForeground(Color.WHITE);
	titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(titleLabel);

	add(Box.createVerticalStrut(100));

	JButton newGameButton = new JButton("New Game");
	newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	Dimension menuButtonSize = new Dimension(300, 100);
	newGameButton.setMaximumSize(menuButtonSize);
	newGameButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(newGameButton);
	newGameButton.addActionListener(e -> panelManager.switchToGame());

	add(Box.createVerticalStrut(25));

	// TODO: Implement game saving
	JButton loadGameButton = new JButton("<html><s>Load Game</s></html>");
	loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	loadGameButton.setMaximumSize(menuButtonSize);
	loadGameButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(loadGameButton);
//	loadGameButton.addActionListener(e -> panelManager.switchToGame());

	add(Box.createVerticalStrut(25));

	JButton upgradesButton = new JButton("Upgrades");
	upgradesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	upgradesButton.setMaximumSize(menuButtonSize);
	upgradesButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(upgradesButton);
	upgradesButton.addActionListener(e -> panelManager.switchToUpgrades());

	add(Box.createVerticalStrut(25));

	JButton optionsButton = new JButton("<html><s>Options</s></html>");
	optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	optionsButton.setMaximumSize(menuButtonSize);
	optionsButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(optionsButton);
	optionsButton.addActionListener(e -> panelManager.switchToOptions());

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
	    if (result == JOptionPane.YES_OPTION) { System.exit(0); }
	});

	add(Box.createVerticalGlue());

    }
}
