package net.tjacobhi.samebird.client;

import net.tjacobhi.samebird.TestWindow;

import java.awt.*;

public class Client
{
    TestWindow mWindow; // I'm creating its window as a member variable.

    public Client()
    {
        mWindow = new TestWindow();
    }

    public static void main(String[] args)
    {
        Client client = new Client();
        client.mWindow.createWindow();
    }
}