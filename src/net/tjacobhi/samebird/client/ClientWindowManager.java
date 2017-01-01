package net.tjacobhi.samebird.client;

import net.tjacobhi.samebird.TestWindow;
import net.tjacobhi.samebird.client.client_pages.ConnectedScreen;
import net.tjacobhi.samebird.client.client_pages.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tjacobhi on 31-Dec-16.
 *
 * Will be responsible to manage the window and different screens as menus are cycled through.
 */
public class ClientWindowManager implements ActionListener
{

	private JFrame mFrame; // keeps track of the window object
	private JPanel mContentPane;
	private MainMenu mMainMenu;
	private ConnectedScreen mConnectedScreen;

	/**
	 * Creates the initial window
	 */
	private void createAndShowGUI()
	{
		//Create and set up the window.
		mFrame = new JFrame("Same-bird");
		mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		mContentPane = new JPanel();
		mContentPane.setLayout(new CardLayout());
		mMainMenu = new MainMenu();
		mConnectedScreen = new ConnectedScreen();
		mContentPane.add(mMainMenu, "MainMenu");
		mContentPane.add(mConnectedScreen, "ConnectedScreen");
		mFrame.setMinimumSize(new Dimension(800, 600));
		mFrame.setSize(800, 600);
		mFrame.setLocation(50, 50);
		mFrame.setResizable(false);

		/*// Start by creating a MainMenu object and assigning it to mFrame.
		MainMenu mainMenu = new MainMenu();

		mainMenu.setOpaque(true);
*/
		mFrame.setContentPane(mContentPane);
		//Display the window.
		mFrame.pack();
		mFrame.setVisible(true);
	}

	/**
	 * When the client triggers for a screen change, the current state of the Client
	 * will determine which screen to change to.
	 */
	public void switchScreen()
	{
		switch (Client.getCurrentState())
		{
			case MAIN_MENU:
				((CardLayout)mContentPane.getLayout()).show(mMainMenu, "MainMenu");
				break;
			case CONNECTED_SCREEN:
				((CardLayout)mContentPane.getLayout()).show(mContentPane, "ConnectedScreen");
				break;
			case LOGIN_SCREEN:
				// todo: implement switching to the login screen
				break;
			case CREATE_USER:
				// todo: implement switching to the create user screen
				break;
			case OPTIONS_SCREEN:
				// todo: implement switching to the options screen
				break;
		}
	}

	/**
	 * This will create the window in the proper procedure
	 */
	public void createWindow()
	{
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}

	/**
	 * If the ClientWindowManager needs to handle any actions, they will be handled here.
	 * @param e event that needs to be handled.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{

	}
}
