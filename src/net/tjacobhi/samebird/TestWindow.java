package net.tjacobhi.samebird;

import net.tjacobhi.samebird.Utilities.Utilities;
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
	private Client mClient;

    public TestWindow()
    {
        super();
        mTestButton = new JButton("Start Game");
	    mTestButton.setActionCommand("GAME_START");
	    mTestButton.addActionListener(this);
        this.add(mTestButton);
    }

    public TestWindow(Client client)
    {
    	super();
    	mClient = client;
    	mTestButton = new JButton("Start Game");
    	mTestButton.setActionCommand("GAME_START");
    	mTestButton.addActionListener(this);
    	add(mTestButton);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Test Window");
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        frame.setMaximumSize(new Dimension(800, 600));
        frame.setMinimumSize(new Dimension(800, 600));

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
        super.paint(g); // I added this line to handle repaint issues
        g.setColor(new Color(0x4488ee)); // This allows us to create a color using hex value
        g.fillRect(60, 60, 80, 120); // Draws a filled in rectangle
        g.setColor(Color.red); // This allows us to use color name (very limited)
        g.drawLine(50, 200, 400, 300); // Draws a line
        g.setColor(new Color(0x21e249));
        g.drawString("Hello, World!", 380, 290); // Draws text
	    g.fillRect(0, 0, 10, 10);
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
