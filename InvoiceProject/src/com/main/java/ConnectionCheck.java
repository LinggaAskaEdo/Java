package com.main.java;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ConnectionCheck extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Database Dbsetting;
	public String Driver;
	public String Database;
	public String Username; 
	public String Password;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ConnectionCheck frame = new ConnectionCheck();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ConnectionCheck() {
		
		Connection connection = null;
		String oo;
		Dbsetting = new Database();
        Driver = Dbsetting.SettingPanel("DBDriver");
        Database = Dbsetting.SettingPanel("DBDatabase");
        Username = Dbsetting.SettingPanel("DBUsername");
        Password = Dbsetting.SettingPanel("DBPassword");
        
        try {
        	Class.forName(Driver);
			connection = DriverManager.getConnection(Database, Username, Password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (connection != null) {    	
    		oo = "You made it, take control your database now!";
    	} else {
    		oo = "Failed to make connection!";
    	}
        
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(5, 5, 438, 1);
		contentPane.add(desktopPane);
		
		JLabel lblConnectionChecking = new JLabel("Connection Checking :");
		lblConnectionChecking.setBounds(31, 106, 165, 15);
		contentPane.add(lblConnectionChecking);
		
		JLabel lblNewLabel = new JLabel(oo);
		lblNewLabel.setBounds(197, 106, 239, 15);
		contentPane.add(lblNewLabel);
				
	}
}
