package net.tjacobhi.samebird.client;

import net.tjacobhi.samebird.utilities.Utilities;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created by tjacobhi on 29 December 2016
 *
 * Client is the starting point for the client-end of same-bird
 */
public class Client
{
    ClientWindowManager mWindow; // I'm creating its window as a member variable.
	private static DataReceiver mDataReceiver;
	private static ClientState mCurrentState;
	private static boolean mConnected;

	public static Client SINGLETON = new Client();
	
	public static boolean isConnected() {
		return mConnected;
	}
	
	public Client()
    {
        mWindow = new ClientWindowManager();
        mConnected = false;
        
    }

    /**
     * This is the entry point of the Client
     * @param args any command args may be placed here (We can handle them to do specific things)
     */
    public static void main(String[] args) throws IOException
    {
        //Client client = new Client();
	    mCurrentState = ClientState.MAIN_MENU;
        SINGLETON.mWindow.createWindow();
        
        Client.mDataReceiver = new DataReceiver();
        Client.mDataReceiver.start();
    }

	/**
	 * Forward events from any screen to this method to have the client handle the necessary actions
	 * @param e The event that needs to be handled. Use the format AA_NAME, where AA is the abbriviation of which screen
	 *          this came from.
	 */
	public static void windowEvent(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "MM_CONNECT_BUTTON":
				mDataReceiver.sendCommand(Utilities.PLAYER_CONNECTED);
				break;
			case "CS_LOGIN_BUTTON":
				mCurrentState = ClientState.LOGIN_SCREEN;
				SINGLETON.mWindow.switchScreen();
				break;
			case "CS_CREATEUSER_BUTTON":
				mCurrentState = ClientState.CREATE_USER;
				SINGLETON.mWindow.switchScreen();
				break;
			case "CS_DISCONNECT_BUTTON":
				mDataReceiver.sendCommand(Utilities.PLAYER_DISCONNECTED);
				break;
			case "LS_BACK_BUTTON":
			case "CU_BACK_BUTTON":
				mCurrentState = ClientState.CONNECTED_SCREEN;
				SINGLETON.mWindow.switchScreen();
				break;
		}
	}

	public static void onConnect()
	{
		mCurrentState = ClientState.CONNECTED_SCREEN;
		mConnected = true;
		SINGLETON.mWindow.switchScreen();
	}

	public static void onFailedConnect()
	{
		SINGLETON.mWindow.onFailedConnect();
	}

	public static void onDisconnect()
	{
		mCurrentState = ClientState.MAIN_MENU;
		mConnected = false;
		SINGLETON.mWindow.switchScreen();
	}

	public static ClientState getCurrentState()
	{
		return mCurrentState;
	}

	public enum ClientState
	{
		MAIN_MENU,
		CONNECTED_SCREEN,
		LOGIN_SCREEN,
		CREATE_USER,
		OPTIONS_SCREEN
	}
}