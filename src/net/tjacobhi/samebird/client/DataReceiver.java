package net.tjacobhi.samebird.client;

import net.tjacobhi.samebird.utilities.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by tjacobhi on 30-Dec-16.
 *
 * connects to the server and provides communication for the client
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
		boolean connected = connect();
		if(connected)
		{
			System.out.println("Connected to server");
			Client.onConnect(); // This will allow the client to handle what to do after connection has taken place
		}
		else
		{
			System.out.println("Could not connect to server");
			Client.onFailedConnect();
			return; // we exit the thread
		}
    }

    private boolean connect()
    {
	    try {
		    mSocket = new Socket(Utilities.HOSTNAME, Utilities.PORT);
		    mSocket.setSoTimeout(10500);
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
		    System.err.println("Don't know about host");
		    //System.exit(1);
	    }
	    catch (IOException e) {
		    System.err.println("Couldn't get I/O for the connection");
		    //System.exit(1);
	    }
	    
	    return false;
    }
	
    public void disconnect()
    {
    	try {
		    mOut.println(Utilities.PLAYER_DISCONNECTED);
		    int messageFromServer = 0;
		    try
		    {
			    while (messageFromServer != Utilities.ACCEPT_PLAYER_DISCONNECTED)
			    {
				    messageFromServer = Integer.parseInt(mIn.readLine());
				    // Probably should handle parseInt's exception
				    System.out.println("Successful disconnect");
			    }
		    }
		    catch (SocketTimeoutException e)
		    {
		    	e.printStackTrace();
		    }

    		mIn.close();
    		mOut.close();
		    mSocket.close();
	    } catch (IOException e){
		    System.err.println("Couldn't get I/O for the connection to server");
		    //System.exit(1); // Do we want to exit the whole program on disconnect, or go back to main menu?
	    }
	    Client.onDisconnect(); // This will allow the client to update everything else after disconnect has taken place
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
