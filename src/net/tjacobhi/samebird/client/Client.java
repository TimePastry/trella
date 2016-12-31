package net.tjacobhi.samebird.client;

import net.tjacobhi.samebird.TestWindow;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created by tjacobhi on 29 December 2016
 *
 * Client is the starting point for the client-end of same-bird
 */
public class Client
{
    TestWindow mWindow; // I'm creating its window as a member variable.
	private static DataReceiver mDataReceiver;
	private static ClientState mCurrentState;

	public static Client SINGLETON = new Client();

    public Client()
    {
        mWindow = new TestWindow();
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

	public static void windowEvent(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "GAME_START":
				mDataReceiver.startGame();
				break;

		}
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