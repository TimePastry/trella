package net.tjacobhi.samebird.server;


import java.util.concurrent.Semaphore;

/**
 * Created by Sean on 12/29/2016.
 *
 * multi-threaded server program to support Samebird game play
 *
 * has three tasks:
 * 1. to manage and update the game state
 * 2. respond to game client queries i.e. what is the game state, I am doing this action, etc.
 * 3. respond to server admin commands
 */
public class ServerApplication {
    // version number constant, maybe the client can use this
    // to check to make sure that the server and the client versions are compatible
    private static final String mVersionNumber = "0.0.1";
    private static final Semaphore mGameStarted = new Semaphore(1);
    static boolean running = true;
    private static Server sServer;
	private static Console sConsole;
	private static GameUpdater sGameUpdater;
	
	public static Server getServer() {
		return sServer;
	}
	
	public static Console getConsole() {
		return sConsole;
	}
	
	public static GameUpdater getGameUpdater() {
		return sGameUpdater;
	}
	//private static GameUpdater mGameUpdater;
    
    public static void main(String[] args){
        System.out.println("Welcome to the Samebird server program version " + mVersionNumber);
        
        try {
	        mGameStarted.acquire();
        }
        catch (InterruptedException e) {
        	System.err.println("Error with initializing GameStarted semaphore");
        }
        
        sServer = new Server(mGameStarted);
        sServer.start();
        
        sGameUpdater = new GameUpdater(mGameStarted);
        sGameUpdater.start();
        
        sConsole = new Console(mGameStarted);
        sConsole.start();
        
        /*
        while (true){
            if (!gameUpdater.getThread().isAlive()) break;
        }
        console.getThread().interrupt();
        */
    }
}
