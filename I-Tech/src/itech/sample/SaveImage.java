package itech.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SaveImage
{
	public static void main(String[] args) 
	{
		DB db = new DB();
		Connection conn = db.dbConnect("jdbc:sqlserver://localhost:1433;databaseName=test;user=sa;password=root");
		db.insertImage(conn,"d://test.JPG");
		db.getImageData(conn);
	}
}

class DB
{
	public DB()
	{}

	public Connection dbConnect(String connUrl)
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(connUrl);
			System.out.println("connected");
			return conn;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void insertImage(Connection conn,String img)
	{
		int len;
		String query;
		PreparedStatement pstmt;
                
		try
		{
			File file = new File(img);
			FileInputStream fis = new FileInputStream(file);
			len = (int)file.length();

			query = ("insert into TableImage VALUES(?,?,?)");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,file.getName());
			pstmt.setInt(2, len);
  
			// Method used to insert a stream of bytes
			pstmt.setBinaryStream(3, fis, len); 
			pstmt.executeUpdate();
			System.out.println("inserted");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getImageData(Connection conn)
	{
		byte[] fileBytes;
		String query;
		try
		{
			query = "select fileImage from TableImage";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			if (rs.next())
			{
				fileBytes = rs.getBytes(1);
				OutputStream targetFile = 	new FileOutputStream("d://new.JPG");
				targetFile.write(fileBytes);
				targetFile.close();
				System.out.println("selected");
			}        
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
};