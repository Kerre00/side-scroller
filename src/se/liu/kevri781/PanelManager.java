package se.liu.kevri781;

import javax.swing.*;
import java.awt.*;

public class PanelManager {

    private JFrame frame;
    private JPanel currentPanel;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private UpgradesPanel upgradesPanel;
    private OptionsPanel optionsPanel;

    public PanelManager() {
        frame = new JFrame("Side Scroller Game");

        menuPanel = new MenuPanel(this);
        upgradesPanel = new UpgradesPanel(this);
        optionsPanel = new OptionsPanel(this);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Runs the game in fullscreen windowed mode.
        frame.setUndecorated(true); // Hides the title bar.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setVisible(true);

        currentPanel = menuPanel;

        frame.add(currentPanel);

        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        PanelManager panel = new PanelManager();
    }

    public void switchToMainMenu() {
        switchPanel(menuPanel);
    }

    public void switchToGame() {
        gamePanel = new GamePanel(this);
        switchPanel(gamePanel);
        gamePanel.start();
        gamePanel.requestFocusInWindow();
    }

    public void switchToUpgrades() {
        switchPanel(upgradesPanel);
    }

        public void switchToOptions() {
                switchPanel(optionsPanel);
        }

    private void switchPanel(JPanel panel) {
        frame.remove(currentPanel);
        currentPanel = panel;
        frame.add(currentPanel);
        frame.revalidate();
        frame.repaint();
    }

}