package se.liu.kevri781;

import javax.swing.*;
import java.awt.*;

/**
 * The PanelManager class is responsible for switching between the different panels in the game.
 * It initializes the different panels and adds them to the frame.
 * It is responsible for starting the game thread.
 */
public class PanelManager {
    public final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private JFrame frame;
    private JPanel currentPanel;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private OptionsPanel optionsPanel;
    public static Dimension frameSize;

    public PanelManager() {
        frame = new JFrame("Side Scroller Game");

        menuPanel = new MenuPanel(this);
        optionsPanel = new OptionsPanel(this);
        gamePanel = new GamePanel(this);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Runs the game in fullscreen windowed mode.
        frame.setUndecorated(true); // Hides the title bar.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameSize = frame.getSize();

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
//        gamePanel = new GamePanel(this);
        switchPanel(gamePanel);
        gamePanel.start();
        gamePanel.requestFocusInWindow();
    }

    public void switchToUpgrades() {
        UpgradesPanel upgradesPanel = new UpgradesPanel(this);
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