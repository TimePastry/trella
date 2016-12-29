package net.tjacobhi.samebird.server;

/**
 * Created by Sean on 12/29/2016.
 *
 * receives, interprets, and executes console commands given to the server while it is running
 */
public class Console implements Runnable {
    private Thread mThread;
    private String mThreadName;
    
    public Thread getThread() {
        return mThread;
    }
    
    Console(String threadName){
        mThreadName = threadName;
        System.out.println("Creating thread " + mThreadName);
    }
    
    @Override
    public void run() {
        System.out.println("Running thread " + mThreadName);
    }
    
    public void start(){
        System.out.println("Starting thread " + mThreadName);
        if (mThread == null){
            mThread = new Thread(this, "Console");
            mThread.start();
        }
    }
    
    public void executeCommand(String command){
        System.out.println(command);
    }
}
