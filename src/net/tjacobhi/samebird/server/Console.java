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
	private static final String GAME_START = "start";
	private static final String NUM_PLAYERS = "nplayers";
	private static final String HELP = "help";
	private static final String EXIT = "exit";
	private static final String QUIT = "quit";
	
	
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
    
    private void executeCommand(String command){
    	switch (command){
    		case GAME_START:
    			mGameStarted.release();
    			break;
		    case NUM_PLAYERS:
		    	System.out.print("The current number of connected users is ");
		    	if (ServerApplication.getServer().getUsher().getServerCapacity() != null){
		    		System.out.println(Server.getNumClients());
			    } else {
		    		System.out.println(0);
			    }
			    break;
		    case HELP:
		    	// sort these statements alphabetically
		    	System.out.println("help: \tyou probably already know what this one does");
		    	System.out.println("nplayers: \tprint the current number of connected players");
		    	System.out.println("start: \tstart the game");
		    	break;
		    case EXIT:
		    case QUIT:
		    	ServerApplication.running = false;
		    	ServerApplication.getGameUpdater().getThread().interrupt();
		    	ServerApplication.getServer().getUsher().getThread().interrupt();
		    	ServerApplication.getServer().getThread().interrupt();
		    	//mThread.interrupt();
		    	System.exit(0);
		    	break;
		    default:
		    	System.out.println("Unknown command");
	    }
    }
}
