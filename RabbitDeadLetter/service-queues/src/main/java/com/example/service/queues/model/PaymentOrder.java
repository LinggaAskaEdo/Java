package com.example.service.queues.model;

import com.example.service.queues.exception.BadRequestException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

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

    public void validate()
    {
        validateFrom();
        validateTo();
        validateAmount();
    }

    private void validateFrom()
    {
        if (StringUtils.isBlank(from))
        {
            throw new BadRequestException("Object from is mandatory");
        }
    }

    private void validateTo()
    {
        if (StringUtils.isBlank(to))
        {
            throw new BadRequestException("Object to is mandatory");
        }
    }

    private void validateAmount()
    {
        if (StringUtils.isBlank(to))
        {
            throw new BadRequestException("Object amount is mandatory");
        }

        if (amount <= 0)
        {
            throw new BadRequestException("Amount should greater than zero");
        }
    }
}