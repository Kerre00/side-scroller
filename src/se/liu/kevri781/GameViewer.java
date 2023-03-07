package se.liu.kevri781;


import javax.swing.*;
import java.awt.*;

public class GameViewer {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
	JFrame frame = new JFrame("Side Scroller Game");

	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




	GamePanel background = new GamePanel();

	background.start();
	frame.add(background, BorderLayout.CENTER);

	frame.pack();
	frame.setVisible(true);
    }
}