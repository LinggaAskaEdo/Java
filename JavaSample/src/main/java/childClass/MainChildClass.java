package childClass;

import java.util.ArrayList;
import java.util.List;

public class MainChildClass
{
    public static void main(String[] args)
    {
        List<ChildClass.Sort.Order> orders = new ArrayList<>();
        ChildClass.Sort.Order order = new ChildClass.Sort.Order();
        order.setDirection("AAA");
        order.setProperty("BBB");

        orders.add(order);

        ChildClass.Sort sort = new ChildClass.Sort();
        sort.setOrder(orders);
        System.out.println(sort.getOrder().get(0).getDirection() + " " + sort.getOrder().get(0).getProperty());


    }
}