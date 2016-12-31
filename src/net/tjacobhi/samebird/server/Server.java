package net.tjacobhi.samebird.server;

import net.tjacobhi.samebird.Utilities.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
	
	Server(Semaphore gameStarted){
		mGameStarted = gameStarted;
	}
	
	@Override
	public void run() {
		if (mServerSocket == null) {
			try {
				mServerSocket = new ServerSocket(Utilities.PORT);
			} catch (IOException e){
				System.out.println("ServerSocket failed");
			}
		}
		
		try (
				Socket clientsocket = mServerSocket.accept();
				PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
		) {
			System.out.println(in.readLine());
			try {
				executeClientCommand(in.readLine());
			} catch (IOException e){
				System.out.println("Client Disconnected");
			}
		} catch (IOException e) {
			System.out.println("Client Disconnected");
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
