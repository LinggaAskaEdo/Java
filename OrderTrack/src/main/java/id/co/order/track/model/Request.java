package id.co.order.track.model;

public class Request
{
    private String paymentType;
    private String transactionId;
    private String orderId;
    private String createdAt;
    private String informationCommodity;
    private String airWillBillNumber;
    private String expeditionName;
    private String receiverName;
    private String receiverRelation;
    private String deliveryAddress;
    private String statusType;
    private String status;

    public String getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType(String paymentType)
    {
        this.paymentType = paymentType;
    }

    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getInformationCommodity()
    {
        return informationCommodity;
    }

    public void setInformationCommodity(String informationCommodity)
    {
        this.informationCommodity = informationCommodity;
    }

    public String getAirWillBillNumber()
    {
        return airWillBillNumber;
    }

    public void setAirWillBillNumber(String airWillBillNumber)
    {
        this.airWillBillNumber = airWillBillNumber;
    }

    public String getExpeditionName()
    {
        return expeditionName;
    }

    public void setExpeditionName(String expeditionName)
    {
        this.expeditionName = expeditionName;
    }

    public String getReceiverName()
    {
        return receiverName;
    }

    public void setReceiverName(String receiverName)
    {
        this.receiverName = receiverName;
    }

    public String getReceiverRelation()
    {
        return receiverRelation;
    }

    public void setReceiverRelation(String receiverRelation)
    {
        this.receiverRelation = receiverRelation;
    }

    public String getDeliveryAddress()
    {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatusType()
    {
        return statusType;
    }

    public void setStatusType(String statusType)
    {
        this.statusType = statusType;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}