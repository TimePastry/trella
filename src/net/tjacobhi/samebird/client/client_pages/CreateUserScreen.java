package net.tjacobhi.samebird.client.client_pages;

import net.tjacobhi.samebird.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tjacobhi on 02-Jan-17.
 */
public class CreateUserScreen extends JPanel implements Reloadable,
                                                        ActionListener
{


	private JTextField mUsernameField;
	private JPasswordField mPasswordField;
	private JPasswordField mConfirmPasswordFeild;
	private JButton mCreateUserButton;
	private JButton mBackButton;

	public CreateUserScreen()
	{
		super();

		mUsernameField = new JTextField(16);
		mUsernameField.setBounds(75, 140, 150, 30);
		add(mUsernameField);

		mPasswordField = new JPasswordField(32);
		mPasswordField.setBounds(75, 220, 150, 30);
		add(mPasswordField);

		mConfirmPasswordFeild = new JPasswordField(32);
		mConfirmPasswordFeild.setBounds(75, 300, 150, 30);
		add(mConfirmPasswordFeild);

		mCreateUserButton = new JButton("Sign In");
		mCreateUserButton.setBounds(75, 380, 150, 30);
		mCreateUserButton.setActionCommand("CU_CREATEUSER_BUTTON");
		mCreateUserButton.addActionListener(this);
		add(mCreateUserButton);

		mBackButton = new JButton("Back");
		mBackButton.setBounds(75, 460, 150, 30);
		mBackButton.setActionCommand("CU_BACK_BUTTON");
		mBackButton.addActionListener(this);
		add(mBackButton);

		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.drawString("Username", 75, 130);
		graphics.drawLine(75, 135, 133, 135);
		graphics.drawString("Password", 75, 210);
		graphics.drawLine(75, 215, 130, 215);
		graphics.drawString("Confirm Password", 75, 290);
		graphics.drawLine(75, 295, 178, 295);

		graphics.setColor(new Color(0x3589c5));
		Font font = new Font(null, Font.PLAIN, 30);
		graphics.setFont(font);
		graphics.drawString("Same-bird Login", 30, 60);

		graphics.setColor(new Color(0xb729a5));
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
			case "CU_BACK_BUTTON":
				Client.windowEvent(e);
				break;
		}
	}
}
