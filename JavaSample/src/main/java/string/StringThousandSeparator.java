package string;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import static java.lang.System.*;

public class StringThousandSeparator
{
    public static void main(String[] args)
    {
        String number = "4591357976985";
        String result = generateThousandSeparator(number);
        out.println(result);
    }

    private static String generateThousandSeparator(String number)
    {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        formatter.setDecimalFormatSymbols(symbols);

        return formatter.format(new BigInteger(number));
    }
}