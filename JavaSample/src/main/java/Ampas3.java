import java.math.BigDecimal;

public class Ampas3
{
    public static void main(String[] args)
    {
        Double a = 0.008;
        Double b = 0.01;

        if (a != 0 && b != 0)
            System.out.println("AAA");
        else
            System.out.println("BBB");

        BigDecimal x = BigDecimal.valueOf(0.09);
        BigDecimal y = BigDecimal.valueOf(0.211);

        if ((x.compareTo(BigDecimal.ZERO) != 0) && (y.compareTo(BigDecimal.ZERO) != 0))
            System.out.println("XXX");
        else
            System.out.println("YYY");
    }
}