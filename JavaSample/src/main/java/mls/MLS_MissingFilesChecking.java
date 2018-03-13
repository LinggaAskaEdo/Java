package mls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by edo on 30/11/16.
 */

public class MLS_MissingFilesChecking
{
    String counter = "16";

    public static void main(String[] args)
    {
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        //get date now
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String markerNow = simpleDateFormat.format(new Date());
        //System.out.println("markerNow: " + markerNow);

        try
        {
            String marker = "2016-11-27";
            Date date = simpleDateFormat.parse(marker);
            cal1.setTime(date);

            date = simpleDateFormat.parse(markerNow);
            cal2.setTime(date);

            int diffDays = daysBetween(cal1.getTime(), cal2.getTime());
            System.out.println("Days: " + diffDays);

            if ((diffDays >= 0) && (diffDays <= 2))
                System.out.println("Don't full update");
            else
                System.out.println("Do full update");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    private static int daysBetween(Date d1, Date d2)
    {
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}