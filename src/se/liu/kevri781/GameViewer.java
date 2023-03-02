package se.liu.kevri781;


import javax.swing.*;

public class GameViewer {

    public static void main(String[] args) {
	JFrame frame = new JFrame("Side Scroller Game");
	frame.setSize(928, 793);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	GameBackground background = new GameBackground(12);
	background.setImagesPath("resources/images/gamebackgrounds");

	frame.add(background);
	frame.setVisible(true);
    }
}