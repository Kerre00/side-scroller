package se.liu.kevri781;

import java.awt.*;
import java.awt.event.KeyEvent;

public interface Drawable
{
    /**
     * Represents an object that can be drawn on the screen.
     * Used to draw objects on the screen.
     */
    void draw(Graphics g);
    void update();
}
