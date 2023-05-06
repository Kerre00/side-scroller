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
    private volatile boolean running;
    private GameBackground background;
    private Player player;
    private PlayerDrawable playerDrawable;
    private Enemy enemy;
    private EnemyDrawable enemyDrawable;
    private boolean playerAlive = true;
    private ArrayList<CharacterType> characterTypes = new ArrayList<>(Arrays.asList(CharacterType.values()));
    public static final int GROUND_LEVEL = (GameViewer.screenSize.height * 9 / 10);
    private Random random = new Random();
    public static final int FPS = 60;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    private long deadEnemyTimer = 0;
    private final long NEW_ENEMY_DELAY = 350; // 2 seconds
    private HUD hud;
    private PanelManager panelManager;

    public GamePanel(PanelManager panelManager) {
	this.panelManager = panelManager;
	setFocusable(true);
	setVisible(true);
	requestFocusInWindow();
	addKeyListener(this);
	thread = new Thread(this);

	// Load background image
	background = new GameBackground();

	// Sets the game to its initial state
	resetGame();

	// Set the size of the panel to the size of the screen
	setPreferredSize(new Dimension(GameViewer.screenSize.width, GameViewer.screenSize.height));

	// Listener to the panel to resize the panel when the window is resized
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

    public void resetGame() {

	player = new Player(0, 0, 135, 135, background); // * 0.5
	player.setY(player.groundCoord);
	player.setX(GameViewer.screenSize.width / 2 - player.getScaledWidth() / 2);
	playerDrawable = new PlayerDrawable(player);

	enemy = new Enemy(CharacterType.SKELETON_WARRIOR, 0, 0, 128, 128);
	enemy.setY(enemy.groundCoord);
	enemy.setX(player.x + 2000);

	enemyDrawable = new EnemyDrawable(enemy);

	enemies.clear();
	enemies.add(enemy);

	deadEnemyTimer = 0;
	hud = new HUD(player, enemy);

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
	hud.update();
	checkSpawnNewEnemy();
	for (Enemy enemy : enemies) {
	    enemy.update();
	    enemy.Ai(player);
	    enemy.moveWithBackground(background);
	}
	enemyDrawable.animationLogic();
	playerDrawable.animationLogic(background);
    }

    private void checkSpawnNewEnemy() {
	if (enemy.isDead() && deadEnemyTimer == 0) {
	    deadEnemyTimer = System.currentTimeMillis(); // start the timer
	}

	if (deadEnemyTimer > 0 && System.currentTimeMillis() - deadEnemyTimer >= NEW_ENEMY_DELAY) {
	    enemy = new Enemy(CharacterType.SKELETON_WARRIOR, 0, 0, 128, 128);
	    enemy.setY(enemy.groundCoord);
	    enemy.setX(player.x + 2000);
	    enemyDrawable = new EnemyDrawable(enemy);
	    enemies.add(enemy);
	    deadEnemyTimer = 0; // reset the timer
	    hud.setEnemy(enemy);
	}
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	background.draw(g);
	enemyDrawable.draw(g);
	playerDrawable.draw(g);
	background.drawForeGround(g);
	hud.draw(g);
    }
    @Override public void keyTyped(final KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {
	int keyCode = e.getKeyCode();
	if (!player.dead) {
	    switch (keyCode) {
		case KeyEvent.VK_LEFT:
		    playerDrawable.setDirection(Direction.LEFT);
		    player.moveLeft();
		    break;
		case KeyEvent.VK_RIGHT:
		    playerDrawable.setDirection(Direction.RIGHT);
		    player.moveRight();
		    break;
		case KeyEvent.VK_UP:
		    player.jump();
		    break;
		case KeyEvent.VK_DOWN: // Future crouch? No animation for it yet
		    break;
		case KeyEvent.VK_SPACE:
		    player.attack();
		    break;
	    }
	} else {
	    if (keyCode == KeyEvent.VK_SPACE) {
//		stop();
		resetGame();
		stop();
		player.setDead(false);
		panelManager.switchToMainMenu();
	    }
	    player.stop();
	    e.consume();
    	}
	playerDrawable.animationLogic(background);
    }

    @Override public void keyReleased(final KeyEvent e) {
	int keyCode = e.getKeyCode();
	playerKeyReleased(keyCode);
    }
    private void playerKeyReleased(int keyCode) {
	if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT && player.isAboveGround()) {
	    player.stop();
	}
	if (!player.isAboveGround()) {
	    player.stop();
	}
	if (!player.dead) {
	    playerDrawable.stopSpriteAnimation();
	}
    }
}