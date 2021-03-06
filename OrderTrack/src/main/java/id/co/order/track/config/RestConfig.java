package id.co.order.track.config;

import id.co.order.track.preference.ConfigPreference;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

@Configuration
public class RestConfig
{
    private static final Logger logger = LogManager.getLogger();

    private final ConfigPreference preference;

    @Autowired
    public RestConfig(ConfigPreference preference)
    {
        this.preference = preference;
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory)
    {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);
        restTemplate.setErrorHandler(new ErrorHandler());

        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory()
    {
        int timeout = preference.wsTimeout;

        RequestConfig config = RequestConfig
                .custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(5);
        connectionManager.setMaxTotal(5);

        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .setConnectionManager(connectionManager)
                .build();

        return new HttpComponentsClientHttpRequestFactory(client);
    }

    private static class ErrorHandler implements ResponseErrorHandler
    {
        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse)
        {
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException
        {
            //conversion logic for decoding conversion
            ByteArrayInputStream arrayInputStream = (ByteArrayInputStream) clientHttpResponse.getBody();
            Scanner scanner = new Scanner(arrayInputStream, "UTF-8");
            scanner.useDelimiter("\\Z");
            String data = "";

            if (scanner.hasNext())
                data = scanner.next();

            logger.debug("handleError={}", data);
        }
    }
}