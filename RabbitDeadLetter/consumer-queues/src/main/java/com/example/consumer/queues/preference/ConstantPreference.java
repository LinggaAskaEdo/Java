package com.example.consumer.queues.preference;

public class ConstantPreference
{
    private ConstantPreference()
    {
        //Do nothing
    }

    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    public static final String X_MESSAGE_TTL = "x-message-ttl";
    public static final String X_RETRIES_HEADER = "x-retries";

    public static class Number
    {
        private Number()
        {}

        public static final int ZERO = 0;
    }
}
