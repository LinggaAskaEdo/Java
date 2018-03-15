package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Lingga on 13/03/17.
 */

public class FilterCollection
{
    public static void main(String[] args)
    {
        SampleCollection collection1 = new SampleCollection("Reno", "Jakarta");
        SampleCollection collection2 = new SampleCollection("Aska", "Bandung");

        List<SampleCollection> sampleCollectionList = new ArrayList<>();
        sampleCollectionList.add(collection1);
        sampleCollectionList.add(collection2);

        List<String> stringList = new ArrayList<>();
        stringList.add("Aska");
        stringList.add("Tejo");
        stringList.add("Tony");

        for (Iterator<SampleCollection> iter = sampleCollectionList.listIterator(); iter.hasNext(); )
        {
            if (!stringList.contains(iter.next().getName().trim()))
            {
                //System.out.println(iter.next().getName().trim());
                iter.remove();
            }
        }

        for (SampleCollection val : sampleCollectionList)
        {
            System.out.println("finalList: " + val);
        }
    }
}