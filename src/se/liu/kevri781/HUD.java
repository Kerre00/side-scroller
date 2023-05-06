package se.liu.kevri781;

import java.awt.*;

public class HUD extends Player implements Drawable
{
    private Image currentHUDImage = Toolkit.getDefaultToolkit().getImage("resources/images/hud/hud_spritesheet.png");
    private Player player;
    private Enemy enemy;
    private int healthBarX;
    private int healthBarY;
    private Dimension screenDimension = GameViewer.screenSize;
    public HUD(Player player, Enemy enemy) {
        super(0, 0, 0, 0, null);
        this.player = player;
        this.enemy = enemy;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
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
    public void drawGameOver(Graphics g) {
        currentHUDImage = Toolkit.getDefaultToolkit().getImage("resources/images/hud/game_over.png");
        x = screenDimension.width / 2 - currentHUDImage.getWidth(null) / 2;
        y = screenDimension.height / 2 - currentHUDImage.getHeight(null) / 2;
        g.drawImage(currentHUDImage, x, y, null);
    }
    public void drawHealthBars(Graphics g) {
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


    public void drawMoney(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.setColor(Color.YELLOW);
        g.drawString(player.getMoney() + " $", 100, 150);
    }
    public void drawHealthNumber(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.setColor(Color.RED);
        g.drawString(player.health + "/" + player.maxHealth + " HP", 100, 100);
    }
    public void drawPlayerDamage(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.setColor(Color.white);
        g.drawString("-" + player.getDamage(), enemy.x + enemy.getScaledWidth() / 2, enemy.y + 100);
    }
    public void drawEnemyDamage(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.setColor(Color.RED);
        g.drawString("-" + enemy.getDamage(), player.x + player.getScaledHeight() / 2, player.y + 100);
    }
    public void drawMoneyGain(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.setColor(Color.YELLOW);
        g.drawString("+" + enemy.getMoneyValue() + " $", player.x + player.getScaledWidth() / 2, player.y + 100);
    }
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}