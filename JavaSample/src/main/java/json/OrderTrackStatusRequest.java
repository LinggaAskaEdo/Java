package json;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderTrackStatusRequest
{
    private PaymentType paymentType;
    private String transactionId;
    private String orderId;
    private String invoiceId;
//    @JsonFormat(
//            shape = JsonFormat.Shape.STRING,
//            pattern = "yyyy-MM-dd HH:mm:ss",
//            timezone = "Asia/Jakarta"
//    )
    private Date createdAt;
    private String informationCommodity;
    private String airWillBillNumber;
    private String expeditionName;
    private String receiverName;
    private String receiverRelation;
    private String deliveryAddress;

    public PaymentType getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType)
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

    public String getInvoiceId()
    {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId)
    {
        this.invoiceId = invoiceId;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
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

    @Override
    public String toString()
    {
        return "OrderTrackStatusRequest{" +
                "paymentType=" + paymentType +
                ", transactionId='" + transactionId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", createdAt=" + createdAt +
                ", informationCommodity='" + informationCommodity + '\'' +
                ", airWillBillNumber='" + airWillBillNumber + '\'' +
                ", expeditionName='" + expeditionName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverRelation='" + receiverRelation + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }
}