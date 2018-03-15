package date;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class SampleDate
{
    public static void main(String[] args)
    {
        //Date date = new Date(Calendar.getInstance().getTime().getTime());

        //System.out.println("Date: " + date);

        //String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        //System.out.println("Current Time: " + currentTime);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
}