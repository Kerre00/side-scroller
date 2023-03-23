package se.liu.kevri781;

import java.awt.*;
import java.awt.event.KeyEvent;

public interface Drawable
{
//    Represents any object that can be drawn on the screen, including the player and enemies.
//    Contains a method for drawing the object on the screen.
    void draw(Graphics g);
    void update();
}
