package com.main.java;

import java.io.FileInputStream;
import java.util.Properties;

public class Database {

	public Properties myPanel;
	public Properties myLanguange;
	private String namePanel;
	
	public Database() {
		
	}
	
	public String SettingPanel (String nmPanel) {
		
		try {
			myPanel = new Properties();
			myPanel.load(new FileInputStream("/home/dery/eclipse-workspace/Database.ini"));
			namePanel = myPanel.getProperty(nmPanel);
		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "Connection Refuse", "Error",
//			JOptionPane.INFORMATION_MESSAGE);
//			System.err.println(e.getMessage());
			System.exit(0);
		}
		return namePanel;
	}
}
