package testonline;

public class TestShipperMagicalString
{
    public static void main(String[] args)
    {
        String[] arrStrTest = new String[] {"kasurrusak", "rusak"};

        for (String data : arrStrTest)
        {
            int dataLength = data.length();

            if (dataLength % 2 == 0)
            {
                String data1 = data.substring(0, (dataLength / 2));
                String data2 = data.substring((dataLength / 2), dataLength);

                System.out.println(data1);
                System.out.println(data2);

                if (data1.equalsIgnoreCase(new StringBuilder(data2).reverse().toString()))
                {
                    System.out.println("It's Magical!");
                }
                else
                {
                    System.out.println("Not Magical");
                }
            }
            else
            {
                System.out.println("Not Magical");
            }
        }
    }
}
