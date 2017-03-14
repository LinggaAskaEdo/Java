package itech.funct;

public class GradeFunct
{
	public static void main(String[] args)
	{
		System.out.println(getGrade(28.7500));
	}
	
	public static String getGrade(double d)
	{
		String total_bill = null;
		
		if(d >= 60)
			total_bill = "1.000.000";
		else if ((d >= 50) && (d < 60) )
			total_bill = "2.000.000";
		else if((d >= 40) && (d < 50))
			total_bill = "3.000.000";
		else if((d >= 30) && (d < 40))
			total_bill = "3.500.000";
		else
			total_bill = "12.000.000";
		
		return total_bill;
	}
}