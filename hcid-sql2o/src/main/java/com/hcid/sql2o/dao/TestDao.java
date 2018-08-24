package com.hcid.sql2o.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao
{
    private static final Logger logger = LogManager.getLogger();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TestDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getCuidByUserId(String userId)
    {
        String result = null;
        String sql = "SELECT cuid FROM mob_user WHERE user_id = ?";

        try
        {
            result = jdbcTemplate.queryForObject(sql, new Object[] { userId }, String.class);
        }
        catch (Exception e)
        {
            logger.error("Error when getCuidByUserId: {}", e);
        }

        return result;
    }
}