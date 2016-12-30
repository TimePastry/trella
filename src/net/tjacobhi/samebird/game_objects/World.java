package net.tjacobhi.samebird.game_objects;

import net.tjacobhi.samebird.Drawable;
import net.tjacobhi.samebird.Updatable;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by tjacobhi on 29-Dec-16.
 */
public class World implements Drawable, Updatable, Serializable
{
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;

    @Override
    public void draw(Graphics graphics, int posX, int posY)
    {

    }

    @Override
    public void update(double elapsedGameTime)
    {

    }

    private static final long serialVersionUID = 0x576f726c64L;
}
