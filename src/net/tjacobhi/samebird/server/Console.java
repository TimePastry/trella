package net.tjacobhi.samebird.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sean on 12/29/2016.
 *
 * receives, interprets, and executes console commands given to the server while it is running
 */
public class Console implements Runnable {
    private Thread mThread;
    private String mThreadName;
    
    Thread getThread() {
        return mThread;
    }
    
    Console(String threadName){
        mThreadName = threadName;
        System.out.println("Creating thread " + mThreadName);
    }
    
    @Override
    public void run() {
        System.out.println("Running thread " + mThreadName);
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String command;
			while ((command = reader.readLine()) != null){
				executeCommand(command);
			}
		} catch(IOException e){
			System.out.println("IOException on reading command");
		}
    }
    
    void start(){
        System.out.println("Starting thread " + mThreadName);
        if (mThread == null){
            mThread = new Thread(this, "Console");
            mThread.start();
        }
    }
    
    void executeCommand(String command){
        System.out.println(command);
    }
}
