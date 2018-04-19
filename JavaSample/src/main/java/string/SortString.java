package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortString
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<>();

        list.add("4");
        list.add("3");
        list.add("1");
        list.add("2");

        System.out.println(Arrays.toString(list.toArray()));

        Collections.sort(list);

        System.out.println(Arrays.toString(list.toArray()));
    }
}