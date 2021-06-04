package com.example.consumer.queues.util;

import com.example.consumer.queues.preference.ConfigPreference;
import com.example.consumer.queues.preference.ConstantPreference;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Component
public class ConsumerUtil
{
    private final ConfigPreference configPreference;
    private final RabbitTemplate rabbitTemplate;
    private final Random random;

    public ConsumerUtil(ConfigPreference configPreference, RabbitTemplate rabbitTemplate) throws NoSuchAlgorithmException
    {
        this.configPreference = configPreference;
        this.rabbitTemplate = rabbitTemplate;
        this.random = SecureRandom.getInstanceStrong();
    }

    public int generateRandomNumber()
    {
        int low = 10;
        int high = 100;

        return random.nextInt(high - low) + low;
    }

    public int countRetry(Message message)
    {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        Integer retryCount = (Integer) headers.get(ConstantPreference.X_RETRIES_HEADER);

        return Objects.isNull(retryCount) ? ConstantPreference.Number.ZERO : retryCount;
    }

    private boolean reWaitingQueue(Message message)
    {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        boolean reWaiting = false;

        if (this.isRetryAvailable(message))
        {
            headers.put(ConstantPreference.X_RETRIES_HEADER, this.countRetry(message) + 1);
            rabbitTemplate.send(configPreference.rabbitExchangeName, configPreference.waitRoutingKey, message);
            reWaiting = true;
        }

        return reWaiting;
    }

    public boolean isRetryAvailable(Message message)
    {
        return this.countRetry(message) < configPreference.maxRetry;
    }

    public void reRetryQueue(Message message)
    {
        if (!this.reWaitingQueue(message))
        {
            rabbitTemplate.send(configPreference.rabbitExchangeName, configPreference.parkRoutingKey, message);
        }
    }
}