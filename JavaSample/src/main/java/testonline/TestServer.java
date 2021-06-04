package testonline;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestServer
{
    public static void main(String[] args)
    {
        int a;
        int b;
        int c;

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt())
        {
            a = scanner.nextInt();
            b = scanner.nextInt();

            System.out.println(a + ", " + b);

            List<String> tokens = new ArrayList<>();
            Scanner lineScanner = new Scanner(scanner.nextLine());

            while (lineScanner.hasNext())
            {
                tokens.add(lineScanner.next());
            }

            lineScanner.close();
            System.out.println(tokens);
        }

        scanner.close();

//        Scanner scanner2 = new Scanner(System.in);
//
//        while (scanner.hasNextInt())
//        {
//            c = scanner.nextInt();
//
//            System.out.println(c);
//        }
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (scanner.hasNextLine())
//        {
//            List<String> tokens = new ArrayList<>();
//            Scanner lineScanner = new Scanner(scanner.nextLine());
//
//            while (lineScanner.hasNext()) {
//                tokens.add(lineScanner.next());
//            }
//
//            lineScanner.close();
//            System.out.println(tokens);
//        }
//
//        scanner.close();
    }
}