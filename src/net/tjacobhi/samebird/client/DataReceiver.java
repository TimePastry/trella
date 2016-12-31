package net.tjacobhi.samebird.client;

import net.tjacobhi.samebird.utilities.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by tjacobhi on 30-Dec-16.
 */
public class DataReceiver implements Runnable
{
    private Thread mThread;
    private String mHostName;
    private int mPort;
    private Socket mSocket;
	private PrintWriter mOut;
	private BufferedReader mIn;


    @Override
    public void run()
    {
		connect();
    }

    public boolean connect()
    {
	    try {
		    mSocket = new Socket(Utilities.HOSTNAME, Utilities.PORT);
		    mSocket.setSoTimeout(500);
			mOut = new PrintWriter(mSocket.getOutputStream(), true);
			mIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
		    mOut.println(Utilities.PLAYER_CONNECTED);
		    try {
			    if (Integer.parseInt(mIn.readLine()) == Utilities.ACCEPT_PLAYER_CONNECTED) {
				    return true;
			    }
		    }
		    catch (SocketTimeoutException e){
		    	System.out.println("No response from server");
		    }
	    }
	    catch (UnknownHostException e) {
		    System.err.println("Don't know about host ");
		    System.exit(1);
	    }
	    catch (IOException e) {
		    System.err.println("Couldn't get I/O for the connection to ");
		    System.exit(1);
	    }
	    
	    return false;
    }
	
    public void disconnect()
    {
    	try {
    		mIn.close();
    		mOut.close();
		    mSocket.close();
	    } catch (IOException e){
		    System.err.println("Couldn't get I/O for the connection to server");
		    System.exit(1);
	    }
    }
    
    public void startGame(){
    	mOut.println(Utilities.GAME_START);
    }
	
	void start(){
		if (mThread == null){
			mThread = new Thread(this, "Game Updater");
			mThread.start();
		}
	}
}
