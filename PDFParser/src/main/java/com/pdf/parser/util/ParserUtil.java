package com.pdf.parser.util;

import com.google.gson.Gson;
import com.pdf.parser.dao.ParserDAO;
import com.pdf.parser.model.Content;
import com.pdf.parser.model.Data;
import com.pdf.parser.model.Konsolidasi;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.util.*;

public class ParserUtil 
{
	private static final String KANTOR_PABEAN = "KANTOR PABEAN ";

	public boolean readPdf(String text)
	{
		boolean status = false;
		NavigableMap<Double, String> map = new TreeMap<>();

		try (PDDocument document = PDDocument.load(new File(text)))
		{
			if (!document.isEncrypted())
			{
			    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			    stripper.setSortByPosition(true);
			    PDFTextStripper textStripper = new PDFTextStripper();
			    String textContent = textStripper.getText(document);
			    
			    System.out.println("---Split---");
		        int count = 1;

		        // split by new line
		        String[] lines = textContent.split("\\r?\\n");
		        
		        for (String line : lines) 
		        {
		            map.put((double) count, line);
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
	
	private void mappingObject(NavigableMap<Double, String> map)
	{
		Content content = new Content();
		
		content.setNomorPengajuan(map.get(1d));
		content.setNomorTanggalPendaftaran(map.get(2d));

		generateDetailsObject(map, content);

		String body = new Gson().toJson(content);

		System.out.println(body);

		//saving to database
		System.out.print("Process to database: ");

		ParserDAO parserDAO = new ParserDAO();

		if (parserDAO.checkContent(content.getNomorPengajuan()))
		{
			if (parserDAO.updateContent(content.getNomorPengajuan(), body))
				System.out.println("SUCCESS UPDATE !!!");
			else
				System.out.println("FAIL UPDATE !!!");
		}
		else
		{
			if (parserDAO.insertContent(content.getNomorPengajuan(), body))
				System.out.println("SUCCESS INSERT !!!");
			else
				System.out.println("FAIL INSERT !!!");
		}
	}

	private void generateDetailsObject(NavigableMap<Double, String> map, Content content)
	{
		Konsolidasi konsolidasi = new Konsolidasi();
		List<Data> dataList = new ArrayList<>();

		for (Map.Entry<Double, String> entry : map.entrySet())
		{
			System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue());

			String value = entry.getValue();

			if (value.contains("Merek/Nomor Peti Kemas "))
			{
				System.out.println("AAA");
				content.setNomorPetiKemas(StringUtils.substringBetween(value, "Merek/Nomor Peti Kemas ", ":"));
			}
			else if (value.contains("Ukuran Peti Kemas "))
			{
				System.out.println("BBB");
				content.setUkuranPetiKemas(StringUtils.substringBetween(value, "Ukuran Peti Kemas ", ":"));
			}
			else if (value.contains("Tempat & Tanggal Pelaksanaan Stuffing "))
			{
				System.out.println("CCC");
				content.setTempatStuffing(StringUtils.substringBetween(value, "Tempat & Tanggal Pelaksanaan Stuffing ", " /"));
				content.setTanggalStuffing(StringUtils.substringBetween(value, "Tanggal: ", ":"));
			}
			else if (value.contains("NPWP "))
			{
				System.out.println("DDD");
				konsolidasi.setNpwp(StringUtils.substringBetween(value, "NPWP ", ":"));
			}
			else if (value.contains("NAMA "))
			{
				System.out.println("EEE");
				konsolidasi.setNama(StringUtils.substringBetween(value, "NAMA ", ":"));
			}
			else if (value.contains("ALAMAT "))
			{
				System.out.println("FFF");
				Map.Entry<Double, String> next = map.higherEntry(entry.getKey());
				konsolidasi.setAlamat(StringUtils.substringAfter(value, "ALAMAT ") + " " + next.getValue());
			}
			else if (value.contains(KANTOR_PABEAN))
			{
				System.out.println("GGG");
				konsolidasi.setPabeanAsal(StringUtils.substringBetween(value, KANTOR_PABEAN, ":"));
				konsolidasi.setPabeanEkspor(StringUtils.substringBetween(value, KANTOR_PABEAN, ":"));
			}
			else if (value.contains("NEGARA TUJUAN "))
			{
				System.out.println("HHH");
				konsolidasi.setNegaraTujuan(StringUtils.substringBetween(value, "NEGARA TUJUAN ", ":"));
			}
			else if (value.contains("NO.VOY / FLIGHT "))
			{
				System.out.println("III");
				konsolidasi.setNoFlight(StringUtils.substringBetween(value, "NO.VOY / FLIGHT ", ":"));
			}

			if (value.contains("SARANA PENGANGKUT: "))
			{
				System.out.println("JJJ");
				konsolidasi.setSarana(StringUtils.substringAfter(value, "NAMA SARANA PENGANGKUT: "));
			}

			if (value.contains("CT"))
			{
				int x = 0;
				int[] separator = new int[15];

				for (int i = -1; (i = value.indexOf(' ', i + 1)) != -1; i++)
				{
					separator[x] = i;
					x++;
				}

				String tanggalPEB = value.substring(separator[0] + 1, separator[0] + 11);
				String nomorPEB = value.substring(separator[0] + 11, separator[3]);
				String nomorNPE = value.substring(separator[3] + 1, separator[4]);
				String tanggalNPE = value.substring(separator[4] + 1, separator[5]);
				String keterangan = value.substring(separator[5] + 1);

				Data data = new Data();
				data.setNomorPEB(nomorPEB);
				data.setTanggalPEB(tanggalPEB);
				data.setNomorNPE(nomorNPE);
				data.setTanggalNPE(tanggalNPE);
				data.setKeterangan(keterangan);

				dataList.add(data);
			}
		}

		content.setKonsolidasi(konsolidasi);

		if (!dataList.isEmpty())
			content.setDataList(dataList);
	}
}