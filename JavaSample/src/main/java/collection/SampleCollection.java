package collection;

/**
 * Created by Lingga on 13/03/17.
 */

public class SampleCollection
{
    private String name;
    private String address;

    SampleCollection(String name, String address)
    {
        this.name = name;
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "SampleCollection{" + "name='" + name + '\'' + ", address='" + address + '\'' + '}';
    }
}