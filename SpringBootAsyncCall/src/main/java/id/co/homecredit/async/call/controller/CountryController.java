package id.co.homecredit.async.call.controller;

import id.co.homecredit.async.call.model.Country;
import id.co.homecredit.async.call.service.CountryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
public class CountryController
{
    private static final Logger logger = LogManager.getLogger();

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService)
    {
        this.countryService = countryService;
    }

    @GetMapping(value = "/v1/country", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllEuropeanFrenchSpeakingCountries()
    {
        CompletableFuture<List<Country>> countriesByLanguageFuture = countryService.getCountriesByLanguage("fr");
        CompletableFuture<List<Country>> countriesByRegionFuture = countryService.getCountriesByRegion("europe");

        List<String> europeanFrenchSpeakingCountries = new ArrayList<>();

        try
        {
            europeanFrenchSpeakingCountries = countriesByLanguageFuture.get().stream().map(Country::getName).collect(Collectors.toList());
            europeanFrenchSpeakingCountries.retainAll(countriesByRegionFuture.get().stream().map(Country::getName).collect(Collectors.toList()));
        }
        catch (Exception e)
        {
            logger.error("Error when getCountry: ", e);
        }

        return europeanFrenchSpeakingCountries;
    }
}