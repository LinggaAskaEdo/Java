package bbw.pulsa.model;

/**
 * Created by Lingga on 12/03/18.
 */

public class Operator
{
    private Integer operatorId;
    private String operatorName;

    public Integer getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getOperatorName()
    {
        return operatorName;
    }

    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }

    @Override
    public String toString()
    {
        return "Operator{" +
                "operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                '}';
    }
}