package itech.funct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class QuizFunct
{
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/itech";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	
	private static String[] fileAnswer = new String[10];
	private static int totalScore = 0;
	
	public String getTotalScore() throws SQLException
	{
		//project path
		System.out.println("Project Path: " + System.getProperty("user.dir") + "\n");
				
		System.out.println("Start reading quiz answer...");
				
		//fill array
		populateStringArray();

		//loop array
		for (String s: fileAnswer)
		{
			System.out.println(s + ".dat");
			
			try(BufferedReader br = new BufferedReader(new FileReader(s + ".dat")))
			{
				for(String line; (line = br.readLine()) != null; )
				{	
					int score = getAnswerScore(s, line);
					System.out.println(line + ": " + score);
					totalScore = totalScore + score;
				}
				System.out.print("\n");
			} 		
			catch (IOException e)
			{			
				e.printStackTrace();
			}		
		}		
		System.out.print("Total score: " + totalScore + "/80");
		double doubTotalScore = totalScore;
		double gradeScore = (double) ((doubTotalScore/80.0) * 100);
		DecimalFormat df = new DecimalFormat("#####0.00");	
		
		return df.format(gradeScore);
	}

	private static void populateStringArray()
	{
		fileAnswer[0] = "qm";
		fileAnswer[1] = "qq0";
		fileAnswer[2] = "qq1";	    
		fileAnswer[3] = "qq2";	
		fileAnswer[4] = "qq3";	
		fileAnswer[5] = "qq4";	
		fileAnswer[6] = "qq5";	
		fileAnswer[7] = "qq6";	
		fileAnswer[8] = "qq7";	
		fileAnswer[9] = "qq8";	
	}
	
	private static int getAnswerScore(String s, String line) throws SQLException
	{
		int result = 0;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "select status_quiz from answer_quiz where code_quiz = ? and label_quiz = ?";
		try 
		{
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, s);
			preparedStatement.setString(2, line);
			ResultSet rs = preparedStatement.executeQuery();
			
			/*preparedStatement = connect.prepareStatement("select status_quiz from answer_quiz where code_quiz = '" + s + "' and label_quiz = '" + line +"'");
			resultSet = preparedStatement.executeQuery();*/
			
			if(rs.next())
			{
				if(rs.getString(1).equalsIgnoreCase("T"))
					result = 1;
			}
		} 
		catch (SQLException e)
		{		
			e.printStackTrace();
		}
		finally
		{
			if (preparedStatement != null) { preparedStatement.close(); }		
 			if (dbConnection != null) { dbConnection.close(); }
		}	
		
		return result;
	}
	
	public String getNameScore(String data) throws SQLException
	{
		String result = null;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "select nama_bt from buku_tamu where id = ?";
		
		try 
		{
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, data);			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
			{
				result = rs.getString(1);
			}
		} 
		catch (SQLException e)
		{		
			e.printStackTrace();
		}
		finally
		{			
			if (preparedStatement != null) { preparedStatement.close(); }		
 			if (dbConnection != null) { dbConnection.close(); }
		}
		
		return result;
	}
	
	public boolean saveScore(String valSeqNum, String finalScore, String finalPrice) throws SQLException
	{
		boolean status = false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "INSERT INTO final_score (noPendaftar, score_value, price_value) VALUES (?, ?, ?)";
		
		try
		{
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, valSeqNum);
			preparedStatement.setString(2, finalScore);
			preparedStatement.setString(3, finalPrice);
		
			int rowsInserted = preparedStatement.executeUpdate();
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
			if (preparedStatement != null) { preparedStatement.close(); }		
 			if (dbConnection != null) { dbConnection.close(); }
		}
		 
		return status;
	}

	public String[] getDataScore(String tableClick) throws SQLException
	{
		String[] data = new String[5];
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String sql = "select score_value, price_value from final_score where id = ?";
		
		try 
		{
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(tableClick));
 
			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
 
			if (rs.next())
			{
				data[0] = rs.getString("score_value");
				data[1] = rs.getString("price_value");
			}
			/*System.out.println(data[0]);
			System.out.println(data[1]);*/
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if (preparedStatement != null) { preparedStatement.close(); }
 			if (dbConnection != null) { dbConnection.close(); }
		}
		
		return data;
	}

	private static Connection getDBConnection()
	{
		Connection dbConnection = null;
		 
		try
		{
			Class.forName(DB_DRIVER);
 
		} 
		catch (ClassNotFoundException e) 
		{
 			System.out.println(e.getMessage());
 		}
 
		try 
		{
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
 
		} 
		catch (SQLException e) 
		{
 			System.out.println(e.getMessage());
 		}
 
		return dbConnection;
	}	
}