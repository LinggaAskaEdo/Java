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
                String urlGetOrigin = env.getProperty("sicepat.url.get.tarif");

                String getOriginUrl = urlGetOrigin + URLEncoder.encode(apiKey, encodeFormat)
                        + "&origin=" + originCode
                        + "&destination=" + destinationCode
                        + "&weight=" + (double) 1;

                log.debug("getOriginUrl: {}", getOriginUrl);

                response = restTemplate.getForObject(getOriginUrl, Response.class);

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
            log.error("Error when getTarif: {}, e");
        }

        return response;
    }
}