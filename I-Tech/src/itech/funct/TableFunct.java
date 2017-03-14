package itech.funct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class TableFunct 
{
	private Connection connect = null;	
	private Statement stat = null;	
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private ResultSetMetaData md = null;
	private DefaultTableModel model = new DefaultTableModel();
	private String query = null;
	
	public int rowCountFunct(String string) 
	{
		int rowCount = 0;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/itech?" + "user=root&password=");		
			preparedStatement = connect.prepareStatement("select count(*) from " + string);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				rowCount = resultSet.getInt(1);
			}				
			resultSet.close();
			System.out.println("Total Baris: " + rowCount);
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
			close();
		}
		
		return rowCount;
	}
	
	public int colCountFunct(String string)
	{
		int columnCount = 0;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/itech?" + "user=root&password=");				
			stat = connect.createStatement();
			stat.executeQuery("select * from " + string);
			resultSet = stat.getResultSet();
			md = resultSet.getMetaData();
			columnCount = md.getColumnCount();			
			resultSet.close();
			System.out.println("Total Kolom: " + columnCount);
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
			close();
		}		
		return columnCount;
	}	
	
	public Object[][] generateDataFunct(int colTotal, int rowTotal, String string)
	{
		Object[][] data = new Object[rowTotal][colTotal];	
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/itech?" + "user=root&password=");				
			stat = connect.createStatement();		
			resultSet =	stat.executeQuery("select * from " + string);
			
			int i = 0;
			
			while (resultSet.next())
			{								
				for (int j = 0; j < colTotal; j++)
				{				
					//part.append(str);
					if ((string.equalsIgnoreCase("pendaftarandatadiri")) && (j == 3)) //skip menampilkan data foto
						j = j + 1;
					data[i][j] = resultSet.getString(j + 1);
					System.out.println("Data[" + i + "][" + j + "] = " + data[i][j]);
				}	
				i = i + 1;				
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
			close();
		}
		return data;
	}	
	
	private void close()
	{
		try 
		{
			if (resultSet != null)
			{
				resultSet.close();
			}
	
			if (stat != null) 
			{
				stat.close();
			}
	
			if (connect != null) 
			{
				connect.close();
			}
		} 
		catch (Exception e) 
		{}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DefaultTableModel loadTable(String stringData)
	{
		if(stringData.equalsIgnoreCase("BukuTamu"))
			query = "select * from ITECH.BUKU_TAMU";
		else if(stringData.equalsIgnoreCase("DataDiri"))
			query = "select * from ITECH.PENDAFTARANDATADIRI";
		else if(stringData.equalsIgnoreCase("DataLain"))
			query = "select * from ITECH.PENDAFTARANDATALAIN";
		else if(stringData.equalsIgnoreCase("DataSekolah"))
			query = "select * from ITECH.PENDAFTARANDATASEKOLAH";
		else
			query = "select * from ITECH.PENDAFTARANDATASUMBER";
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");			
			connect = DriverManager.getConnection("jdbc:mysql://localhost/itech?" + "user=root&password=");		
			stat = connect.createStatement();
			stat.executeQuery(query);
			resultSet = stat.getResultSet();
			md = resultSet.getMetaData();
			int columnCount = md.getColumnCount();
			Vector data = new Vector();
			Vector columnNames = new Vector();
			Vector row = new Vector(columnCount);
			
			for (int i = 1; i <= columnCount; i++)
			{
				columnNames.addElement(md.getColumnName(i));
			}
			
			while (resultSet.next()) 
			{
				for (int i = 1; i <= columnCount; i++) 
				{
					row.addElement(resultSet.getObject(i));
				}
				data.addElement(row);
				row = new Vector(columnCount);
			}
			resultSet.close();
			model = new DefaultTableModel(data, columnNames);			
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
				if(stat != null)
					stat.close();
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
		
		return model;
	}	
}
