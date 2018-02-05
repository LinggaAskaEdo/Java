package com.main.java.invoice.project.function;

import com.main.java.invoice.project.dao.KontrakDAO;
import com.main.java.invoice.project.preference.StaticPreference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class GeneralFunction 
{
	public String generateCodeName()
	{
		return generateCode1() + "/" + generateCode2() + "/" + generateCode3();
	}

	private String generateCode1()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date currentTime = new Date();

		return sdf.format(currentTime);
	}

	private String generateCode2()
	{
		UUID uuid = UUID.randomUUID();

		return uuid.toString().substring(Math.max(uuid.toString().length() - 5, 0));
	}

	private String generateCode3()
	{
		KontrakDAO kontrakDAO = new KontrakDAO();
		String result =  kontrakDAO.getLastId();

		System.out.println("result: " + result);
		
		return result.equalsIgnoreCase("") || result.equalsIgnoreCase("0") ? generateCodeNamex("1") :  generateCodeNamex(result);
	}

	private String generateCodeNamex(String counterValue)
	{
		StringBuilder extendName = new StringBuilder();

		int lengthCounter = counterValue.length();

		/*if (lengthCounter == 1)
		{
			counterValue = "0" + counterValue;
			System.out.println("AAA");
		}*/

		for (int x = 0; x < (StaticPreference.COUNTER_FILE_LENGTH - lengthCounter); x++)
		{
			System.out.println("BBB");
			extendName.append("0");
		}

		return String.valueOf(extendName) + counterValue;
	}
}