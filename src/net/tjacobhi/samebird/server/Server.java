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
	
	Server(Semaphore gameStarted){
		mGameStarted = gameStarted;
	}
	
	@Override
	public void run() {
		try(
				ServerSocket serversocket = new ServerSocket(Utilities.PORT);
				Socket clientsocket = serversocket.accept();
				PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
				)
		{
			System.out.println(in.readLine());
			executeClientCommand(in.readLine());
			
		}catch (IOException e){
			System.out.println("IOException! Stop breaking things!");
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
