package com.main.java;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {

	public static void main (String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		Menu menu = new Menu();
		menu.setExtendedState(Menu.MAXIMIZED_BOTH);
		menu.setSize(screenSize);
		menu.setVisible(true);
		menu.setResizable(false);
		menu.setDefaultCloseOperation(Menu.EXIT_ON_CLOSE);
		
		//ConnectionCheck check = new ConnectionCheck();
		//check.setVisible(true);
	}
}
