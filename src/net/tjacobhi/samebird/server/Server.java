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
	private ServerSocket mServerSocket;
	private ArrayList<Socket> mClients;
	private ArrayList<PrintWriter> mClientOuts;
	private ArrayList<BufferedReader> mClientIns;
	
	public ArrayList<PrintWriter> getClientOuts() {
		return mClientOuts;
	}
	
	public ArrayList<BufferedReader> getClientIns() {
	
		return mClientIns;
	}
	
	public ServerSocket getServerSocket() {
		return mServerSocket;
	}
	
	Server(Semaphore gameStarted){
		mGameStarted = gameStarted;
		Usher usher = new Usher(this);
		if (mServerSocket == null) {
			try {
				mServerSocket = new ServerSocket(Utilities.PORT);
			} catch (IOException e) {
				System.out.println("ServerSocket failed");
			}
		}
	}
	
	public ArrayList<Socket> getClients() {
		return mClients;
	}
	
	@Override
	public void run() {
		try {
			while (ServerApplication.running) {
				for (int i = 0; i < mClients.size(); i++) {
					if (mClientIns.get(i).ready()) {
						executeClientCommand(mClientIns.get(i).readLine(), i);
					}
				}
			}
		}
		catch (IOException e){
			System.err.println("IOException in Server");
		}
		try {
			mServerSocket.close();
		} catch (IOException e){
			System.out.println("ServerSocket close failed");
		}
	}
	
	void start(){
		if (mThread == null){
			mThread = new Thread(this, "Console");
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
			default:
				System.out.println("Unrecognized command from client");
		}
	}
}
