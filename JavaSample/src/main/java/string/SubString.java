package string;

public class SubString
{
    public static void main(String[] args)
    {
        String xxx = "410437******0556";
        String yyy = "0556";

        System.out.println(xxx.substring(xxx.length() - 4).equalsIgnoreCase(yyy));
    }
}