package itech.funct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TempPendaftaran 
{
	public void appendFile(String fileName, String data)
	{
		try
		{    	
    		File file = new File(fileName);
    		
    		//if file doesnt exists, then create it
    		if(!file.exists())
    		{
    			file.createNewFile();
    		}
 
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file.getName(), true);
    		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    		bufferWritter.write(data + "\n");
    		bufferWritter.close(); 
	        //System.out.println("Done"); 
    	}
		catch(Exception e)
		{
			System.err.println("Error: " + e.getMessage());
    	}	
	}	
	
	public void emptyFile(String fileName)
	{
		try
		{
			PrintWriter writer = new PrintWriter(fileName);
			writer.print("");
			writer.close();
		}
		catch(Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public String getSpecificFile(String fileName, int lineNumber)
	{
		String line = "";
        int lineNo;
        try
        {
        	FileReader fr = new FileReader(fileName);
        	BufferedReader br = new BufferedReader(fr);
        	for (lineNo = 1; lineNo < 100; lineNo++)
        	{
        		if (lineNo == lineNumber)
        		{
        			line = br.readLine();
        		}
        		else
        		{
        			br.readLine();
        		}
        	}
        	fr.close();
        	br.close();
        } 
        catch (IOException e) 
        {
        	e.printStackTrace();
        }
        
        //System.out.println("Line: " + line);
        return line;
	}
}