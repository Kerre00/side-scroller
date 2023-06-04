package se.liu.kevri781;

import java.awt.*;

/**
 * Represents an object that can be drawn on the screen.
 * Used to draw objects on the screen.
 */
public interface Drawable
{
    void draw(Graphics g);
    void update();
}
