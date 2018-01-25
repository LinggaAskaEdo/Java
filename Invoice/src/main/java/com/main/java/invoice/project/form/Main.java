package com.main.java.invoice.project.form;

import com.main.java.invoice.project.function.SshFunction;

import java.awt.Dimension;
import java.awt.Toolkit;

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

		}
		else
		{

		}
	}
}