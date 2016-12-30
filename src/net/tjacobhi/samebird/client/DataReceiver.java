package net.tjacobhi.samebird.client;

import net.tjacobhi.samebird.Utilities.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by tjacobhi on 30-Dec-16.
 */
public class DataReceiver implements Runnable
{
    private Thread mThread;
    private String mHostName;
    private int mPort;



    @Override
    public void run()
    {
		connect();
    }

    public void connect()
    {
	    try (
			    Socket socket = new Socket(Utilities.HOSTNAME, Utilities.PORT);
			    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			    BufferedReader in = new BufferedReader(
					    new InputStreamReader(socket.getInputStream()));
	    ) {
		    out.println("I've connected!");
	    }
	    catch (UnknownHostException e) {
		    System.err.println("Don't know about host ");
		    System.exit(1);
	    }
	    catch (IOException e) {
		    System.err.println("Couldn't get I/O for the connection to ");
		    System.exit(1);
	    }
    }
	
	void start(){
		if (mThread == null){
			mThread = new Thread(this, "Game Updater");
			mThread.start();
		}
	}
}
