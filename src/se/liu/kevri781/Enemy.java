package se.liu.kevri781;

import javax.swing.*;
import java.awt.*;

public abstract class Enemy extends Character
{
//    Represents an enemy character.
//    Defines methods for enemy-specific behavior, such as AI and spawning.
    public Enemy(final int x, final int y, final int width, final int height) {
	super(x, y, width, height);
    }

    @Override public void update() {

    }

    @Override public void draw(final Graphics g) {

    }

    @Override public boolean collidesWith(final GameObjects other) {
	return false;
    }

    public Object getAttackPower() {
        return damage;
    }

    public abstract void keyPressed();

    public abstract void keyReleased();
}
