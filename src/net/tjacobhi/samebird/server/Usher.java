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
	private static Semaphore mServerCapacity;
	private Thread mThread;
	private static Server mServer;
	
	static final int SIZE = 2;
	
	public static Semaphore getServerCapacity() {
		return mServerCapacity;
	}
	
	// set server size
	
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
					Server.clientSemaphore.acquire();
					mServer.getClients().add(socket);
					mServer.getClientOuts().add(new PrintWriter(socket.getOutputStream(), true));
					mServer.getClientIns().add(new BufferedReader(new InputStreamReader(socket.getInputStream())));
					Server.clientSemaphore.release();
				} catch (SocketTimeoutException e)
				{
					mServerCapacity.release();
					//wait(5000); // Keep getting an IllegalMonitorStateException thrown here, find out why
					//Thread.sleep(5000); // Does this fix the above exception? Yep, it fixed it.
					//What is the difference between wait and sleep? Is there a specific reason wait needs to be used?
					// just looked it up on stack overflow, looks like sleep is the better function to call anyways
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
	
	void start(){
		if (mThread == null){
			mThread = new Thread(this, "Usher");
			mThread.start();
		}
	}
}
