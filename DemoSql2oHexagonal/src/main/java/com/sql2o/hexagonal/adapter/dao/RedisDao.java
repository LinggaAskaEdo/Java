package com.sql2o.hexagonal.adapter.dao;

import com.sql2o.hexagonal.adapter.config.CacheConfiguration;
import com.sql2o.hexagonal.application.model.Employee;
import com.sql2o.hexagonal.application.port.outgoing.EmployeePort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisDao implements EmployeePort
{
    private static final Logger logger = LogManager.getLogger();

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisDao(RedisTemplate<String, Object> redisTemplate)
    {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean saveEmployee(Employee employee)
    {
        boolean status = false;

        try
        {
            String uniqueKey = CacheConfiguration.EMPLOYEE_CACHE + "-" + employee.getEmployeeId();
            redisTemplate.opsForHash().put(uniqueKey, employee.getEmployeeId(), employee);
            redisTemplate.expire(uniqueKey, 30, TimeUnit.SECONDS);
            status = true;
        }
        catch (Exception e)
        {
            logger.error("Error when saveEmployee", e);
        }

        return status;
    }

    @Override
    public Employee getEmployeeById(String id)
    {
        String uniqueKey = CacheConfiguration.EMPLOYEE_CACHE + "-" + id;
        return (Employee) redisTemplate.opsForHash().get(uniqueKey, id);
    }
}