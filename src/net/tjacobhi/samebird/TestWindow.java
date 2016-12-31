package net.tjacobhi.samebird;

import net.tjacobhi.samebird.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tjacobhi on 28-Dec-16.
 * Please reference the following site to learn 2d graphics
 * https://docs.oracle.com/javase/tutorial/2d/index.html
 *
 * This will allow us to create a window with 2d graphics
 * Right now this class is a playground in order to test how to draw with Swing, later, we will create a more
 * refined class that will allow us to do specific things
 */
public class TestWindow extends JPanel implements ActionListener
{
    private JButton mTestButton;
    private JButton mMainMenu_ConnectButton;
    private JButton mMainMenu_OptionsButton;
    private JButton mMainMenu_ExitButton;

    public TestWindow()
    {
        super();
        mTestButton = new JButton("Start Game");
	    mTestButton.setActionCommand("GAME_START");
	    mTestButton.addActionListener(this);
	    mTestButton.setBounds(10, 10, 170, 30);
        this.add(mTestButton);
        setLayout(null);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Same-bird");
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	    frame.setMinimumSize(new Dimension(800, 600));
        frame.setSize(800, 600);
        frame.setResizable(false); // *facepalm* for whatever reason, I missed this method...

        TestWindow testWindow = new TestWindow();

        testWindow.setOpaque(true);

        frame.setContentPane(testWindow);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void createWindow() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        switch (Client.getCurrentState())
        {
	        case MAIN_MENU:

	        	break;

	        default:
	        	// There is an error
        }
    }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "GAME_START":
				Client.windowEvent(e);
				remove(mTestButton);
				repaint();
				break;
		}
	}
}
