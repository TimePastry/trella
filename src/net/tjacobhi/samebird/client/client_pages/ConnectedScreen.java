package net.tjacobhi.samebird.client.client_pages;

import net.tjacobhi.samebird.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tjacobhi on 31-Dec-16.
 *
 * After connecting to the server, This screen will be responsible for giving options of actions to interact with the
 * server.
 */
public class ConnectedScreen extends JPanel implements ActionListener,
                                                       Reloadable
{
	private JButton mLoginButton;
	private JButton mCreateUserButton;
	private JButton mDisconnectButton;

	public ConnectedScreen()
	{
		super();
		//Create login button
		mLoginButton = new JButton("Login");
		mLoginButton.setActionCommand("CS_LOGIN_BUTTON");
		mLoginButton.addActionListener(this);
		mLoginButton.setBounds(75, 150, 150, 30);
		mLoginButton.setVisible(true);
		add(mLoginButton);

		//Create CreateUser Button
		mCreateUserButton = new JButton("Create User");
		mCreateUserButton.setActionCommand("CS_CREATEUSER_BUTTON");
		mCreateUserButton.addActionListener(this);
		mCreateUserButton.setBounds(75, 250, 150, 30);
		mCreateUserButton.setVisible(true);
		add(mCreateUserButton);

		//Create Disconnect Button
		mDisconnectButton = new JButton("Disconnect");
		mDisconnectButton.setActionCommand("CS_DISCONNECT_BUTTON");
		mDisconnectButton.addActionListener(this);
		mDisconnectButton.setBounds(75, 350, 150, 30);
		mDisconnectButton.setVisible(true);
		add(mDisconnectButton);

		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.setColor(new Color(0x3589c5));
		Font font = new Font(null, Font.PLAIN, 30);
		graphics.setFont(font);
		graphics.drawString("Same-bird Server Menu", 30, 60);

		graphics.setColor(new Color(0x229933));
		graphics.fillRoundRect(300, 100, 400, 400, 20, 20);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "CS_LOGIN_BUTTON":
				Client.windowEvent(e);
				break;
			case "CS_DISCONNECT_BUTTON":
				Client.windowEvent(e);
				break;
		}
	}

	@Override
	public void reload()
	{
		mLoginButton.setEnabled(true);
		mCreateUserButton.setEnabled(true);
		mDisconnectButton.setEnabled(true);
	}
}
