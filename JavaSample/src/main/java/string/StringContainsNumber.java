package string;

public class StringContainsNumber
{
    public static void main(String[] args)
    {
        String codeBank = "a123a123a";

        if (codeBank.matches(".*[a-zA-Z].*"))
        {
            System.out.println("AAA");
        }
        else
        {
            System.out.println("BBB");
        }
    }
}