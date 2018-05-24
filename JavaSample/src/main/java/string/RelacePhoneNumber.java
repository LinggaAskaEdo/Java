package string;

public class RelacePhoneNumber
{
    public static void main(String[] args)
    {
        String test = "+62 857-1502-5257";
        test = test.replace("-","").replaceAll(" ","").replaceAll("\\+","");
        System.out.println(test);
    }
}