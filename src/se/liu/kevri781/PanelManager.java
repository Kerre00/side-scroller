package se.liu.kevri781;

import javax.swing.*;

/**
 * The PanelManager class is responsible for switching between the different panels in the game.
 * It initializes the different panels and adds them to the frame.
 * It is responsible for starting the game thread.
 */
public class PanelManager {
    private JFrame frame = new JFrame("Side Scroller Game");
    private JPanel currentPanel;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private OptionsPanel optionsPanel;
    private GameProgress gameProgress;

    public PanelManager() {
        gameProgress = new GameProgress();
        gameProgress.setUpgradeLevels();

        menuPanel = new MenuPanel(this);
        optionsPanel = new OptionsPanel(this);
        gamePanel = new GamePanel(this, gameProgress);

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
        new PanelManager();
    }

    public void switchToMainMenu() {
        switchPanel(menuPanel);
    }

    public void switchToGame() {
//        gamePanel = new GamePanel(this);
        switchPanel(gamePanel);
        gamePanel.start();
        gamePanel.requestFocusInWindow();
    }

    public void switchToUpgrades() {
        UpgradesPanel upgradesPanel = new UpgradesPanel(this, gameProgress);
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