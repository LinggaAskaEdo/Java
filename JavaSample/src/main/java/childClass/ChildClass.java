package childClass;

import java.util.ArrayList;
import java.util.List;

public class ChildClass
{
    public static class Sort
    {
        protected List<ChildClass.Sort.Order> order;

        public Sort() {
        }

        public void setOrder(List<Order> order) {
            this.order = order;
        }

        public List<Order> getOrder() {
            return order;
        }

        public static class Order
        {
            protected String direction;
            protected String property;

            public Order() {
            }

            public String getDirection() {
                return this.direction;
            }

            public void setDirection(String value) {
                this.direction = value;
            }

            public String getProperty() {
                return this.property;
            }

            public void setProperty(String value) {
                this.property = value;
            }
        }
    }
}