package date;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class KabisatYear
{
    public static void main(String[] args)
    {
        try
        {
            XMLGregorianCalendar gregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar("2020-12-01");

            GregorianCalendar calendar = gregorianCalendar.toGregorianCalendar();
            calendar.add(Calendar.MONTH, 1);

            XMLGregorianCalendar newPeriodStart = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(newPeriodStart.toGregorianCalendar().getTime()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}