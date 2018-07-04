/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.bos.scheduler;

import com.bos.controller.BosController;
import com.bos.dao.BOSDAO;
import com.bos.model.Request;
import com.bos.model.ResponseGet;
import com.bos.model.User;
import com.bos.service.AWHService;
import com.bos.service.BOSService;
import com.bos.service.SCPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BOSScheduler
{
    private final Logger log = LoggerFactory.getLogger(BOSScheduler.class);

    @Autowired
    private Environment env;

    @Autowired
    private BosController controller;

    @Autowired
    private BOSDAO dao;

    @Autowired
    private AWHService awhService;

    @Autowired
    private SCPService scpService;

    @Autowired
    private BOSService bosService;

    @Scheduled(fixedRateString = "${scheduler.time.ms.get.message}")
    public void getNewMessage()
    {
        try
        {
            String apiKey = env.getProperty("apiwha.api.key");

            //Get Message
            List<ResponseGet> responseGetList = awhService.getMessage(apiKey);

            if (responseGetList.size() > 0)
            {
                for (ResponseGet responseGet : responseGetList)
                {
                    String ownNumber = responseGet.getNumber();
                    String number = responseGet.getFrom();
                    String messsage = responseGet.getText();
                    String text = controller.requestHandler(new Request(number, apiKey, messsage));

                    log.debug("message: {}", text);

                    //Send Message
                    awhService.sendMessage(apiKey, number, text);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error when getNewMessage: {}", e);
        }
    }

    @Scheduled(fixedRateString = "${scheduler.time.ms.check.credit}")
    public void checkCredit()
    {
        awhService.checkCredit();
    }

    @Scheduled(cron = "${scheduler.time.update.origin.destination}")
    public void updateOriginDestination()
    {
        //Origin
        scpService.updateOrigin();

        //Destination
        scpService.updateDestination();
    }
}