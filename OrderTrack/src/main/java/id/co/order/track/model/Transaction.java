package id.co.order.track.model;

public class Transaction
{
    //Success
    private String orderId;
    private String transactionId;
    private String responseDescription;

    //Error
    private Integer code;
    private String message;
    private String detailMessage;
    private String severity;
    private Boolean error;

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }

    public String getResponseDescription()
    {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription)
    {
        this.responseDescription = responseDescription;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getDetailMessage()
    {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
    }

    public String getSeverity()
    {
        return severity;
    }

    public void setSeverity(String severity)
    {
        this.severity = severity;
    }

    public Boolean getError()
    {
        return error;
    }

    public void setError(Boolean error)
    {
        this.error = error;
    }
}