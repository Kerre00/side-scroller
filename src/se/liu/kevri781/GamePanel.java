package se.liu.kevri781;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

/**
 * The GamePanel class represents the game panel where the game is played.
 * It extends JPanel and implements Runnable and KeyListener interfaces.
 * It is responsible for drawing the game and updating the game state.
 * It also handles keyboard input.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener
{
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Thread thread = null;
    private volatile boolean running;
    private GameBackground background;
    private Player player = null;
    private PlayerDrawable playerDrawable = null;
    private Enemy enemy = null;
    private EnemyDrawable enemyDrawable = null;
    private int groundLevel = (screenSize.height * 9 / 10);
    private Random random = new Random();
    private List<Enemy> enemies = new ArrayList<>();
    private long deadEnemyTimer = 0;
    private HUD hud = null;
    private PanelManager panelManager;
    private UpgradesManager upgradesManager;
    private EnemyType[] unlockedEnemies;
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
	setPreferredSize(new Dimension(screenSize.width, screenSize.height));

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

    private void resetGame() {

	unlockedEnemies = upgradesManager.getUnlockedEnemies(gameProgress);

	player = new Player(0, 0, 135, 135, background, gameProgress, groundLevel);
	player.setY(player.groundCoordSpriteOffset);
	final int playerMidXCoord = screenSize.width / 2 - player.getScaledWidth() / 2;
	player.setX(playerMidXCoord);
	playerDrawable = new PlayerDrawable(player);

	enemy = new Enemy(EnemyType.SKELETON_WARRIOR, 0, 0, 128, 128, gameProgress, groundLevel);
	enemy.setY(enemy.groundCoordSpriteOffset);
	enemy.setX(player.x + ENEMY_SPAWN_DISTANCE);

	enemyDrawable = new EnemyDrawable(enemy);

	enemies.clear();
	enemies.add(enemy);

	deadEnemyTimer = 0;
	hud = new HUD(player, enemy);

    }

    public void run() {
	resetGame();

	long lastTime = System.nanoTime();
	final int framesPerSecond = 60;
	double nanosecondsPerFrame = 1000000000.0 / framesPerSecond;
	double deltaTime = 0;	// The time since the last update

	while (running) {
	    long currentTime = System.nanoTime();
	    deltaTime += (currentTime - lastTime) / nanosecondsPerFrame;
	    lastTime = currentTime;

	    while (deltaTime >= 1) {
		update();
		deltaTime--;
	    }

	    repaint();
	}
    }

    private void update() {
	background.update();
	player.update();
	hud.update();
	checkSpawnNewEnemy();
	updatePlayerEnemyCollision();
	for (Enemy enemy : enemies) {
	    enemy.update();
	    enemy.decisionLogic(player);
	    enemy.moveWithBackground(background);
	}
	enemyDrawable.animationLogic();
	playerDrawable.animationLogic(background);
    }

    private void checkSpawnNewEnemy() {
	if (enemy.isDead() && deadEnemyTimer == 0) {
	    deadEnemyTimer = System.currentTimeMillis(); // start the timer
	}

	final long enemySpawnDelay = 350;
	if (deadEnemyTimer > 0 && System.currentTimeMillis() - deadEnemyTimer >= enemySpawnDelay) {
	    int randomIndex = random.nextInt(unlockedEnemies.length);
	    EnemyType randomEnemyType = unlockedEnemies[randomIndex];
	    while (randomEnemyType == null) {
		randomIndex = random.nextInt(unlockedEnemies.length);
		randomEnemyType = unlockedEnemies[randomIndex];
	    }
	    enemy = new Enemy(randomEnemyType, 0, 0, 128, 128, gameProgress, groundLevel);
	    enemy.setY(enemy.groundCoordSpriteOffset);
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
    private void updatePlayerEnemyCollision() {
	/**
	 * Updates the player's position and applies gravity. And also checks for collisions with enemies.
	 */

	// Checks if the player has died
	if (player.getHealth() <= 0) {
	    player.setDead(true);
	}

	// Checks if the player is recovering and sets recovering to false if the player has been
	// recovering for longer than the invincibility timer.
	if (player.isRecovering()) {
	    long elapsed = (System.nanoTime() - player.getRecoverTimer()) / 1000000;
	    if (elapsed > player.maxRecoveryTime) {
		player.setRecovering(false);
		player.resetRecoveryTimer();
	    }
	}

	for (Enemy e : enemies) {
	    int dist = player.xDistanceTo(e, false);

	    // Checks if the enemy is recovering and sets recovering to false if the enemy has been
	    // recovering for longer than the invincibility timer.
	    if (e.isRecovering()) {
		long elapsed = (System.nanoTime() - e.getRecoverTimer()) / 1000000;
		if (elapsed > e.maxRecoveryTime) {
		    e.setRecovering(false);
		    e.resetRecoveryTimer();
		}
	    }

	    // If the enemy is attacking and the player is close enough, the player takes damage
	    if (e.isAttacking() && !e.isDead()) {
		if (dist <= e.getAttackReach() && !player.isRecovering()) {
		    player.reduceHealth(e.getDamage());
		    player.setRecovering(true);
		    player.setRecoverTimer(System.nanoTime());
		}
	    }

	    // If the player is close enough to an enemy, the player loses a life
	    if (dist < 100 && !player.isRecovering() && !e.isDead()) {
		player.reduceHealth(1);
		player.setRecovering(true);
		player.setRecoverTimer(System.nanoTime());
	    }

	    // If the player is attacking and the enemy is close enough, the enemy takes damage
	    if (player.isAttacking() && dist <= player.getAttackReach() && !e.recovering) {
		e.reduceHealth(player.getDamage());
		e.setRecovering(true);
		e.setRecoverTimer(System.nanoTime());

		if (e.getHealth() <= 0 && !e.isDead()) {
		    e.setDead(true);
		    player.addMoney(e.getMoneyValue());
		}
	    }
	}
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
}
