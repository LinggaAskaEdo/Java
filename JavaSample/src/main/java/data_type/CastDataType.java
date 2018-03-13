package data_type;

/**
 * Created by Lingga on 13/02/17.
 */
public class CastDataType
{
    public static void main(String[] args)
    {
        String data1 = "13";
        System.out.println("Result: " + castDataType("Int", data1));
        System.out.println("Data type: " + castDataType("Int", data1).getClass().getName());

        String data2 = "false";
        System.out.println("\nResult: " + castDataType("Boolean", data2));
        System.out.println("Data type: " + castDataType("Boolean", data2).getClass().getName());

        String data3 = "10.458";
        System.out.println("\nResult: " + castDataType("String", data3));
        System.out.println("Data type: " + castDataType("String", data3).getClass().getName());

        String data4 = "10.458";
        System.out.println("\nResult: " + castDataType("Float", data4));
        System.out.println("Data type: " + castDataType("Float", data4).getClass().getName());
    }

    private static Object castDataType(String strType, String strValue)
    {
        Object result = null;

        if (strType.equalsIgnoreCase("int") || strType.equalsIgnoreCase("integer"))
        {
            result = Integer.parseInt(strValue);
        }
        else if (strType.equalsIgnoreCase("boolean"))
        {
            result = strValue.equalsIgnoreCase("true");
        }
        else if (strType.equalsIgnoreCase("float"))
        {
            result = Float.parseFloat(strValue);
        }
        else if (strType.equalsIgnoreCase("string"))
        {
            result = strValue;
        }

        return result;
    }
}
