package com.pdf.parser.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.pdf.parser.model.Content;
import com.pdf.parser.model.Konsolidasi;

public class ParserUtil 
{
	public boolean readPdf(String text) 
	{
		boolean status = false;
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		try
		{
			PDDocument document = null; 
			document = PDDocument.load(new File(text));
			document.getClass();
			
			if (!document.isEncrypted())
			{
			    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			    stripper.setSortByPosition(true);
			    PDFTextStripper Tstripper = new PDFTextStripper();
			    String textContent = Tstripper.getText(document);
			    
//			    System.out.println("Text: " + textContent);
			    System.out.println("---Split---");
		        int count = 1;

		        // split by new line
		        String[] lines = textContent.split("\\r?\\n");
		        
		        for (String line : lines) 
		        {
		            System.out.println("line " + count + " : " + line);
		            map.put(count, line);
		            count++;
		        }
		        
		        //mapping content to object
		        mappingObject(map);
		        
		        status = true;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error when reading PDF: " + ex.getMessage());
		}
		
		return status;
	}
	
	private Date reformatDate(String date)
	{
		Date result = null;
		
		try 
		{
			result = new SimpleDateFormat("dd-MM-yyyy").parse(date);
		}
		catch (ParseException ex) 
		{
			System.out.println("Error when parsing date: " + ex.getMessage());
		}
		
		return result;
	}

	private void mappingObject(Map<Integer, String> map)
	{
		Content content = new Content();
		
		content.setNomorPengajuan(map.get(1));
		content.setNomorTanggalPendaftaran(map.get(2));
		content.setNomorPetiKemas(StringUtils.substringBetween(map.get(11), "Merek/Nomor Peti Kemas ", ":"));
		content.setUkuranPetiKemas(StringUtils.substringBetween(map.get(12), "Ukuran Peti Kemas ", ":"));
		content.setTempatStuffing(StringUtils.substringBetween(map.get(13), "Tempat & Tanggal Pelaksanaan Stuffing ", " /"));
		
		System.out.println("TEST: " + map.get(13));
		System.out.println("TEST1: " + StringUtils.substringBetween(map.get(13), "Tanggal: ", ":"));
		
		content.setTanggalStuffing(reformatDate(StringUtils.substringBetween(map.get(13), "Tanggal: ", ":")));
		
		Konsolidasi konsolidasi = new Konsolidasi();
		konsolidasi.setNPWP(StringUtils.substringBetween(map.get(17), "NPWP ", ":"));
		konsolidasi.setNama(StringUtils.substringBetween(map.get(18), "NAMA ", ":"));
		konsolidasi.setAlamat(StringUtils.substringAfter(map.get(19), "ALAMAT ") + " " + map.get(20));
		konsolidasi.setPabeanAsal(StringUtils.substringBetween(map.get(27), "KANTOR PABEAN ", ":"));
		konsolidasi.setPabeanEkspor(StringUtils.substringBetween(map.get(27), "KANTOR PABEAN ", ":"));
//		konsolidasi.setNegaraTujuan(StringUtils.substringBetween(map.get(83), "KANTOR NEGARA TUJUAN ", ":"));
//		konsolidasi.setSarana(StringUtils.substringAfter(map.get(27), "NAMA SARANA PENGANGKUT: "));
//		konsolidasi.setNoFlight(StringUtils.substringBetween(map.get(27), "KANTOR PABEAN ", ":"));
		
		content.setKonsolidasi(konsolidasi);
		
		System.out.println(content);
	}
}