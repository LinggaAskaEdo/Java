import java.text.SimpleDateFormat;
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

        UUID uuid = UUID.randomUUID();
        int variant = uuid.variant();
        int version = uuid.version();
        System.out.println(String.valueOf(variant));
        System.out.println(String.valueOf(version));

        String temp = uuid.toString();
        System.out.println(temp);
        System.out.println(temp.substring(Math.max(temp.length() - 5, 0)));
    }
}