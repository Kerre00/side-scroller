package se.liu.kevri781;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

// Import screensize from PanelManager
import static se.liu.kevri781.PanelManager.SCREEN_SIZE;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
    private Thread thread;
    private volatile boolean running; // volatile = the variable is accessed by multiple threads
    private GameBackground background;
    private Player player;
    private PlayerDrawable playerDrawable;
    private Enemy enemy;
    private EnemyDrawable enemyDrawable;
    public static final int GROUND_LEVEL = (SCREEN_SIZE.height * 9 / 10);
    private Random random = new Random();
    public static final int FPS = 60;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    private long deadEnemyTimer = 0;
    private final long NEW_ENEMY_DELAY = 350; // 2 seconds
    private HUD hud;
    private PanelManager panelManager;
    private UpgradesManager upgradesManager;
    private CharacterType[] unlockedEnemies;
    private final static int ENEMY_SPAWN_DISTANCE = 2000;

    public GamePanel(PanelManager panelManager) {
	this.panelManager = panelManager;
	this.upgradesManager = new UpgradesManager();

	// Load unlocked enemies
	this.unlockedEnemies = upgradesManager.getUnlockedEnemies();

	setFocusable(true);
	setVisible(true);
	requestFocusInWindow();
	addKeyListener(this);

	// Load background image
	background = new GameBackground();

	// Sets the game to its initial state
	resetGame();

	// Set the size of the panel to the size of the screen
	setPreferredSize(new Dimension(SCREEN_SIZE.width, SCREEN_SIZE.height));

	// Listener to the panel to resize the panel when the window is resized
	addComponentListener(new ComponentAdapter()
	{
	    @Override public void componentResized(ComponentEvent e) {
		background.componentResized(e);
	    }
	});

    }
    public void start() {
	thread = new Thread(this);
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

	unlockedEnemies = upgradesManager.getUnlockedEnemies();

	player = new Player(0, 0, 135, 135, background);
	player.setY(player.groundCoord);
	player.setX(SCREEN_SIZE.width / 2 - player.getScaledWidth() / 2);
	playerDrawable = new PlayerDrawable(player);

	enemy = new Enemy(CharacterType.SKELETON_WARRIOR, 0, 0, 128, 128);
	enemy.setY(enemy.groundCoord);
	enemy.setX(player.x + ENEMY_SPAWN_DISTANCE);

	enemyDrawable = new EnemyDrawable(enemy);

	enemies.clear();
	enemies.add(enemy);

	deadEnemyTimer = 0;
	hud = new HUD(player, enemy);

    }

    public void run() {
	resetGame();
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
	    int randomIndex = random.nextInt(unlockedEnemies.length);
	    CharacterType randomEnemyType = unlockedEnemies[randomIndex];
	    while (randomEnemyType == null) {
		randomIndex = random.nextInt(unlockedEnemies.length);
		randomEnemyType = unlockedEnemies[randomIndex];
	    }
	    enemy = new Enemy(randomEnemyType, 0, 0, 128, 128);
	    enemy.setY(enemy.groundCoord);
	    enemy.setX(player.x + ENEMY_SPAWN_DISTANCE);
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
		case KeyEvent.VK_DOWN: // TODO: Future crouch? No animation for it yet
		    break;
		case KeyEvent.VK_SPACE:
		    if (!player.isAttacking()) {
			player.attack();
		    }
		    break;
	    }
	} else {
	    if (keyCode == KeyEvent.VK_SPACE) {
		stop();
		GameProgress.addMoney(player.getMoney());
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
	if ((keyCode == KeyEvent.VK_LEFT  && player.isAboveGround()) ||
	    (keyCode == KeyEvent.VK_RIGHT && player.isAboveGround())) {
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