package testonline;

public class TestShipperFizz
{
    public static void main(String[] args)
    {

        String[] secondNum = new String[]{"3", "15"};

        int paramFirst = Integer.parseInt(secondNum[0]);
        int paramSec = Integer.parseInt(secondNum[1]);

        System.out.println("paramFirst: " + paramFirst + ", paramSec: " + paramSec);
        System.out.println("AAA: " + 6 % paramFirst);
        System.out.println("BBB: " + paramSec % 5);

        for (String s : secondNum)
        {
            for (int x = 1; x <= Integer.parseInt(s); x++)
            {
                check(x, paramFirst, paramSec);
            }
        }
    }

    private static void check(int i, int paramFirst, int paramSec)
    {
        if (i != 1)
        {
            if (i == paramSec)
            {
                System.out.println("FizzBuzz");
            }
            else if (paramFirst % i == 0)
            {
                System.out.println("Fizz");
            }
            else if (paramSec % i == 0)
            {
                System.out.println("Buzz");
            }
            else
            {
                System.out.println(i);
            }
        }
        else
        {
            System.out.println(i);
        }
    }
}