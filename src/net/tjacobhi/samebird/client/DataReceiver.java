package net.tjacobhi.samebird.client;

import net.tjacobhi.samebird.utilities.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

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
	private Semaphore mCommandReady;
	private Semaphore mCommandMutex;
	private int mCommand;
	

    @Override
    public void run()
    {
    	while(true){
    		try{
    			mCommandReady.acquire();
    			mCommandMutex.acquire();
    			if ((mCommand & Utilities.PLAYER_CONNECTED) == Utilities.PLAYER_CONNECTED){
    				connect();
    				mCommand ^= Utilities.PLAYER_CONNECTED;
			    }
			    if ((mCommand & Utilities.PLAYER_DISCONNECTED) == Utilities.PLAYER_DISCONNECTED){
    				disconnect();
    				mCommand ^= Utilities.PLAYER_DISCONNECTED;
			    }
    			mCommandReady.release();
    			mCommandMutex.release();
		    }
		    catch (InterruptedException e){
    			e.printStackTrace();
		    }
	    }
		
    }
    
    public void sendCommand(int command){
    	try {
		    mCommandMutex.acquire();
		    mCommand |= command;
		    mCommandReady.release();
		    mCommandMutex.release();
	    }
	    catch(InterruptedException e){
    		e.printStackTrace();
	    }
    }

    boolean connect()
    {
	    try {
		    mSocket = new Socket(Utilities.HOSTNAME, Utilities.PORT);
		    mSocket.setSoTimeout(500); // changed to half a second
			mOut = new PrintWriter(mSocket.getOutputStream(), true);
			mIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
		    mOut.println(Utilities.PLAYER_CONNECTED);
		    String serverMessage = "";
		    try {
		    	serverMessage = mIn.readLine();
			    if ((Integer.parseInt(serverMessage) & Utilities.ACCEPT_PLAYER_CONNECTED) ==
			        Utilities.ACCEPT_PLAYER_CONNECTED)
			    {
				    System.out.println("Connected to server");
				    Client.onConnect();
				    return true;
			    }
			    if ((Integer.parseInt(serverMessage) & Utilities.SERVER_FULL) ==
			        Utilities.SERVER_FULL)
			    {
			    	System.out.print("Server is full");
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
	
	    System.out.println("Could not connect to server");
	    Client.onFailedConnect();
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
    	mCommandReady = new Semaphore(1);
    	// set initial semaphore state to 0
    	try {
	    	mCommandReady.acquire();
	    }
	    catch (InterruptedException e){
    		e.printStackTrace();
		}
		mCommandMutex = new Semaphore(1);
		
		mCommand = 0;
		if (mThread == null){
			mThread = new Thread(this, "Game Updater");
			mThread.start();
		}
	}
}
