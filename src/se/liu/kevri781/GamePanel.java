package se.liu.kevri781;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
    private Thread thread;
    private volatile boolean running;
    private GameBackground background;
    private Player player;
    private PlayerDrawable playerDrawable;
    private Enemy enemy;
    private EnemyDrawable enemyDrawable;
    private boolean playerAlive = true;
//    private ArrayList<CharacterType> characterTypes = new ArrayList<>();
    public static final int GROUND_LEVEL = (GameViewer.screenSize.height * 9 / 10);
    private Random random = new Random();
    public static final int FPS = 60;
    public static ArrayList<Enemy> enemies = new ArrayList<>();

    public GamePanel() {
	setFocusable(true);
	setVisible(true);
	requestFocus();
	addKeyListener(this);
	thread = new Thread(this);

	// Load background image
	background = new GameBackground();

	// Creates the player
	player = new Player(0, 0, 135, 135); // * 0.5
	player.setY(player.groundCoord);
	player.setX(GameViewer.screenSize.width / 2 - player.getScaledWidth() / 2);
	playerDrawable = new PlayerDrawable(player);

	// Adds enemy types to the enemyTypes array
//	characterTypes.addAll(Arrays.asList(CharacterType.values()));
	// gets a random enemy type
//	CharacterType enemyType = characterTypes.get(random.nextInt(characterTypes.size()));

	// Creates an enemy
	enemy = new Enemy(CharacterType.SKELETON_WARRIOR, 0, 0, 128, 128);
	enemy.setY(enemy.groundCoord);
	enemy.setX(player.x + 2000);

	enemyDrawable = new EnemyDrawable(enemy);
	enemies.add(enemy);

	// Set the size of the panel to the size of the screen
	setPreferredSize(new Dimension(GameViewer.screenSize.width, GameViewer.screenSize.height));

	// Add a listener to the panel to resize the panel when the window is resized
	addComponentListener(new ComponentAdapter()
	{
	    @Override public void componentResized(ComponentEvent e) {
		background.componentResized(e);
	    }
	});

    }
    public void start() {
	running = true;
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
	playerDrawable.animationLogic(background);
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
    @Override public void keyPressed(KeyEvent e) {
	int keyCode = e.getKeyCode();
	if (!player.dead) {
	    switch (keyCode) {
		case KeyEvent.VK_LEFT:
		    playerDrawable.setDirection(Direction.LEFT);
		    background.moveBackground(player.speed, PlayerAction.RUN_LEFT);
		    break;
		case KeyEvent.VK_RIGHT:
		    playerDrawable.setDirection(Direction.RIGHT);
		    background.moveBackground(player.speed, PlayerAction.RUN_RIGHT);
		    break;
		case KeyEvent.VK_UP:
		    player.jump();
		    break;
		case KeyEvent.VK_DOWN:
		    background.moveBackground(player.speed, PlayerAction.CROUCH);
		    break;
		case KeyEvent.VK_SPACE:
		    player.attack();
		    break;
	    }
	} else {
	    background.stopBackground();
	    e.consume();
    	}
	playerDrawable.animationLogic(background);
    }

    @Override public void keyReleased(final KeyEvent e) {
	int keyCode = e.getKeyCode(); // 37 = left, 38 = up, 39 = right, 40 = down
	playerKeyReleased(keyCode);
    }
    private void playerKeyReleased(int keyCode) {
	if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT && player.isAboveGround()) {
	    background.stopBackground();
	}
	if (!player.isAboveGround()) {
	    background.stopBackground();
	}
	if (!player.dead) {
	    playerDrawable.stopSpriteAnimation();
	};
//	playerDrawable.stopAttack();
    }
}