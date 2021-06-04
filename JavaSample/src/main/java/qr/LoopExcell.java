package qr;

public class LoopExcell
{
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();

        for (int i = 0; i <= 1048576; i++)
        {
            System.out.println(i);
        }

        long end = System.currentTimeMillis();
        System.out.println("\nelapsedTime: " + (end - start) / 1000 + " sec");
    }
}