package calculator;

import java.util.Scanner;

/**
 * Created by Lingga on 16/02/17.
 */

class MainCalculator
{
    private Integer resultInteger;
    private Long resultLong;
    private Double resultDouble;
    private String result;

    int add(int a, int b)
    {
        return a + b;
    }

    String add(String a, String b)
    {
        clearCache();

        Object objA = interpret(a);
        Object objB = interpret(b);

        if (isString(objA, objB))
        {
            return "";
        }
        else
        {
            parseString(objA);
            parseString(objB);

            result = countResultAdd();
            //System.out.println("result: " + result);
        }

        return result;
    }

    int subtract(int a, int b)
    {
        return a - b;
    }

    int multiply(int a, int b)
    {
        return a * b;
    }

    int divide(int a, int b)
    {
        return a / b;
    }

    private Object interpret(String str)
    {
        String trimStr = str.trim();
        //System.out.println("trimStr: " + trimStr);

        Scanner sc = new Scanner(trimStr);
        return sc.hasNextInt() ? sc.nextInt() : sc.hasNextLong() ? sc.nextLong() : sc.hasNextDouble() ? sc.nextDouble() : sc.hasNext() ? sc.next() : trimStr;
    }

    private boolean isString(Object typeA, Object typeB)
    {
        return "String".equalsIgnoreCase(typeA.getClass().getSimpleName()) || "String".equalsIgnoreCase(typeB.getClass().getSimpleName());
    }

    private void parseString(Object objValue)
    {
        String dataType = objValue.getClass().getSimpleName();
        //System.out.println("dataType: " + dataType);

        switch (dataType)
        {
            case "Integer" :
                if (resultInteger == null)
                    resultInteger = Integer.valueOf(String.valueOf(objValue));
                else
                    resultInteger = resultInteger + Integer.valueOf(String.valueOf(objValue));
                break;
            case "Long" :
                if (resultLong == null)
                    resultLong = Long.valueOf(String.valueOf(objValue));
                else
                    resultLong = resultLong + Long.valueOf(String.valueOf(objValue));
                break;
            case "Double" :
                if (resultLong == null)
                    resultDouble = Double.valueOf(String.valueOf(objValue));
                else
                    resultDouble = resultDouble + Double.valueOf(String.valueOf(objValue));
                break;
            default :
                break;
        }
    }

    private String countResultAdd()
    {
        //System.out.println("resultInteger: " + resultInteger);
        //System.out.println("resultLong: " + resultLong);
        //System.out.println("resultDouble: " + resultDouble);

        if (resultDouble == null)
            result = String.valueOf(((resultInteger == null) ? 0 : resultInteger) + ((resultLong == null) ? 0 : resultLong));
        else
            result = String.valueOf(((resultInteger == null) ? 0 : resultInteger) + ((resultLong == null) ? 0 : resultLong) + resultDouble);

        return result;
    }

    private void clearCache()
    {
        resultInteger = null;
        resultLong = null;
        resultDouble = null;
        result = "";
    }
}