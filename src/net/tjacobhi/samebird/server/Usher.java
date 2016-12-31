package net.tjacobhi.samebird.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Semaphore;

/**
 * Created by Sean on 12/30/2016.
 *
 * manages new connections made to the server
 */
public class Usher implements Runnable{
	private Semaphore mServerCapacity;
	private Thread mThread;
	private Server mServer;
	
	
	// set server size
	private final int SIZE = 1;
	
	Usher(Server server){
		mServerCapacity = new Semaphore(SIZE);
		mServer = server;
	}
	
	@Override
	public void run() {
		try {
			while (ServerApplication.running) {
				mServerCapacity.acquire();
				mServer.getServerSocket().setSoTimeout(10000);
				try {
					Socket socket = mServer.getServerSocket().accept();
					mServer.getClients().add(socket);
					mServer.getClientOuts().add(new PrintWriter(socket.getOutputStream(), true));
					mServer.getClientIns().add(new BufferedReader(new InputStreamReader(socket.getInputStream())));
				} catch (SocketTimeoutException e)
				{
					wait(5000);
				}
			}
		}
		catch(IOException e)
		{
			System.err.println("IOException in Usher");
		}
		catch(InterruptedException e){
			System.err.println("Interrupted exception in Usher");
		}
	}
	
	public void start(){
		if (mThread == null){
			mThread = new Thread(this, "Usher");
			mThread.start();
		}
	}
}