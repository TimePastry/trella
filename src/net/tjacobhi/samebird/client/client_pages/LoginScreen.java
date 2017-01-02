package net.tjacobhi.samebird.client.client_pages;

import net.tjacobhi.samebird.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tjacobhi on 01-Jan-17.
 */
public class LoginScreen extends JPanel implements ActionListener,
                                                   Reloadable
{
	private JTextField mUsernameField;
	private JPasswordField mPasswordField;
	private JButton mSigninButton;
	private JButton mBackButton;

	public LoginScreen()
	{
		super();

		mUsernameField = new JTextField(16);
		mUsernameField.setBounds(75, 160, 150, 30);
		add(mUsernameField);

		mPasswordField = new JPasswordField(32);
		mPasswordField.setBounds(75, 260, 150, 30);
		add(mPasswordField);

		mSigninButton = new JButton("Sign In");
		mSigninButton.setBounds(75, 360, 150, 30);
		mSigninButton.setActionCommand("LS_SIGNIN_BUTTON");
		mSigninButton.addActionListener(this);
		add(mSigninButton);

		mBackButton = new JButton("Back");
		mBackButton.setBounds(75, 460, 150, 30);
		mBackButton.setActionCommand("LS_BACK_BUTTON");
		mBackButton.addActionListener(this);
		add(mBackButton);

		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawString("Username", 75, 150);
		graphics.drawLine(75, 155, 133, 155);
		graphics.drawString("Password", 75, 250);
		graphics.drawLine(75, 255, 130, 255);

		graphics.setColor(new Color(0x3589c5));
		Font font = new Font(null, Font.PLAIN, 30);
		graphics.setFont(font);
		graphics.drawString("Same-bird Login", 30, 60);

		graphics.setColor(new Color(0xbb9922));
		graphics.fillRoundRect(300, 100, 400, 400, 20, 20);
	}

	@Override
	public void reload()
	{

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "LS_BACK_BUTTON":
				Client.windowEvent(e);
				break;
		}
	}
}
