package com.bos.service;

import com.bos.model.ResponseCheck;
import com.bos.model.ResponseGet;
import com.bos.model.ResponseSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AWHService
{
    private final Logger log = LoggerFactory.getLogger(AWHService.class);

    @Autowired
    private Environment env;

    public List<ResponseGet> getMessage(String apiKey)
    {
        List<ResponseGet> responseGetList = new ArrayList<>();

        try
        {
            log.info("Starting get new messages");

            RestTemplate restTemplate = new RestTemplate();

            String encodeFormat = env.getProperty("encode.format");
            String type = env.getProperty("apiwha.api.type");
            String markaspulled = env.getProperty("apiwha.mark.as.pulled");
            String getnotpulledonly = env.getProperty("apiwha.get.not.pulled.only");
            String urlGetMessage = env.getProperty("apiwha.url.get.message");

            String getMessageUrl = urlGetMessage + URLEncoder.encode(apiKey, encodeFormat)
                    + "&type=" + URLEncoder.encode(type, encodeFormat)
                    + "&markaspulled=" + URLEncoder.encode(markaspulled, encodeFormat)
                    + "&getnotpulledonly=" + URLEncoder.encode(getnotpulledonly, encodeFormat);

            log.debug("getMessageUrl: {}", getMessageUrl);

            ResponseGet[] responseGets = restTemplate.getForObject(getMessageUrl, ResponseGet[].class);

            log.debug("responseGets: {}", Arrays.toString(responseGets));

            responseGetList = Arrays.asList(responseGets);
        }
        catch (Exception e)
        {
            log.error("Error when getMessage: {}", e);
        }

        return responseGetList;
    }

    public void sendMessage(String apiKey, String number, String text)
    {
        try
        {
            RestTemplate restTemplate = new RestTemplate();

            String encodeFormat = env.getProperty("encode.format");
            String urlSendMessage = env.getProperty("apiwha.url.send.message");
            String sendMessageUrl = urlSendMessage + URLEncoder.encode(apiKey, encodeFormat)
                    + "&number=" + URLEncoder.encode(number, encodeFormat)
                    + "&text=" + text;

            log.debug("sendMessageUrl: {}", sendMessageUrl);

            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);

            ResponseSend responseSend = restTemplate.getForObject(sendMessageUrl, ResponseSend.class);

            log.debug("sending message to {}: {}", number, (responseSend.getSuccess().equalsIgnoreCase("true")) ? "success" : "fail");
        }
        catch (Exception e)
        {
            log.error("Error when sendMessage: {}", e);
        }
    }

    public void checkCredit()
    {
        try
        {
            log.info("Starting get check credit");

            RestTemplate restTemplate = new RestTemplate();

            String encodeFormat = env.getProperty("encode.format");
            String apiKey = env.getProperty("apiwha.api.key");
            String urlCheckCredit = env.getProperty("apiwha.url.check.credit");

            String checkCreditUrl = urlCheckCredit + URLEncoder.encode(apiKey, encodeFormat);

            log.debug("checkCreditUrl: {}", checkCreditUrl);

            ResponseCheck responseCheck = restTemplate.getForObject(checkCreditUrl, ResponseCheck.class);

            log.debug("Credit: {}", responseCheck.getCredit());
        }
        catch (Exception e)
        {
            log.error("Error when checkCredit: {}", e);
        }
    }
}