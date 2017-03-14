package itech.funct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SavePendaftaran
{
	private static final String dbDriver = "com.mysql.jdbc.Driver";
	private static final String dbURL = "jdbc:mysql://localhost:3306/itech";
	private static final String dbUsername = "root";
	private static final String dbPassword = "";
	
	TempPendaftaran tp = new TempPendaftaran();
	SeqNumber sn = new SeqNumber();
	
	private static Connection getDBConnection()
	{
		Connection dbConnection = null;
 
		try 
		{
			Class.forName(dbDriver); 
		}
		catch (ClassNotFoundException e) 
		{
 			System.out.println(e.getMessage());
 		}
 
		try
		{
 			dbConnection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			return dbConnection;
 		} 
		catch (SQLException e) 
		{
 			System.out.println(e.getMessage());
 		}
 
		return dbConnection;
 	}
	
	public void saveDataDiri() throws Exception
	{				        
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "insert into pendaftarandatadiri "
				+ "(id, noPendaftar, programStudi, foto, namaLengkap, namaPanggilan, tempatLahir, tanggalLahir, agama, jenisKelamin,"
				+ "alamatTinggal, nomorTelp, nomorHp, email, statusNikah, statusKerja) values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try 
		{			
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sql); 			
			preparedStatement.setString(1, tp.getSpecificFile("p1.dat", 1)); //no pendaftar
			preparedStatement.setString(2, tp.getSpecificFile("p1.dat", 2)); //program studi
			preparedStatement.setBinaryStream(3, getBinaryPict("test.jpg")); //foto
			preparedStatement.setString(4, tp.getSpecificFile("p1.dat", 3)); //nama lengkap
			preparedStatement.setString(5, tp.getSpecificFile("p1.dat", 4)); //nama panggilan
			preparedStatement.setString(6, tp.getSpecificFile("p1.dat", 5)); //tempat lahir			
			preparedStatement.setDate(7, java.sql.Date.valueOf(tp.getSpecificFile("p1.dat", 6))); //tanggal lahir
			preparedStatement.setString(8, tp.getSpecificFile("p1.dat", 7)); //agama
			preparedStatement.setString(9, tp.getSpecificFile("p1.dat", 8)); //jenis kelamin
			preparedStatement.setString(10, tp.getSpecificFile("p1.dat", 9)); //alamat tinggal
			preparedStatement.setString(11, tp.getSpecificFile("p1.dat", 10)); //nomor telp
			preparedStatement.setString(12, tp.getSpecificFile("p1.dat", 11)); //nomor hp
			preparedStatement.setString(13, tp.getSpecificFile("p1.dat", 12)); //email
			preparedStatement.setString(14, tp.getSpecificFile("p1.dat", 13)); //status nikah
			preparedStatement.setString(15, tp.getSpecificFile("p1.dat", 14)); //status kerja
			preparedStatement.executeUpdate();		
			System.out.println("Record is inserted !");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
	    } 
		finally
		{
			if (preparedStatement != null) { preparedStatement.close(); } 
			if (dbConnection != null) { dbConnection.close(); }
	    }
	}	
	
	public void saveDataLain() throws Exception
	{		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "insert into pendaftarandatalain (id, noPendaftar, namaBapak, pekerjaanBapak, namaIbu, pekerjaanIbu, penghasilan, sumberBiaya)"
				+ " values (default, ?, ?, ?, ?, ?, ?, ?)";
		
		try 
		{			
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sql); 	
			preparedStatement.setString(1, sn.readSeqNumber("pp.dat")); //no pendaftar
			preparedStatement.setString(2, tp.getSpecificFile("p1.dat", 15)); //nama bapak
			preparedStatement.setString(3, tp.getSpecificFile("p1.dat", 16)); //pekerjaan bapak
			preparedStatement.setString(4, tp.getSpecificFile("p1.dat", 17)); //nama ibu
			preparedStatement.setString(5, tp.getSpecificFile("p1.dat", 18)); //pekerjaan ibu
			preparedStatement.setString(6, tp.getSpecificFile("p1.dat", 19)); //penghasilan			
			preparedStatement.setString(7, tp.getSpecificFile("p1.dat", 20)); //sumber biaya			
			preparedStatement.executeUpdate();			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
	    } 
		finally
		{
			if (preparedStatement != null) { preparedStatement.close(); } 
			if (dbConnection != null) { dbConnection.close(); }
	    }
	}	
	
	public void saveDataSekolah() throws Exception
	{		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "insert into pendaftarandatasekolah (id, noPendaftar, namaSMP, jurusanSMP, alamatSMP, "
				+ "lulusSMP, namaSMU, jurusanSMU, alamatSMU, lulusSMU, tingkatPendidikan, namaInstitusi, jurusan, alamat, lulus, ipk)"
				+ " values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try 
		{		
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sql); 
			preparedStatement.setString(1, sn.readSeqNumber("pp.dat")); //no pendaftar
			preparedStatement.setString(2, tp.getSpecificFile("p2.dat", 1)); //nama smp
			preparedStatement.setString(3, tp.getSpecificFile("p2.dat", 2)); //jurusan smp
			preparedStatement.setString(4, tp.getSpecificFile("p2.dat", 3)); //alamat smp
			preparedStatement.setString(5, tp.getSpecificFile("p2.dat", 4)); //lulus smp
			preparedStatement.setString(6, tp.getSpecificFile("p2.dat", 5)); //nama smu			
			preparedStatement.setString(7, tp.getSpecificFile("p2.dat", 6)); //jurusan smu	
			preparedStatement.setString(8, tp.getSpecificFile("p2.dat", 7)); //alamat smu
			preparedStatement.setString(9, tp.getSpecificFile("p2.dat", 8)); //lulus smu
			preparedStatement.setString(10, tp.getSpecificFile("p2.dat", 9)); //tingkat pendidikan
			preparedStatement.setString(11, tp.getSpecificFile("p2.dat", 10)); //nama institusi
			preparedStatement.setString(12, tp.getSpecificFile("p2.dat", 11)); //jurusan
			preparedStatement.setString(13, tp.getSpecificFile("p2.dat", 12)); //alamat
			preparedStatement.setString(14, tp.getSpecificFile("p2.dat", 13)); //lulus
			preparedStatement.setString(15, tp.getSpecificFile("p2.dat", 14)); //ipk
			preparedStatement.executeUpdate();			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
	    } 
		finally
		{
			if (preparedStatement != null) { preparedStatement.close(); } 
			if (dbConnection != null) { dbConnection.close(); }
	    }
	}	

	public void saveDataSumber() throws Exception
	{			
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "insert into pendaftarandatasumber (id, noPendaftar, surat, selebaran, poster, perwakilan, presentasi, iklan, rekomendasi, pameran, lainnya)"
				+ " values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try 
		{			
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(sql); 
			preparedStatement.setString(1, sn.readSeqNumber("pp.dat")); //no pendaftar
			preparedStatement.setString(2, tp.getSpecificFile("p2.dat", 15)); //surat
			preparedStatement.setString(3, tp.getSpecificFile("p2.dat", 16)); //selebaran
			preparedStatement.setString(4, tp.getSpecificFile("p2.dat", 17)); //poster
			preparedStatement.setString(5, tp.getSpecificFile("p2.dat", 18)); //perwakilan
			preparedStatement.setString(6, tp.getSpecificFile("p2.dat", 19)); //presentasi		
			preparedStatement.setString(7, tp.getSpecificFile("p2.dat", 20)); //iklan	
			preparedStatement.setString(8, tp.getSpecificFile("p2.dat", 21)); //rekomendasi
			preparedStatement.setString(9, tp.getSpecificFile("p2.dat", 22)); //pameran
			preparedStatement.setString(10, tp.getSpecificFile("p2.dat", 23)); //lainnya
			preparedStatement.executeUpdate();			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
	    } 
		finally
		{
			if (preparedStatement != null) { preparedStatement.close(); } 
			if (dbConnection != null) { dbConnection.close(); }
	    }
	}	
	
	private InputStream getBinaryPict(String path) throws FileNotFoundException
	{
		File image = new File(path);
		FileInputStream fis = new FileInputStream(image);
		return fis;
	}
}