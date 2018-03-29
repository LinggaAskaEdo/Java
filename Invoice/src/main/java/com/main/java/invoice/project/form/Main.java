package com.main.java.invoice.project;

import com.main.java.invoice.project.form.LoginForm;
import com.main.java.invoice.project.function.SshFunction;

import javax.swing.*;

public class Main
{
	public static void main (String[] args)
	{
		/*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		MenuForm menuForm = new MenuForm();
		menuForm.setExtendedState(MenuForm.MAXIMIZED_BOTH);
		menuForm.setSize(screenSize);
		menuForm.setVisible(true);
		menuForm.setResizable(false);
		menuForm.setDefaultCloseOperation(MenuForm.EXIT_ON_CLOSE);*/
		
		//ConnectionCheck check = new ConnectionCheck();
		//check.setVisible(true);

		SshFunction sshFunction = new SshFunction();

		if (sshFunction.getConfigBySsh())
		{
			LoginForm loginForm = new LoginForm();
			loginForm.setVisible(true);
		}
		else
		{
			// create a jframe
			//JFrame frame = new JFrame("");

			// show a joptionpane dialog using showMessageDialog
			JOptionPane.showMessageDialog(null, "Can't read configuration file", "Program Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}