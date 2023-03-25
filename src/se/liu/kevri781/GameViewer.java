package se.liu.kevri781;


import javax.swing.*;
import java.awt.*;

public class GameViewer {
    //TODO: Fix the attack animation so that you don't have to hold the attack button down to attack.
    //TODO: Fix the attack animation so that it doesn't play when the player is not moving.
    //TODO: Fix the attack animation so that it doesn't play when the player is not moving.
    //TODO: Fix all other panels: MenuPanel, GameOverPanel, PausePanel, OptionsPanel, etc...
    //TODO: Fix save/load system?
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static Dimension frameSize = null;
//    public static JFrame frame;

    public static void main(String[] args) {
	JFrame frame = new JFrame("Side Scroller Game");

//    	frame.setSize(1280, 720);
	frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Runs the game in fullscreen windowed mode.
//	frame.setUndecorated(true); // Hides the title bar.
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.setResizable(false);
	frameSize = frame.getSize();

	GamePanel panel = new GamePanel();
	panel.start();

	frame.add(panel, BorderLayout.CENTER);

	frame.setVisible(true);
    }
}
