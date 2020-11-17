package com.sql2o.hexagonal.adapter.ws;

import com.sql2o.hexagonal.adapter.config.CacheConfiguration;
import com.sql2o.hexagonal.adapter.preference.ConfigPreference;
import com.sql2o.hexagonal.application.model.MovieInfo;
import com.sql2o.hexagonal.application.port.outgoing.MoviePort;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieREST implements MoviePort
{
    private static final Logger logger = LogManager.getLogger();

    private final ConfigPreference preference;
    private final RestTemplate restTemplate;

    @Autowired
    public MovieREST(ConfigPreference preference, RestTemplate restTemplate)
    {
        this.preference = preference;
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(value = CacheConfiguration.MOVIE_CACHE,key = "#titleKey", cacheManager = "redisCacheManager")
    public MovieInfo findMovieByTitle(String titleKey)
    {
        MovieInfo movieInfo = null;

        try
        {
            String url = new URIBuilder()
                    .setScheme("http")
                    .setHost(preference.omdbApiUrl)
                    .addParameter("i", titleKey)
                    .addParameter("apikey", preference.omdbApiKey)
                    .toString();

            logger.debug("URL: {}", url);

            ResponseEntity<MovieInfo> response = restTemplate.exchange(url, HttpMethod.GET, null, MovieInfo.class);

            if (null != response.getBody())
            {
                movieInfo = response.getBody();
            }
        }
        catch (Exception e)
        {
            logger.error("Error when findMovieByTitle - {}: {}", titleKey, e.getMessage());
        }

        return movieInfo;
    }
}