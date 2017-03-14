package itech.funct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFunct 
{
	private Connection connect = null;	
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public boolean checkUser(String user, String pass)
	{	
		boolean result = false;		
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/itech?" + "user=root&password=");		
			
			preparedStatement = connect.prepareStatement("select * from ITECH.LOGIN where username = '" + user + "' and password = '" + pass +"'");
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				result = true;				
			}
			resultSet.close();
		} 
		catch (ClassNotFoundException e)
		{			
			e.printStackTrace();
		} 
		catch (SQLException e)
		{		
			e.printStackTrace();
		}
		finally
		{			
			try
			{
				if(preparedStatement!=null)
					preparedStatement.close();
			}
			catch(SQLException se2)
			{
				se2.printStackTrace();
			}
			try
			{
				if(connect!=null)
					connect.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean checkUser2(String user, String pass)
	{
		boolean result = false;				
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=itech;user=root;password=";	
		Connection con = null;
		Statement stmt = null;	
				
		try
		{		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");							
			
			System.out.println("Connecting to a selected database...");
			con = DriverManager.getConnection(connectionUrl);
			System.out.println("Connected database successfully...");					 
		
			System.out.println("Creating statement...");
			stmt = con.createStatement();			
			String sql = "SELECT username, password FROM login where username = '" + user + "' and password = '" + pass + "'";
			ResultSet rs = stmt.executeQuery(sql);				   
	
			while(rs.next())
			{
				result = true;				
			}
			rs.close();
		}
		catch(SQLException se)
		{		
			se.printStackTrace();
		}
		catch(Exception e)
		{			
			e.printStackTrace();
		}
		finally
		{			
			try
			{
				if(stmt!=null)
					stmt.close();
			}
			catch(SQLException se2)
			{
				se2.printStackTrace();
			}
			try
			{
				if(con!=null)
					con.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return result;		
	}
}
