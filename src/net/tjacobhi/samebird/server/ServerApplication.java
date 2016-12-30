package net.tjacobhi.samebird.server;


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
    
    //private static GameUpdater mGameUpdater;
    
    public static void main(String[] args){
        System.out.println("Welcome to the Samebird server program version " + mVersionNumber);
        
        GameUpdater gameUpdater = new GameUpdater("Game Updater");
        gameUpdater.start();
        
        Console console = new Console("Console");
        console.start();
        
        while (gameUpdater.getThread().isAlive()){
            
        }
        console.getThread().interrupt();
    }
}
