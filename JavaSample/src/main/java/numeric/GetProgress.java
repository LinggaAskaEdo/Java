package numeric;

public class GetProgress
{
    public static void main(String[] args)
    {
        double a = 7000000;
        double b = 2725000;
        int c = (int) ((b / a) * 100);
        int d = (int) (a + b);
        System.out.println(c);
        System.out.println(d);
    }
}