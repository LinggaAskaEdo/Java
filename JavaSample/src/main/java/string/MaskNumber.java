package string;

public class MaskNumber
{
    public static void main(String[] args)
    {
        //4111 1100 3859 0837
        String cc = "4111110038590837";
        int startCount = 0;
        int endCount = 4;

        if (cc.length() == 16)
        {
            String lastCreditCardNumber = cc.substring(12, 16);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 4; i++)
            {
                stringBuilder.append("**** ");
            }
            //stringBuilder.append(lastCreditCardNumber);

            System.out.println("Result = " + stringBuilder.toString());
        }
    }
}