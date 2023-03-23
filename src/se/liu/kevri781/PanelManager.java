package se.liu.kevri781;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelManager {

    private JFrame frame;
    private JPanel currentPanel;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
//    private PausePanel pausePanel;
//    private EndGamePanel endGamePanel;

    public PanelManager() {
        frame = new JFrame("Game");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
//        pausePanel = new PausePanel(this);
//        endGamePanel = new EndGamePanel(this);

        currentPanel = menuPanel;
        frame.add(currentPanel);

        frame.setVisible(true);
    }

    public void switchToMainMenu() {
        switchPanel(menuPanel);
    }

    public void switchToGame() {
        switchPanel(gamePanel);
    }

//    public void switchToPause() {
//        switchPanel(pausePanel);
//    }

//    public void switchToEndGame() {
//        switchPanel(endGamePanel);
//    }

    private void switchPanel(JPanel panel) {
        frame.remove(currentPanel);
        currentPanel = panel;
        frame.add(currentPanel);
        frame.revalidate();
        frame.repaint();
    }

}