package net.tjacobhi.samebird.server;

import net.tjacobhi.samebird.utilities.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by Sean on 12/30/2016.
 *
 * connects to, receives information from, and sends information to the clients
 */
public class Server implements Runnable{
	private Thread mThread;
	private Semaphore mGameStarted;
	static Semaphore clientSemaphore = new Semaphore(1);
	private ServerSocket mServerSocket;
	private static ArrayList<Socket> mClients;
	private ArrayList<PrintWriter> mClientOuts;
	private ArrayList<BufferedReader> mClientIns;
	private Usher mUsher;
	
	public Usher getUsher() {
		return mUsher;
	}
	
	static int getNumClients(){
		if (mClients != null){
		
			return mClients.size();
		}
		return 0;
	}
	
	public Thread getThread() {
		return mThread;
	}
	
	ArrayList<PrintWriter> getClientOuts() {
		return mClientOuts;
	}
	
	ArrayList<BufferedReader> getClientIns() {
	
		return mClientIns;
	}
	
	ServerSocket getServerSocket() {
		return mServerSocket;
	}
	
	Server(Semaphore gameStarted){
		mGameStarted = gameStarted;
		mUsher = new Usher(this);
		if (mServerSocket == null) {
			try {
				mServerSocket = new ServerSocket(Utilities.PORT);
			} catch (IOException e) {
				System.out.println("ServerSocket failed");
			}
		}
		mClients = new ArrayList<>();
		mClientIns = new ArrayList<>();
		mClientOuts = new ArrayList<>();
		mUsher.start();
	}
	
	ArrayList<Socket> getClients() {
		return mClients;
	}
	
	@Override
	public void run() {
		try {
			while (ServerApplication.running) {
				clientSemaphore.acquire();
				for (int i = 0; i < mClientIns.size(); i++) {
					if (mClientIns.get(i).ready()) {
						executeClientCommand(mClientIns.get(i).readLine(), i);
					}
				}
				clientSemaphore.release();
				Thread.sleep(50);
			}
		}
		catch (InterruptedException e){
			System.out.println("Server interrupting");
		}
		catch (Exception e){
			System.err.println("Exception in Server");
		}
		try {
			mServerSocket.close();
		} catch (IOException e){
			System.out.println("ServerSocket close failed");
		}
	}
	
	void start(){
		if (mThread == null){
			mThread = new Thread(this, "Server");
			mThread.start();
		}
	}
	
	private void executeClientCommand(String command, int user){
		int code = Integer.parseInt(command);
		switch(code){
			case Utilities.GAME_START:
				mGameStarted.release();
				break;
			case Utilities.PLAYER_CONNECTED:
				mClientOuts.get(user).println(Utilities.ACCEPT_PLAYER_CONNECTED);
				System.out.println("Player connected");
				break;
			case Utilities.PLAYER_DISCONNECTED:
				// I hope this works, might be some issues with closing the stream
				mClientOuts.get(user).println(Utilities.ACCEPT_PLAYER_DISCONNECTED);
				try {
					mClientOuts.get(user).close();
					mClientIns.get(user).close();
					mClients.get(user).close();
				}
				catch (IOException e){
					System.out.println("Error disconnecting user");
				}
				finally
				{
					mClientOuts.remove(user);
					mClientIns.remove(user);
					mClients.remove(user);
					mUsher.getServerCapacity().release();
					System.out.println("Player disconnected");
				}
				break;
			default:
				System.out.println("Unrecognized command from client");
		}
	}
}
