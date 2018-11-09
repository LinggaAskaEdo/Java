package com.hcid.sql2o.service;

import com.hcid.sql2o.dao.TestDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class TestService
{
    private static final Logger logger = LogManager.getLogger();

    private TestDao dao;

    @Autowired
    public TestService(TestDao dao)
    {
        this.dao = dao;
    }

//    @Scheduled(fixedRateString = "30000")
    public void testGetCuid()
    {
        String cuid = dao.getCuidByUserId("8022222212");

        logger.info("jdbctemplate - cuid: {}", cuid);
    }
}