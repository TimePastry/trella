package net.tjacobhi.samebird.client.client_pages;

import net.tjacobhi.samebird.client.Client;
import net.tjacobhi.samebird.client.ClientWindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tjacobhi on 31-Dec-16.
 */
public class MainMenu extends JPanel implements ActionListener
{
	private JButton mConnectButton;
	private JButton mOptionsButton;
	private JButton mExitButton;

	public MainMenu()
	{
		super();

		//Create Connect Button
		mConnectButton = new JButton("Connect");
		mConnectButton.setActionCommand("MM_CONNECT_BUTTON");
		mConnectButton.addActionListener(this);
		mConnectButton.setBounds(75, 150, 150, 30);
		mConnectButton.setVisible(true);
		this.add(mConnectButton);

		//Create Options Button
		mOptionsButton = new JButton("Options");
		mOptionsButton.setActionCommand("MM_OPTIONS_BUTTON");
		mOptionsButton.addActionListener(this);
		mOptionsButton.setBounds(75, 250, 150, 30);
		mOptionsButton.setVisible(true);
		this.add(mOptionsButton);

		//Create Exit Button
		mExitButton = new JButton("Exit");
		mExitButton.setActionCommand("MM_EXIT_BUTTON");
		mExitButton.addActionListener(this);
		mExitButton.setBounds(75, 350, 150, 30);
		mExitButton.setVisible(true);
		this.add(mExitButton);

		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);

		graphics.setColor(new Color(0x3589c5));
		Font font = new Font(null, Font.PLAIN, 30);
		graphics.setFont(font);
		graphics.drawString("Same-bird Main Menu", 30, 60);

		graphics.setColor(new Color(0x993333));
		graphics.fillRoundRect(300, 100, 400, 400, 20, 20);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "MM_CONNECT_BUTTON":
				System.out.println("Connect Button Pressed");
				Client.windowEvent(e);
				break;
			case "MM_OPTIONS_BUTTON":
				break;
			case "MM_EXIT_BUTTON":
				System.out.println("Exited from Main Menu Exit Button");
				System.exit(0);
				break;
			default:
				// do nothing
		}
	}
}
