package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterCollection2
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            stressTestFilter();
        }
    }

    private static void stressTestFilter()
    {
        Prepaid p1 = new Prepaid("Axis 5RB", "SUB-SP-AX5H", "AXIS", 5000, "airtime");
        Prepaid p2 = new Prepaid("Axis 10RB", "SUB-SP-AX10H", "AXIS", 10900, "airtime");
        Prepaid p3 = new Prepaid("BRONET 300MB", "SUB-AXISBRONET300MB", "AXIS", 12000, "data");
        Prepaid p4 = new Prepaid("30 Shell", "SUB-PODUPGC001", "GARENA", 10000, "game");
        Prepaid p5 = new Prepaid("PLN 200", "SUB-PLN200", "PLN", 200000, "electricity");

        List<Prepaid> prepaids = new ArrayList<>();
        prepaids.add(p1);
        prepaids.add(p2);
        prepaids.add(p3);
        prepaids.add(p4);
        prepaids.add(p5);

        List<Prepaid> airtime = prepaids.stream().filter(pr -> pr.getType().contains("air")).collect(Collectors.toList());
        System.out.println(airtime.toString());

        List<Prepaid> data = prepaids.stream().filter(pr -> pr.getType().equalsIgnoreCase("data")).collect(Collectors.toList());
        System.out.println(data.toString());

        List<Prepaid> game = prepaids.stream().filter(pr -> pr.getType().equalsIgnoreCase("game")).collect(Collectors.toList());
        System.out.println(game.toString());

        List<Prepaid> electricity = prepaids.stream().filter(pr -> pr.getType().equalsIgnoreCase("electricity")).collect(Collectors.toList());
        System.out.println(electricity.toString());

        System.out.println("==============================================================================================================================================");
    }
}