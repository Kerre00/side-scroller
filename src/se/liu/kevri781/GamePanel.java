package se.liu.kevri781;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
    private Thread thread;
    private boolean running;
    private GameBackground background;
    private Player player;
    private PlayerDrawable playerDrawable;
    private Enemy enemy;
    private EnemyDrawable enemyDrawable;
    private boolean playerAlive = true;
    private ArrayList<CharacterType> characterTypes = new ArrayList<>();
//    private int groundLevel = (int) (GameViewer.screenSize.height * 0.5);
    private Random random = new Random();
    private final int FPS = 60;
    public static ArrayList<Enemy> enemies = new ArrayList<>();

    public GamePanel() {
	setFocusable(true);
	setVisible(true);
	requestFocus();
	addKeyListener(this);

	// Load background image
	background = new GameBackground();

	// Creates the player
	player = new Player(GameViewer.screenSize.width / 3, (int) (GameViewer.screenSize.height * 0.6), 135, 135); // * 0.5
	playerDrawable = new PlayerDrawable(player.x, player.y, player.width, player.height, player);

	// Adds enemy types to the enemyTypes array
	characterTypes.addAll(Arrays.asList(CharacterType.values()));
	// gets a random enemy type
//	CharacterType enemyType = characterTypes.get(random.nextInt(characterTypes.size()));

	// Creates an enemy
	enemy = new Enemy(CharacterType.SKELETON_WARRIOR, GameViewer.screenSize.width / 3, (int) (GameViewer.screenSize.height * (0.543 + 0.09)), 128, 128);
	enemyDrawable = new EnemyDrawable(GameViewer.screenSize.width, (int) (GameViewer.screenSize.height * (0.543 + 0.09)), 128, 128, enemy);
	enemies.add(enemy);

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
	enemy.update();
	enemy.Ai(player);
	enemyDrawable.animationLogic();
	playerDrawable.animationLogic();
	enemy.moveWithBackground(background);
    }
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	background.draw(g);
	enemyDrawable.draw(g);
	playerDrawable.draw(g);
	background.drawForeGround(g);
    }
    @Override public void keyTyped(final KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
	int keyCode = e.getKeyCode();
	if (!player.dead) {
	    switch (keyCode) {
		case KeyEvent.VK_LEFT:
		    player.moveLeft();
		    background.moveBackground(player.speed, PlayerAction.RUN_LEFT);
		    break;
		case KeyEvent.VK_RIGHT:
		    player.moveRight();
		    background.moveBackground(player.speed, PlayerAction.RUN_RIGHT);
		    break;
		case KeyEvent.VK_UP:
//		background.moveBackground(5, Direction.UP);
		    break;
		case KeyEvent.VK_DOWN:
		    background.moveBackground(player.speed, PlayerAction.CROUCH);
		    break;
		case KeyEvent.VK_SPACE:
		    break;
	    }
	} else {
	    background.stopBackground();
	    e.consume();
    	}
	playerDrawable.animationLogic();
    }

    @Override public void keyReleased(final KeyEvent e) {
	background.stopBackground();
	if (!player.dead) {
	    playerDrawable.stopSpriteAnimation();
	}
	    enemy.moveWithBackground(background);
    }

    public void setRunning(boolean running) {
	this.running = running;
    }


}