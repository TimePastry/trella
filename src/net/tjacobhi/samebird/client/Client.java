package net.tjacobhi.samebird.client;

import net.tjacobhi.samebird.TestWindow;

/**
 * Created by tjacobhi on 29 December 2016
 *
 * Client is the starting point for the client-end of same-bird
 */
public class Client
{
    TestWindow mWindow; // I'm creating its window as a member variable.

    public Client()
    {
        mWindow = new TestWindow();
    }

    /**
     * This is the entry point of the Client
     * @param args any command args may be placed here (We can handle them to do specific things)
     */
    public static void main(String[] args)
    {
        Client client = new Client();
        client.mWindow.createWindow();
    }
}