package net.tjacobhi.samebird.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;

/**
 * Created by Sean on 12/29/2016.
 *
 * receives, interprets, and executes console commands given to the server while it is running
 */
public class Console implements Runnable {
    private Thread mThread;
	private Semaphore mGameStarted;
    
    // command strings
	static final String GAME_START = "start";
    
    Thread getThread() {
        return mThread;
    }
    
    Console(Semaphore gameStarted){
        mGameStarted = gameStarted;
    }
    
    @Override
    public void run() {
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String command;
			while ((command = reader.readLine()) != null){
				executeCommand(command);
			}
		} catch(IOException e){
			System.out.println("IOException on reading command");
		}
    }
    
    void start(){
        if (mThread == null){
            mThread = new Thread(this, "Console");
            mThread.start();
        }
    }
    
    void executeCommand(String command){
    	if (GAME_START.equals(command)){
    		mGameStarted.release();
	    }
	    else {
        System.out.println("Unknown command");
	    }
    }
}
