package com.example.consumer.queues.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentOrder
{
    private final String from;
    private final String to;
    private final Integer amount;

    @JsonCreator
    public PaymentOrder(@JsonProperty("from") String from, @JsonProperty("to") String to, @JsonProperty("amount") Integer amount)
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

    public Integer getAmount()
    {
        return amount;
    }
}