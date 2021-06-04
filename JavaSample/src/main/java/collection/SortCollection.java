package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortCollection
{
    public static void main(String[] args)
    {
        List<Double> testList = new ArrayList();

        testList.add(0.5);
        testList.add(0.2);
        testList.add(0.9);
        testList.add(0.1);
        testList.add(0.1);
        testList.add(0.1);
        testList.add(0.54);
        testList.add(0.71);
        testList.add(0.71);
        testList.add(0.71);
        testList.add(0.92);
        testList.add(0.12);
        testList.add(0.65);
        testList.add(0.34);
        testList.add(0.62);

        Collections.sort(testList);
        System.out.println(testList);

        Collections.reverse(testList);
        System.out.println(testList);
    }
}