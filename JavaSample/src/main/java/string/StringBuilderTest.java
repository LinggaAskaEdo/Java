package string;

import java.math.BigDecimal;

public class StringBuilderTest
{
    public static void main(String[] args)
    {
        StringBuilder builder = new StringBuilder();
        addWord(builder);
        System.out.println(builder.toString());

        BigDecimal totalInvoice = new BigDecimal(5003451.83);

        String totalDown = totalInvoice.setScale(0, BigDecimal.ROUND_HALF_DOWN).toString();
        System.out.println(totalDown);

        String totalEven = totalInvoice.setScale(0, BigDecimal.ROUND_HALF_EVEN).toString();
        System.out.println(totalEven);

        String total = totalInvoice.toBigInteger().toString();
        System.out.println(total);
    }

    private static void addWord(StringBuilder builder)
    {
        builder.append("Aska");
        builder.append(" ");
        builder.append("Cool");
    }
}