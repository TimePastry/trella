package net.tjacobhi.samebird;

/**
 * Created by Sean on 12/29/2016.
 *
 * Updates the game state on the server at a fixed time rate
 */
public class GameUpdater implements Runnable {
    private Thread mThread;
    private String mThreadName;
    
    // runs just under 60 frames per second
    private final int mFPS = 17;
    
    // for testing, one update per second
    private final int mTestFPS = 1000;
    
    public GameUpdater(String threadName){
        mThreadName = threadName;
        System.out.println("Creating thread " + mThreadName);
    }
    
    
    @Override
    public void run() {
        System.out.println("Updating game");
        try{
            int i = 0;
            // todo create a check to see if the game is over
            while (i < 100) {
                System.out.println(i);
                i++;
                Thread.sleep(mTestFPS);
            }
        } catch (InterruptedException e){
            System.out.println("Game updater interrupted");
        }
        System.out.println("Game updater exiting");
    }
    
    public void start(){
        System.out.println("Game updater starting");
        if (mThread == null){
            mThread = new Thread(this, "Game Updater");
            mThread.start();
        }
    }
}
