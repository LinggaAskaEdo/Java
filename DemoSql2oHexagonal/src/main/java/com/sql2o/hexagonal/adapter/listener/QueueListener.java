package com.sql2o.hexagonal.adapter.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sql2o.hexagonal.adapter.dao.StudentDao;
import com.sql2o.hexagonal.application.model.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QueueListener
{
    private static final Logger logger = LogManager.getLogger();

    private static final String REQUEST_ID = "requestId";

    private final StudentDao studentDao;

    @Autowired
    public QueueListener(StudentDao studentDao)
    {
        this.studentDao = studentDao;
    }

    @RabbitListener(queues = "${admin.queue}")
    public void receivedMessageAdmin(Document data)
    {
        ObjectMapper mapper = new ObjectMapper();
        MDC.put(REQUEST_ID, "admin-" + RandomStringUtils.randomNumeric(5));

        try
        {
            logger.debug("Received Message From AdminQueue: {}", mapper.writeValueAsString(data));
            logger.debug(data.getContractNumber());

            Student student = studentDao.findByNumber(data.getContractNumber());

            logger.debug("Result student: {}", mapper.writeValueAsString(student));
        }
        catch (Exception e)
        {
            logger.error("Error when receivedMessageAdmin: ", e);
        }
    }

    @RabbitListener(queues = "${marketing.queue}")
    public void receivedMessageMarketing(Document data)
    {
        ObjectMapper mapper = new ObjectMapper();
        MDC.put(REQUEST_ID, "marketing-" + RandomStringUtils.randomNumeric(5));

        try
        {
            logger.debug("Received Message From MarketingQueue: {}", mapper.writeValueAsString(data));
        }
        catch (Exception e)
        {
            logger.error("Error when receivedMessageMarketing: ", e);
        }
    }

    @RabbitListener(queues = "${car.queue}", concurrency = "3")
    public Registration receivedMessageCar(Car car)
    {
        ObjectMapper mapper = new ObjectMapper();
        MDC.put(REQUEST_ID, "car-" + RandomStringUtils.randomNumeric(5));

        try
        {
            logger.debug("Received Message From CarQueue: {}", mapper.writeValueAsString(car));
            logger.debug("id: {}", car.getId());
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            logger.error("Error when receivedMessageCar: ", e);
        }

        return new Registration(car.getId(), new Date(), "Ms. Rabbit", "Signature of the registration");
    }

    @RabbitListener(queues = "${test.queue}", concurrency = "3")
    public void receivedMessageTest(Test test)
    {
        ObjectMapper mapper = new ObjectMapper();
        MDC.put(REQUEST_ID, "test-" + RandomStringUtils.randomNumeric(5));

        try
        {
            logger.debug("Received Message From TestQueue: {}", mapper.writeValueAsString(test));
            logger.debug("id: {}", test.getId());
            logger.debug("name: {}", test.getName());
        }
        catch (Exception e)
        {
            logger.error("Error when receivedMessageTest: ", e);
        }
    }
}