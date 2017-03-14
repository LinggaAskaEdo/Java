package itech.funct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SeqNumber
{
	public String readSeqNumber(String fileName)
    {
        String num = "";
        
        try
        {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            
            int numberOfLines = 1;
            int i;        
                    
            for (i =0; i < numberOfLines; i++)
            {
                num = br.readLine();
            }
            //System.out.println("Num: " + num);
            br.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        return num;        
    }  
	
	public void updateSeqNumber(String data, String fileName)
    {
        try
        {
            // Create file 
            FileWriter fr = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(fr);
            out.write(data);			
            //Close the output stream
            out.close();
            //System.out.println("Done"); 
        }
        catch (Exception e)
        {
            //Catch exception if any
             e.printStackTrace();
        }
    }

    public String countSeqNumber(String num)
    {      
        int intNewNum;
        String strNewNum;
        
        intNewNum = Integer.parseInt(num) + 1;
        strNewNum = Integer.toString(intNewNum);
        
        return strNewNum;
    }
}
