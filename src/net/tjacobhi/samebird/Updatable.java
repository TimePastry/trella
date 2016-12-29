package net.tjacobhi.samebird;

/**
 * Created by tjacobhi on 29-Dec-16.
 */
public interface Updatable
{
    /**
     * Implements an update on the game loop. Use the elapsedGameTime to determine how long it has been since last time
     * the method was called.
     * @param elapsedGameTime the amount of time the game has run
     */
    public void update(double elapsedGameTime);
}
