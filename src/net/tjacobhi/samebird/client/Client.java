package net.tjacobhi.samebird.client;

import net.tjacobhi.samebird.TestWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by tjacobhi on 29 December 2016
 *
 * Client is the starting point for the client-end of same-bird
 */
public class Client implements ActionListener
{
    TestWindow mWindow; // I'm creating its window as a member variable.
	private static DataReceiver mDataReceiver;

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
        Client client = new Client();
        client.mWindow.createWindow();
        
        Client.mDataReceiver = new DataReceiver();
        Client.mDataReceiver.start();
    }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if("GAME_START".equals(e.getActionCommand()))
		{
			// todo: call DataReceiver's game start command
		}
	}
}