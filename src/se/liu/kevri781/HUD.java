package se.liu.kevri781;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * The HUD class represents the heads-up display that appears on the screen during gameplay.
 * It contains the player's health bar, the enemy's health bar, the player's money, and the player's damage indicator.
 * It implements the Drawable interface and extends the Player class because it needs to know the player's health and money.
 */
public class HUD extends Player implements Drawable
{
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Image currentHUDImage = null;
    private Player player;
    private Enemy enemy;
    private int healthBarX;
    private int healthBarY;
    public HUD(Player player, Enemy enemy) {
        super(0, 0, 0, 0, null, player.getGameProgress(), player.groundLevel);
        try {
            final URL hud = ClassLoader.getSystemResource("images/hud/hud_spritesheet.png");
            currentHUDImage = ImageIO.read(hud);
        } catch (IOException e) {
            e.printStackTrace();
            Logger logger = Logger.getLogger(HUD.class.getName());
            logger.severe("Error loading HUD spritesheet");
        }
        this.player = player;
        this.enemy = enemy;
    }

    public void draw(Graphics g) {
        if(player.isDead()) {
            drawGameOver(g);
        } else {
            drawHealthBars(g);
            drawHealthNumber(g);
            drawMoney(g);
            if (player.isGettingHit()) {
                drawEnemyDamage(g);
            } if (enemy.isGettingHit()) {
                drawPlayerDamage(g);
            } if (enemy.isDead()) {
                drawMoneyGain(g);
            }
        }
    }
    private void drawGameOver(Graphics g) {
        try {
            final URL gameOver = ClassLoader.getSystemResource("images/hud/game_over.png");
            currentHUDImage = ImageIO.read(gameOver);
        } catch (IOException e) {
            e.printStackTrace();
            Logger logger = Logger.getLogger(HUD.class.getName());
            logger.severe("Error loading game over image");
        }

        x = screenSize.width / 2 - currentHUDImage.getWidth(null) / 2;
        y = screenSize.height / 2 - currentHUDImage.getHeight(null) / 2;
        g.drawImage(currentHUDImage, x, y, null);
    }
    private void drawHealthBars(Graphics g) {
        int healthWidth = 119;
        int healthHeight = 7;
        int healthX = 133;
        int healthY = 4;

        int roundedHealth = Math.round((float) player.health / player.maxHealth * healthWidth);

        if (player.x != healthBarX || player.y != healthBarY) {
            healthBarX = player.x + player.getScaledWidth() / 2 - healthWidth / 2;
            healthBarY = player.y + player.getScaledHeight() / 3;
        }

        healthWidth = roundedHealth;

        g.drawImage(currentHUDImage, healthBarX, healthBarY, healthBarX + healthWidth, healthBarY + healthHeight,
                    healthX, healthY, healthX + healthWidth, healthY + healthHeight, null);

        if (enemy != null) {
            healthWidth = 119;
            roundedHealth = Math.round((float) enemy.health / enemy.maxHealth * healthWidth);

            healthBarX = enemy.x + enemy.getScaledWidth() / 2 - healthWidth / 2;
            healthBarY = enemy.y + enemy.getScaledHeight() / 3;

            healthWidth = roundedHealth;

            g.drawImage(currentHUDImage, healthBarX, healthBarY, healthBarX + healthWidth, healthBarY + healthHeight,
                        healthX, healthY, healthX + healthWidth, healthY + healthHeight, null);

        }
    }


    private void drawMoney(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.setColor(Color.YELLOW);
        g.drawString(player.getMoney() + " $", 100, 150);
    }
    private void drawHealthNumber(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.setColor(Color.RED);
        g.drawString(player.health + "/" + player.maxHealth + " HP", 100, 100);
    }
    private void drawPlayerDamage(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.setColor(Color.white);
        g.drawString("-" + player.getDamage(), enemy.x + enemy.getScaledWidth() / 2, enemy.y + 100);
    }
    private void drawEnemyDamage(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.setColor(Color.RED);
        g.drawString("-" + enemy.getDamage(), player.x + player.getScaledHeight() / 2, player.y + 100);
    }
    private void drawMoneyGain(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.setColor(Color.YELLOW);
        g.drawString("+" + enemy.getMoneyValue() + " $", player.x + player.getScaledWidth() / 2, player.y + 100);
    }
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
