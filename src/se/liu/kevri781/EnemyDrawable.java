package se.liu.kevri781;

import java.awt.*;
import javax.swing.ImageIcon;

public class EnemyDrawable extends Enemy implements Drawable
{
//    Represents the player character that can be drawn on the screen.
//    Implements the Drawable interface to provide a method for drawing the player.
    private Enemy enemy = null;
    private ImageIcon sprite = null;

    public EnemyDrawable(final int x, final int y, final int width, final int height) {
	super(x, y, width, height);
	this.sprite = new ImageIcon("resources/images/enemy/Run.png");
    }

    @Override
    public void draw(Graphics g) {
    	// Draws the enemy character on the screen
        g.drawImage(sprite.getImage(), x, y, width, height, null);
    }

    @Override public void collidesWith() {

    }

    @Override public void keyPressed() {

    }

    @Override public void keyReleased() {

    }
}
