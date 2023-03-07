package se.liu.kevri781;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static se.liu.kevri781.GameViewer.screenSize;

public class Player extends Character
{

    public Player(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        //    Represents the player character.
        //    Contains additional properties such as lives and score.
        //    Defines methods for handling input and updating the player's state.
        boolean recovering = false;
        long recoverTimer = 1;
        int score = 0;
        int lives = 3;
        int level = 1;
        int xp = 0;
    }


    @Override public void update() {
        // Moves the player
        x += velocityX;
        y += velocityY;
    }

    @Override public void draw(final Graphics g) {
        // Draws the player
        PlayerDrawable player = new PlayerDrawable(this.x, this.y, this.width, this.height);
    }

    @Override public boolean collidesWith(final GameObjects other) {
        return false;
    }


    public void keyPressed(KeyEvent e) {
        // Handles input from the keyboard
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            velocityX = -5;
        }
        if (key == KeyEvent.VK_RIGHT) {
            velocityX = 5;
        }
        if (key == KeyEvent.VK_UP) {
            velocityY = -5;
        }
        if (key == KeyEvent.VK_DOWN) {
            velocityY = 5;
        }
    };
    public void keyReleased(KeyEvent e) {
        // Handles input from the keyboard
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            velocityX = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            velocityX = 0;
        }
        if (key == KeyEvent.VK_UP) {
            velocityY = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            velocityY = 0;
        }
    };
}
