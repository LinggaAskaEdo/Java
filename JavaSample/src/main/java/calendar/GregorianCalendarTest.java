package calendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class GregorianCalendarTest
{
    public static void main(String[] args) throws ParseException, DatatypeConfigurationException
    {
        /*DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("1995-11-13");*/

        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse("13-11-1995");

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

        System.out.println(xmlGregCal);
    }
}