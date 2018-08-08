package calendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GregorianCalendarTest2
{
    public static void main(String[] args) throws DatatypeConfigurationException
    {
        /*GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());

        XMLGregorianCalendar result;

        DatatypeFactory factory = DatatypeFactory.newInstance();
        *//*result = factory.newXMLGregorianCalendar(
                gregorianCalendar.get(GregorianCalendar.YEAR),
                gregorianCalendar.get(GregorianCalendar.MONTH) + 1,
                gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH),
                gregorianCalendar.get(GregorianCalendar.HOUR_OF_DAY),
                gregorianCalendar.get(GregorianCalendar.MINUTE),
                gregorianCalendar.get(GregorianCalendar.SECOND),
                gregorianCalendar.get(GregorianCalendar.MILLISECOND), 0);*//*

        calendar.add(2,1);

        result = factory.newXMLGregorianCalendar(calendar);

        System.out.println(result);*/

        XMLGregorianCalendar request = DatatypeFactory.newInstance().newXMLGregorianCalendar("2017-11-06");
        XMLGregorianCalendar response;

        try
        {
            GregorianCalendar calendar = request.toGregorianCalendar();

            /*
             * Type Field of Calendar
             * 1. YEAR = 1
             * 2. MONTH = 2
             * 3. DAY_OF_MONTH = 5
             * */
            calendar.add(2, 2);

            response = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

            System.out.println("newPeriodStart: " + response);
        }
        catch (Exception e)
        {
            System.out.println("Error when generateNewCalendar: " + e);
        }
    }
}