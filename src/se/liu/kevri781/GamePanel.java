package se.liu.kevri781;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

//TODO: Fix player animation freezing on the last frame wen the player is moving.
//TODO: Fix the attack animation so that you don't have to hold the attack button down to attack.

public class GamePanel extends JPanel implements Runnable, KeyListener
{
    private Thread thread;
    private boolean running;
    private GameBackground background;
    private Player player;
    private PlayerDrawable playerDrawable;
    private final int FPS = 60;
    public GamePanel() {
	setFocusable(true);
	setVisible(true);
	requestFocus();
	addKeyListener(this);

	// Load background image
	background = new GameBackground();

	player = new Player(GameViewer.screenSize.width / 3, (int) (GameViewer.screenSize.height * 0.5), 135, 135);
	playerDrawable = new PlayerDrawable(player.x, player.y, player.width, player.height);

	// Set the size of the panel to the size of the screen
	setPreferredSize(new Dimension(GameViewer.screenSize.width, GameViewer.screenSize.height));

//	// Add a listener to the panel to resize the panel when the window is resized
	addComponentListener(new ComponentAdapter()
	{
	    @Override public void componentResized(ComponentEvent e) {
		background.componentResized(e);
	    }
	});

    }



    public void start() {
	running = true;
	thread = new Thread(this);
	thread.start();
    }

    public void stop() {
	running = false;
	try {
	    thread.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void run() {
	while (running) {
	    update();
	    repaint();
	    try {
		Thread.sleep(1000 / FPS);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    private void update() {
	background.update();
	player.update();
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	background.draw(g);
	playerDrawable.draw(g);
    }

    @Override public void keyTyped(final KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
	int keyCode = e.getKeyCode();
	switch(keyCode) {
	    case KeyEvent.VK_LEFT:
//		System.out.println("Left");
		background.moveBackground(5, Controls.RUN_LEFT);
		playerDrawable.setSpriteAnimation(Controls.RUN_LEFT);
		break;
	    case KeyEvent.VK_RIGHT :
//		System.out.println("Right");
		background.moveBackground(5, Controls.RUN_RIGHT);
		playerDrawable.setSpriteAnimation(Controls.RUN_RIGHT);
		break;
	    case KeyEvent.VK_UP:
//		System.out.println("Jump");
//		background.moveBackground(5, Direction.UP);
		break;
	    case KeyEvent.VK_DOWN:
//		System.out.println("Down");
		background.moveBackground(5, Controls.CROUCH);
		break;
	    case KeyEvent.VK_SPACE:
//		System.out.println("Attack");
		playerDrawable.setSpriteAnimation(Controls.ATTACK);
		break;
	}
    }

    @Override public void keyReleased(final KeyEvent e) {
	background.stopBackground();
	playerDrawable.setSpriteAnimation();
    }

}