package se.liu.kevri781;


import javax.swing.*;
import java.awt.*;

public class GameViewer {
    //TODO: Fix player animation freezing on the last frame wen the player is moving.
    //TODO: Fix the attack animation so that you don't have to hold the attack button down to attack.
    //TODO: Fix the attack animation so that it doesn't play when the player is not moving.
    //TODO: Fix it so that the game resizes when the window is resized.
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//    public static JFrame frame;

    public static void main(String[] args) {
	JFrame frame = new JFrame("Side Scroller Game");

	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	frame.setSize(screenSize.width / 2, screenSize.height / 2);
	frame.setResizable(false);



	GamePanel panel = new GamePanel();
	panel.start();
//	MenuPanel panel = new MenuPanel();
	frame.add(panel, BorderLayout.CENTER);

//	frame.pack();
	frame.setVisible(true);
    }
}
