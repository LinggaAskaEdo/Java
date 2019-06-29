package testint;

public class TestInt
{
    public static void main(String[] args)
    {
        String myString = "My-Name".split("-")[0];
        String nameString = "My-Name".split("-")[1];

        System.out.println(myString);
        System.out.println(nameString);

        int a = 2;
        int b = 3;

        System.out.println(a + "-" + b);

        a = testsummary(a);

        System.out.println(a);
    }

    private static int testsummary(int a)
    {
        for (int i = 0; i < 3; i++)
        {
            a = a + i;
//            System.out.println(a);
        }

        return a;
    }
}