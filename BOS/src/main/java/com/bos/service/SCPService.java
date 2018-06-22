package com.bos.service;

import com.bos.dao.BOSDAO;
import com.bos.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

@Service
public class SCPService
{
    private final Logger log = LoggerFactory.getLogger(SCPService.class);

    @Autowired
    private Environment env;

    @Autowired
    private BOSDAO dao;

    Response getTarif(String clientDistricts, String clientCity)
    {
        Response response = new Response();

        try
        {
            String originCode = dao.getOriginCode(env.getProperty("origin.city"));
            String destinationCode = dao.getDestinationCode(clientDistricts, clientCity);

            log.debug("originCode: {}, destinationCode: {}", originCode, destinationCode);

            if (originCode != null && destinationCode != null)
            {
                RestTemplate restTemplate = new RestTemplate();

                String encodeFormat = env.getProperty("encode.format");
                String apiKey = env.getProperty("sicepat.api.key");
                String urlGetTarif = env.getProperty("sicepat.url.get.tarif");

                String getTarifUrl = urlGetTarif + URLEncoder.encode(apiKey, encodeFormat)
                        + "&origin=" + originCode
                        + "&destination=" + destinationCode
                        + "&weight=" + (double) 1;

                log.debug("getOriginUrl: {}", getTarifUrl);

                response = restTemplate.getForObject(getTarifUrl, Response.class);

                if (response.getSicepat() != null && response.getSicepat().getStatus().getCode() == 200)
                {
                    //update tarif from API to Database
                    if (dao.updateTarif(response.getSicepat().getResults(), destinationCode))
                    {
                        log.debug("Update tarif success");
                    }
                    else
                    {
                        log.debug("Update tarif fail");
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error when getTarif: {}", e);
        }

        return response;
    }

    public void updateOrigin()
    {
        try
        {
            log.info("Start checking for update Origin");

            RestTemplate restTemplate = new RestTemplate();

            String encodeFormat = env.getProperty("encode.format");
            String apiKey = env.getProperty("sicepat.api.key");
            String urlGetOrigin = env.getProperty("sicepat.url.get.origin");
            String getOriginUrl = urlGetOrigin + URLEncoder.encode(apiKey, encodeFormat);

            log.debug("getOriginUrl: {}", getOriginUrl);

            Response responseOrigin = restTemplate.getForObject(getOriginUrl, Response.class);

            log.debug("responseOrigin: {}", responseOrigin.toString());

            if (responseOrigin.getSicepat() != null && responseOrigin.getSicepat().getStatus().getCode() == 200)
            {
                if (dao.updateOriginData(responseOrigin.getSicepat().getResults()))
                {
                    log.debug("Update Origin's table success");
                }
                else
                {
                    log.debug("Update Origin's table fail");
                }
            }
            else
            {
                log.debug("Don't get response from Origin API");
            }
        }
        catch (Exception e)
        {
            log.error("Error when updateOrigin: {}", e);
        }
    }

    public void updateDestination()
    {
        try
        {
            log.info("Start checking for update Destination");

            RestTemplate restTemplate = new RestTemplate();

            String encodeFormat = env.getProperty("encode.format");
            String apiKey = env.getProperty("sicepat.api.key");
            String urlGetDestination = env.getProperty("sicepat.url.get.destination");
            String getDestinationUrl = urlGetDestination + URLEncoder.encode(apiKey, encodeFormat);

            log.debug("getDestinationUrl: {}", getDestinationUrl);

            Response responseDestination = restTemplate.getForObject(getDestinationUrl, Response.class);

            log.debug("responseDestination: {}", responseDestination.toString());

            if (responseDestination.getSicepat() != null && responseDestination.getSicepat().getStatus().getCode() == 200)
            {
                if (dao.updateDestinationData(responseDestination.getSicepat().getResults()))
                {
                    log.debug("Update Destination's table success");
                }
                else
                {
                    log.debug("Update Destination's table fail");
                }
            }
            else
            {
                log.debug("Don't get response from Destination API");
            }
        }
        catch (Exception e)
        {
            log.error("Error when updateDestination: {}", e);
        }
    }
}