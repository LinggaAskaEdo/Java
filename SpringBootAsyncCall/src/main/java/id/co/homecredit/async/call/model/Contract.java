package id.co.homecredit.async.call.model;

public class Contract
{
    private String sourceType;
    private String type;
    private String contractNumber;
    private String commodityName;

    public String getSourceType()
    {
        return sourceType;
    }

    public void setSourceType(String sourceType)
    {
        this.sourceType = sourceType;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getContractNumber()
    {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber)
    {
        this.contractNumber = contractNumber;
    }

    public String getCommodityName()
    {
        return commodityName;
    }

    public void setCommodityName(String commodityName)
    {
        this.commodityName = commodityName;
    }
}
