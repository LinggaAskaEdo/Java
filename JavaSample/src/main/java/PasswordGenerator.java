import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by edo on 29/11/16.
 */

public class PasswordGenerator
{
    public static void main(String[] args)
    {
        int a = '0';
        int b = 'a';
        int c = 'A';
        System.out.println("a: " + a + ", b:" + b + ", c:" + c);

        String generatedPassword = getPassword(8);
        System.out.println(generatedPassword);
    }

    private static String getPassword(int n)
    {
        n = passwordLengthValidator(n);
        char[] pw = new char[n];
        int c = 'A';
        int r1;

        for (int i = 0; i < n; i++)
        {
            r1 = (int) (Math.random() * 4);
            System.out.println("r1: " + r1);

            switch (r1)
            {
                case 0:
                    System.out.println("case 0");
                    c = '0' + (int) (Math.random() * 10);
                    System.out.println("c0: " + c);
                    break;
                case 1:
                    System.out.println("case 1");
                    c = 'a' + (int) (Math.random() * 26);
                    System.out.println("c1: " + c);
                    break;
                case 2:
                    System.out.println("case 2");
                    c = 'A' + (int) (Math.random() * 26);
                    System.out.println("c2: " + c);
                    break;
                case 3:
                    System.out.println("case 3");
                    c = getRandomChar();
                    System.out.println("c3: " + c);
                    break;
            }
            pw[i] = (char) c;
        }

        return new String(passwordCriteria(pw));
    }

    private static char[] passwordCriteria(char[] password)
    {
        int min = 1;
        int max = password.length - 2;
        int randomNumber;

        char[] criteriasString = {' ', 'A', 'a', '1'};

        List<Integer> randomNumberPicked = new ArrayList<>();

        for(int i = 0; i < criteriasString.length; i++)
        {
            randomNumber = min + (int)(Math.random() * ((max - min) + 1));
            while(randomNumberPicked.contains(randomNumber))
            {
                randomNumber = min + (int)(Math.random() * ((max - min) + 1));
            }
            randomNumberPicked.add(randomNumber);
            password[randomNumber] = criteriaFiller(i);
        }

        return password;
    }

    private static char criteriaFiller(int i)
    {
        int c = 'A';
        switch(i)
        {
            case 0:
                c = getRandomChar();
                break;
            case 1:
                c = 'a' + (int)(Math.random() * 26);
                break;
            case 2:
                c = 'A' + (int)(Math.random() * 26);
                break;
            case 3:
                c = '0' + (int)(Math.random() * 10);
                break;
        }

        return (char)c;
    }

    private static int getRandomChar()
    {
        Random r = new SecureRandom();
        String letters = "!@%&()-.<>?";
        int index = (int)(r.nextDouble()*letters.length());

        return letters.substring(index, index+1).toCharArray()[0];
    }

    private static int passwordLengthValidator(int passwordLength)
    {
        if(passwordLength < 6)
        {
            passwordLength = 6;
        }

        if(passwordLength > 20)
        {
            passwordLength = 20;
        }

        return passwordLength;
    }
}