package com.example.consumer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class PaymentOrder
{
    private final String from;
    private final String to;
    private final BigDecimal amount;

    @JsonCreator
    public PaymentOrder(@JsonProperty("from") String from, @JsonProperty("to") String to, @JsonProperty("amount") BigDecimal amount)
    {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public String getFrom()
    {
        return from;
    }

    public String getTo()
    {
        return to;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    @Override
    public String toString()
    {
        return "PaymentOrder{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                '}';
    }
}