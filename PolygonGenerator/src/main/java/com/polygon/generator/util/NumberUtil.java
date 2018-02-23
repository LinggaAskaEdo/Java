package com.polygon.generator.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

class NumberUtil
{
	static Double getTrimmedDecDigitDouble(Number number, int decimalDigit)
	{
		Double newValue = null;

		if (number != null)
		{
			String numberString = getTrimmedDecDigitString(number, decimalDigit);
			
			if (numberString != null && !numberString.isEmpty())
			{
				newValue = Double.valueOf(numberString);
			}
		}

		return newValue;
	}
	
	private static String getTrimmedDecDigitString (Number number, int decimalDigit)
	{
		if (number != null)
		{
			DecimalFormat decimalFormat;
			StringBuilder formatString = new StringBuilder();
			
			if (decimalDigit < 1)
			{
				formatString.append("0");
			}
			else
			{
				formatString.append("0.");
				
				int decimalLength = getDecimalLength(number);

				for (int i = 0; i < decimalDigit; i++)
				{
					if (decimalLength > -1 && i >= decimalLength)
					{
						break;
					}
					else
					{
						formatString.append("0");
					}
				}
			}
			
			decimalFormat = new DecimalFormat(formatString.toString());
			decimalFormat.setRoundingMode(number.doubleValue()>0?RoundingMode.FLOOR:RoundingMode.CEILING);
			
			return decimalFormat.format(number);
		}

		return null;
	}
	
	private static int getDecimalLength(Number number)
	{
		int decimalLength = -1;
		
		if (number != null)
		{
			String numberString = number.toString();
			int decPos = numberString.indexOf(".");
			
			if (decPos > -1)
			{
				String decimalString = numberString.substring(decPos+1);
				decimalLength=decimalString.length();
			}
		}
		
		return decimalLength;
	}
}