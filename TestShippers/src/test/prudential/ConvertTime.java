package test.prudential;

public class ConvertTime
{
    public static void main(String[] args)
    {
        int time = 86399;

        int hours = time / 3600;
        System.out.println(hours);

        int remainder = time - hours * 3600;
        int mins = remainder / 60;
        System.out.println(mins);

        remainder = remainder - mins * 60;
        int secs = remainder;
        System.out.println(secs);

        System.out.println(String.valueOf(hours));
        int[] ints = {hours , mins , secs};
        System.out.println(ints[0] + "" + ints[1] + "" + ints[2]);
    }
}