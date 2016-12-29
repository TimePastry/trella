package net.tjacobhi.samebird;

import java.awt.*;

/**
 * Created by tjacobhi on 29-Dec-16.
 *
 * This defines that a class may be drawn.
 */
public interface Drawable
{
    /**
     * Impliment this to draw to the graphics that are passed in.
     * Position is redundant in this application because world is the screen,
     * and objects should know their own position, but to make this work for
     * other drawables that may have position assigned by the world, position
     * been added to make things easier in these situations
     * @param graphics Swing graphics device to be drawn to
     * @param posX the x position to draw
     * @param posY the y position to draw
     */
    public void draw(Graphics graphics, int posX, int posY);
}
