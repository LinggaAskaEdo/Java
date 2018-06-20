import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Ampas4
{
    public static void main(String[] args)
    {
        /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String xxx = format.format(new Date());

        String sql = "SELECT NOW() BETWEEN '2017-12-10 17:00:00' AND '2018-12-11 16:59:59'";

        String sql2 = "SELECT " + xxx;

        System.out.println(sql.replaceAll("NOW\\(\\)", "'" + xxx + "'"));*/

        /*UUID uuid = UUID.randomUUID();
        int variant = uuid.variant();
        int version = uuid.version();
        System.out.println(String.valueOf(variant));
        System.out.println(String.valueOf(version));

        String temp = uuid.toString();
        System.out.println(temp);
        System.out.println(temp.substring(Math.max(temp.length() - 5, 0)));*/

        Date date = new Date();
        System.out.println(date);

        boolean status = after(new Date(2017, 5, 5), new Date(), 1, 12);
        System.out.println(status);

        if (7 >= 1 && 7 < 11 && (false || (true && status)))
        {
            System.out.println("Y");
        }
        else
        {
            System.out.println("N");
        }
    }

    private static boolean after(Date before, Date after, int count, int type)
    {
        boolean status = false;

        try
        {
            Calendar beforeCalendar = Calendar.getInstance();
            beforeCalendar.setTime(before);
            Calendar afterCalendar = Calendar.getInstance();
            afterCalendar.setTime(after);

            System.out.println("A: " + after.compareTo(before));
            System.out.println("B: " + afterCalendar.get(type));
            System.out.println("C: " + beforeCalendar.get(type));

            status = after.after(before) && afterCalendar.get(type) - beforeCalendar.get(type) >= count;
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e);
        }

        return status;
    }
}