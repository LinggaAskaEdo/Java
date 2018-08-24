package com.hcid.sql2o.service;

import com.hcid.sql2o.dao.HcidDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HcidService
{
    private static final Logger logger = LogManager.getLogger();

    private HcidDao dao;

    @Autowired
    public HcidService(HcidDao dao)
    {
        this.dao = dao;
    }

    @Scheduled(fixedRateString = "450000")
    public void testGetCuid()
    {
        String cuid = dao.getCuidByUserId("8022222212");

        logger.info("sql2o - cuid: {}", cuid);
    }
}