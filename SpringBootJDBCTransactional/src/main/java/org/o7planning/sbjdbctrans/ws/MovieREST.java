package org.o7planning.sbjdbctrans.ws;

import com.google.gson.Gson;
import org.apache.http.client.utils.URIBuilder;
import org.o7planning.sbjdbctrans.model.MovieInfo;
import org.o7planning.sbjdbctrans.preference.ConfigPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieREST
{
    private ConfigPreference preference;
    private RestTemplate restTemplate;

    @Autowired
    public MovieREST(ConfigPreference preference, RestTemplate restTemplate)
    {
        this.preference = preference;
        this.restTemplate = restTemplate;
    }

    public MovieInfo getMovieDetails(String titleKey)
    {
        System.out.println("Hitting API");

        MovieInfo movieInfo = new MovieInfo();

        try
        {
            String url = generateUrl(titleKey);
            System.out.println("URL: " + url);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

            if (null != response && null != response.getBody())
            {
                movieInfo = new Gson().fromJson(response.getBody(), MovieInfo.class);
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed when getMovieDetails: " + e.getMessage());
        }

        return movieInfo;
    }

    private String generateUrl(String titleKey)
    {
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost(preference.apiUrl)
                .addParameter("i", titleKey)
                .addParameter("apikey", preference.apiKey);

        return builder.toString();
    }
}