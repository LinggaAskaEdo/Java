package id.co.homecredit.async.call.service;

import id.co.homecredit.async.call.model.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CountryService
{
    private static final Logger logger = LogManager.getLogger();

    private final RestTemplate restTemplate;

    @Autowired
    public CountryService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<List<Country>> getCountriesByLanguage(String language)
    {
        String url = "https://restcountries.eu/rest/v2/lang/" + language + "?fields=name";
        Country[] response = restTemplate.getForObject(url, Country[].class);

        logger.debug("Response getCountriesByLanguage: {}", Arrays.toString(response));

        return CompletableFuture.completedFuture(Arrays.asList(response));
    }

    @Async
    public CompletableFuture<List<Country>> getCountriesByRegion(String region)
    {
        String url = "https://restcountries.eu/rest/v2/region/" + region + "?fields=name";
        Country[] response = restTemplate.getForObject(url, Country[].class);

        logger.debug("Response getCountriesByRegion: {}", Arrays.toString(response));

        return CompletableFuture.completedFuture(Arrays.asList(response));
    }
}