package numeric;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBigDecimalSQL
{
    public static void main(String[] args)
    {
        //BigDecimal data1 = BigDecimal.valueOf(400995723442579.1234);
        BigDecimal data1 = BigDecimal.valueOf(995723442579.1234);

        Double data2 = 400995723442579.1234;

        System.out.println(data1);
        System.out.println(data2);

        Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);
        System.out.println("Java's Default Date Format: " + now);
        System.out.println("Mysql's Default Date Format: " + mysqlDateString);
    }
}