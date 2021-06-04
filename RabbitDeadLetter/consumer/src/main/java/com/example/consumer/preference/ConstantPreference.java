package com.example.consumer.preference;

public class ConstantPreference
{
    private ConstantPreference()
    {
        //Do nothing
    }

    public static final String DEAD_LETTER_QUEUE_NAME = "payment-orders.dead-letter.queue";
    public static final String INCOMING_QUEUE_NAME = "payment-orders.incoming.queue";
}