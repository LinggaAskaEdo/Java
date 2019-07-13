package collection;

public class Prepaid
{
    private String productName;
    private String productCode;
    private String network;
    private int amount;
    private String type;

    Prepaid(String productName, String productCode, String network, int amount, String type)
    {
        this.productName = productName;
        this.productCode = productCode;
        this.network = network;
        this.amount = amount;
        this.type = type;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductCode()
    {
        return productCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getNetwork()
    {
        return network;
    }

    public void setNetwork(String network)
    {
        this.network = network;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "Prepaid{" +
                "productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", network='" + network + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}