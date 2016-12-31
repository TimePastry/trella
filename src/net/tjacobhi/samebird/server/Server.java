package net.tjacobhi.samebird.server;

import net.tjacobhi.samebird.Utilities.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		while (ServerApplication.running) {
			for (int i = 0; i < mClients.size(); i++) {
				
			}
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
	
	private void executeClientCommand(String command){
		int code = Integer.parseInt(command);
		switch(code){
			case Utilities.GAME_START:
				mGameStarted.release();
				break;
			default:
				System.out.println("Unrecognized command from client");
		}
	}
}
