package org.o7planning.sbjdbctrans.preference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigPreference
{
    @Value("${api.url}")
    public String apiUrl;

    @Value("${api.key}")
    public String apiKey;

    @Value("${expire.time.accounts}")
    public long expireTimeAccounts;

    @Value("${expire.time.accounts-db}")
    public long expireTimeAccountsDB;

    @Value("${expire.time.movie-info}")
    public long expireTimeMovieInfo;
}