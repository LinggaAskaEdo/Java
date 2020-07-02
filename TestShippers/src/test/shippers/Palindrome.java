package test.shippers;

public class Palindrome
{
    public static void main(String[] args)
    {
        //case 1
        String input1 = "Abba";

        boolean status = true;

        char[] arrStr = input1.toCharArray();

        int lengthArrStr = arrStr.length;

        if (lengthArrStr % 2 == 0)
        {
            for (int i = 0; i < lengthArrStr / 2; i++)
            {
                if (arrStr[i] != arrStr[(lengthArrStr - 1) - i])
                {
                    status = false;
                    break;
                }
            }

            if (status)
                System.out.println("yes");
            else
                System.out.println("no");
        }
        else
        {
            System.out.println("no");
        }
    }
}