package test.hackerrank;

import java.util.*;

import static java.lang.System.*;

public class ActiveTraders
{
    public static void main(String[] args)
    {
        List<String> customers = new ArrayList<>();
        customers.add("Bigcorp");
        customers.add("Bigcorp");
        customers.add("Acme");
        customers.add("Bigcorp");
        customers.add("Zork");
        customers.add("Zork");
        customers.add("Abc");
        customers.add("Bigcorp");
        customers.add("Acme");
        customers.add("Bigcorp");
        customers.add("Bigcorp");
        customers.add("Zork");
        customers.add("Bigcorp");
        customers.add("Zork");
        customers.add("Zork");
        customers.add("Bigcorp");
        customers.add("Acme");
        customers.add("Bigcorp");
        customers.add("Acme");
        customers.add("Bigcorp");
        customers.add("Acme");
        customers.add("Littlecorp");
        customers.add("Nadircorp");

        int size = customers.size();
        out.println("size: " + size);

        HashMap<String, Double> freqMap = new HashMap<>();

        for (String customer : customers)
        {
            if (freqMap.containsKey(customer))
            {
                freqMap.put(customer, freqMap.get(customer) + 1.0);
            }
            else
            {
                freqMap.put(customer, 1.0);
            }
        }

        out.println(freqMap);

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Double> entry : freqMap.entrySet())
        {
            out.println(entry.getKey() + ", " + entry.getValue());
            double count = entry.getValue() / size * 100;
            out.println(count);

            if (count > 5)
            {
                result.add(entry.getKey());
            }
        }

        Collections.sort(result);

        out.println(result);
    }
}
