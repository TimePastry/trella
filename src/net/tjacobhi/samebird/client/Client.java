package net.tjacobhi.samebird.client;

import net.tjacobhi.samebird.TestWindow;
import net.tjacobhi.samebird.Utilities.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
    public static void main(String[] args) throws IOException
    {
        Client client = new Client();
        client.mWindow.createWindow();
	
		try (
				Socket socket = new Socket(Utilities.HOSTNAME, Utilities.PORT);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
		) {
			BufferedReader stdIn =
					new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;
		
			while ((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("Bye."))
					break;
			
				fromUser = stdIn.readLine();
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					out.println(fromUser);
				}
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host ");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to ");
			System.exit(1);
		}
    }
}