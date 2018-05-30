/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.scheduler;

import com.back.olshop.controller.BosController;
import com.back.olshop.model.Request;
import com.back.olshop.model.ResponseCheck;
import com.back.olshop.model.ResponseGet;
import com.back.olshop.model.ResponseSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class BOSScheduler
{
    private final Logger log = LoggerFactory.getLogger(BOSScheduler.class);

    @Autowired
    private Environment env;

    @Autowired
    private BosController controller;

    @Scheduled(fixedRateString = "${scheduler.time.ms.get.message}")
    public void getNewMessage()
    {
        try
        {
            log.info("Starting get new messages");

            RestTemplate restTemplate = new RestTemplate();

            String encodeFormat = env.getProperty("encode.format");
            String apiKey = env.getProperty("api.key");
            String type = env.getProperty("api.type");
            String markaspulled = env.getProperty("mark.as.pulled");
            String getnotpulledonly = env.getProperty("get.not.pulled.only");
            String urlGetMessage = env.getProperty("url.get.message");

            String getMessageUrl = urlGetMessage + URLEncoder.encode(apiKey, encodeFormat)
                    + "&type=" + URLEncoder.encode(type, encodeFormat)
                    + "&markaspulled=" + URLEncoder.encode(markaspulled, encodeFormat)
                    + "&getnotpulledonly=" + URLEncoder.encode(getnotpulledonly, encodeFormat);

            ResponseGet[] responseGets = restTemplate.getForObject(getMessageUrl, ResponseGet[].class);

            log.debug("responseGets: {}", Arrays.toString(responseGets));

            List<ResponseGet> responseGetList = Arrays.asList(responseGets);

            if (responseGetList.size() > 0)
            {
                for (ResponseGet responseGet : responseGetList)
                {
                    String ownNumber = responseGet.getNumber();
                    String number = responseGet.getFrom();
                    String messsage = responseGet.getText();
                    String text = controller.requestHandler(new Request(ownNumber, apiKey, messsage));

                    log.debug("message: {}", text);

                    String urlSendMessage = env.getProperty("url.send.message");
                    String sendMessageUrl = urlSendMessage + URLEncoder.encode(apiKey, encodeFormat)
                            + "&number=" + URLEncoder.encode(number, encodeFormat)
                            + "&text=" + text;

                    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
                    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                    converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
                    messageConverters.add(converter);
                    restTemplate.setMessageConverters(messageConverters);

                    ResponseSend responseSend = restTemplate.getForObject(sendMessageUrl, ResponseSend.class);

                    log.debug("sending message to {}: {}", number, (responseSend.getSuccess().equalsIgnoreCase("true")) ? "success" : "fail");
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
        try
        {
            log.info("Starting get check credit");

            RestTemplate restTemplate = new RestTemplate();

            String encodeFormat = env.getProperty("encode.format");
            String apiKey = env.getProperty("api.key");
            String urlCheckCredit = env.getProperty("url.check.credit");

            String checkCreditUrl = urlCheckCredit + URLEncoder.encode(apiKey, encodeFormat);

            ResponseCheck responseCheck = restTemplate.getForObject(checkCreditUrl, ResponseCheck.class);

            log.debug("responseCheck: {}", responseCheck.getCredit());
        }
        catch (Exception e)
        {
            log.error("Error when checkCredit: {}", e);
        }
    }
}