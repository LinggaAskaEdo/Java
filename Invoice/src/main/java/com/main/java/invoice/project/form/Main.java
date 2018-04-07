package com.main.java.invoice.project.form;

import com.main.java.invoice.project.function.SshFunction;
import com.main.java.invoice.project.preference.StaticPreference;

import javax.swing.*;

public class Main
{
	public static void main (String[] args)
	{
		//SshFunction sshFunction = new SshFunction();

		//if (sshFunction.getConfigBySsh())
		if (true)
		{
			StaticPreference.URL = "jdbc:mysql://localhost:3306/INVOICE_PROJECT";
			StaticPreference.USERNAME = "dev";
			StaticPreference.PASSWORD = "Password";

			LoginForm loginForm = new LoginForm();
			loginForm.setVisible(true);
		}
		else
		{
			// show a joptionpane dialog using showMessageDialog
			JOptionPane.showMessageDialog(null, "Can't read configuration file", "Program Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}