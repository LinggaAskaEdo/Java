package itech.funct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BukuTamuFunct 
{
	private static Connection conn = null;
	private static PreparedStatement statement = null;
	private ResultSet resultSet = null;
	
	public boolean addBukuTamu(String no, String nama, String alamat, String telp, String sekolah)
    {
        String dbURL = "jdbc:mysql://localhost:3306/itech";
        String username = "root";
        String password = "";
        boolean status = false;
        
        try
        {
            String sql = "INSERT INTO buku_tamu (no_bt, nama_bt, alamat_bt, telp_bt, sekolah_bt) VALUES (?, ?, ?, ?, ?)";
            conn = DriverManager.getConnection(dbURL, username, password);
            statement = conn.prepareStatement(sql);
            statement.setString(1, no);
            statement.setString(2, nama);
            statement.setString(3, alamat);
            statement.setString(4, telp);
            statement.setString(5, sekolah);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) 
            {
                status = true;
            }          
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }    
        finally
		{			
			try	{	if(statement != null)	statement.close();	}	catch(SQLException se2)	{	se2.printStackTrace();	}
			try	{	if(conn != null)	conn.close();	}	catch(SQLException se)	{	se.printStackTrace();	}
		}
        
        return status;
    }   
	
	public boolean checkBukuTamu(String noPendaftaran)
	{
		boolean result = false;		
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/itech?" + "user=root&password=");				
			statement = conn.prepareStatement("select * from ITECH.buku_tamu where no_bt = '" + noPendaftaran + "'");
			resultSet = statement.executeQuery();
			
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
			try { if(statement !=null) statement.close(); } catch(SQLException se2) { se2.printStackTrace(); }
			try { if(conn !=null) conn.close(); } catch(SQLException se) { se.printStackTrace(); }
		}
		
		return result;
	}
}
