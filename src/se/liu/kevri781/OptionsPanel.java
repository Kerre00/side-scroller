package se.liu.kevri781;

import javax.swing.*;
import java.awt.*;

/**
 * The OptionsPanel class represents the options panel of the game.
 * It is responsible for drawing the options panel and updating the options of
 * the game.
 * NOT FINISHED
 */
public class OptionsPanel extends JPanel
{
    private PanelManager panelManager;

    public OptionsPanel(PanelManager panelManager) {
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

	JLabel optionsLabel = new JLabel("Options");
	optionsLabel.setFont(new Font("Times New Roman", Font.BOLD, 50)); // sets the font and size
	optionsLabel.setForeground(Color.WHITE); // sets the color
	add(optionsLabel, c);

	c.gridy++;

	// Create the fullscreen checkbox
	JCheckBox fullscreenCheckBox = new JCheckBox("Toggle Fullscreen/Windowed");
	fullscreenCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
	fullscreenCheckBox.setForeground(Color.BLACK);
	fullscreenCheckBox.setPreferredSize(new Dimension(400, 50));
	fullscreenCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(fullscreenCheckBox, c);

	c.gridy++;

	// Create the dropdown menu items
	String[] resolutionItems = {
		"800x600 (4:3)",
		"1024x768 (4:3)",
		"1280x720 (16:9)",
		"1366x768 (16:9)",
		"1600x900 (16:9)",
		"1920x1080 (16:9)",
		"2560x1440 (16:9)",
		"3840x2160 (16:9)",
		"1280x960 (4:3)",
		"1400x1050 (4:3)",
		"1600x1200 (4:3)",
		"2048x1536 (4:3)",
		"2560x1920 (4:3)",
		"5120x3840 (4:3)"
	};

	// Create the JComboBox
	JComboBox<String> comboBox = new JComboBox<>(resolutionItems);
	comboBox.setPreferredSize(new Dimension(400, 50));
	comboBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
	comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
	add(comboBox);

	c.gridy++;
	c.gridwidth = GridBagConstraints.REMAINDER; // spans across all columns
	c.anchor = GridBagConstraints.NORTH; // aligns to the top
	c.weighty = 0; // resets the vertical weight
	c.insets = new Insets(50, 10, 10, 10); // sets the insets

	// Create the apply button
	JButton applyButton = new JButton("Apply");
	applyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	applyButton.setPreferredSize(new Dimension(400, 50));
	applyButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(applyButton, c);

	c.gridy++;

	JButton backButton = new JButton("Back");
	backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	backButton.setPreferredSize(new Dimension(400, 50));
	backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
	add(backButton, c);
	backButton.addActionListener(e -> {
	    panelManager.switchToMainMenu();
	});


    }
}

// TODO: Add an option for windowed/fullscreen
// TODO: Add an option for resolution
// TODO: Add an option for volume
// TODO: Add an option for keybinds