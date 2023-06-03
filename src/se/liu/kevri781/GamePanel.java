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

/**
 * The GamePanel class represents the game panel where the game is played.
 * It extends JPanel and implements Runnable and KeyListener interfaces.
 * It is responsible for drawing the game and updating the game state.
 * It also handles keyboard input.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener
{
    private Thread thread = null;
    private volatile boolean running;
    private GameBackground background;
    private Player player = null;
    private PlayerDrawable playerDrawable = null;
    private Enemy enemy = null;
    private EnemyDrawable enemyDrawable = null;
    public final int GROUND_LEVEL = (SCREEN_SIZE.height * 9 / 10);
    private Random random = new Random();
    public final int FPS = 60;
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    private long deadEnemyTimer = 0;
    private final long NEW_ENEMY_DELAY = 350;
    private HUD hud = null;
    private PanelManager panelManager;
    private UpgradesManager upgradesManager;
    private CharacterType[] unlockedEnemies;
    private final static int ENEMY_SPAWN_DISTANCE = 2000;
    private GameProgress gameProgress;

    public GamePanel(PanelManager panelManager, GameProgress gameProgress) {
	this.gameProgress = gameProgress;

	this.panelManager = panelManager;
	this.upgradesManager = new UpgradesManager();

	// Load unlocked enemies
	this.unlockedEnemies = upgradesManager.getUnlockedEnemies(gameProgress);

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

	unlockedEnemies = upgradesManager.getUnlockedEnemies(gameProgress);

	player = new Player(0, 0, 135, 135, background, gameProgress, GROUND_LEVEL);
	player.setY(player.groundCoord);
	player.setX(SCREEN_SIZE.width / 2 - player.getScaledWidth() / 2);
	playerDrawable = new PlayerDrawable(player);

	enemy = new Enemy(CharacterType.SKELETON_WARRIOR, 0, 0, 128, 128, gameProgress, GROUND_LEVEL);
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
	    enemy = new Enemy(randomEnemyType, 0, 0, 128, 128, gameProgress, GROUND_LEVEL);
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
		gameProgress.addMoney(player.getMoney());
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
    public int getGROUND_LEVEL() {
	return GROUND_LEVEL;
    }
}