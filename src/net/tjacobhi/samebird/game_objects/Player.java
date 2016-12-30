package net.tjacobhi.samebird.game_objects;

import net.tjacobhi.samebird.Drawable;
import net.tjacobhi.samebird.Updatable;
import org.jetbrains.annotations.Contract;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by tjacobhi on 29-Dec-16.
 */
public class Player implements Drawable, Updatable, Serializable
{
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    private Color mPlayerColor;
    private int mId;    // This will keep track of which player is which (specifically for now what color to draw them)
                        // Later, we may decide to allow players to pick there own color, but for now it is assigned.
                        // todo: have server assign id
    private Point.Double mPosition; // Keeps track of the position

    public Player()
    {
        assignId();
        assignColor();
        setPosition(new Point.Double(0, 0));
    }

    /**
     * This will draw the player on the world at the specified position
     * @param graphics Swing graphics device to be drawn to
     * @param posX the x position to draw
     * @param posY the y position to draw
     */
    @Override
    public void draw(Graphics graphics, int posX, int posY)
    {
        graphics.setColor(mPlayerColor);
        graphics.fillRect(posX, posY, Player.WIDTH/2, Player.HEIGHT/2);
        graphics.setColor(Color.black);
    }

    /**
     * This will update the player's position and actions, as well as deal with input.
     * @param elapsedGameTime the amount of time the game has run
     */
    @Override
    public void update(double elapsedGameTime)
    {

    }

    @Contract(" -> !null") // we will never give a null point (Remove if we need to give a null position)
    public final Point.Double getPosition()
    {
        return new Point.Double(mPosition.x, mPosition.y);
        // This ensures that the return value cannot change the values in position after it is returned
    }

    public void setPosition(Point.Double position)
    {
        mPosition = position;
    }

    public final int getId()
    {
        return mId; // Because int is a primitive value, we can be certain that mId will not be changed outside class
    }

    private static int nextId = 1;

    private void assignId()
    {
        mId = nextId;
        nextId++;
    }

    private void assignColor()
    {
        switch (mId)
        {
            case 1:
                mPlayerColor = new Color(0xe23621);
                break;
            case 2:
                mPlayerColor = new Color(0x2136e2);
                break;
            case 3:
                mPlayerColor = new Color(0x369236);
                break;
            case 4:
                mPlayerColor = new Color(0xc2b236);
                break;
            default: // We need to decide on how many players there are going to be
                mPlayerColor = new Color(0x555566);
        }
    }

    private static long serialVersionUID = 0x506c61796572L;
}
