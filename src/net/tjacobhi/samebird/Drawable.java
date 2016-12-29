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
     * @param graphics Swing graphics device to be drawn to
     * @param posX the x position to draw
     * @param posY the y position to draw
     */
    public void draw(Graphics graphics, int posX, int posY);
}
