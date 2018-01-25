package com.main.java.invoice.project.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectTest
{
	private String url;
	private String username;
	private String password;
	private Connection connection;

	ConnectTest(String url, String username, String password)
	{
		this.url = url;
		this.username = username;
		this.password = password;
	}

	void connect()
	{
		//System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();

			return;
		}

		//System.out.println("MySQL JDBC Driver Registered!");

		try
		{
			connection = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e) 
		{
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

			return;
		}

		if (connection != null) 
		{
			System.out.println("You made it, take control your database now!");
		}
		else 
		{
			System.out.println("Failed to make connection!");
		}
	}

	void disconnect()
	{
		try
		{
			if (connection != null && !connection.isClosed())
			{
				connection.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println("Exception disconnect: " + e.getMessage());
		}
	}
}