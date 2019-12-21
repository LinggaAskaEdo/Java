package org.o7planning.sbjdbctrans.config;

import com.google.common.cache.CacheBuilder;
import org.o7planning.sbjdbctrans.preference.ConfigPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer
{
    public static final String CACHE_ACCOUNTS = "accounts";
    public static final String CACHE_ACCOUNTS_DB = "accountsDB";
    public static final String CACHE_MOVIE_INFO = "movieInfo";

    private ConfigPreference preference;

    @Autowired
    public CacheConfig(ConfigPreference preference)
    {
        this.preference = preference;
    }

    @Bean
    @Override
    public CacheManager cacheManager()
    {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        GuavaCache cacheAccounts = new GuavaCache(CACHE_ACCOUNTS, CacheBuilder.newBuilder()
                .expireAfterWrite(preference.expireTimeAccounts, TimeUnit.SECONDS)
                .build());

        GuavaCache cacheAccountsDB = new GuavaCache(CACHE_ACCOUNTS_DB, CacheBuilder.newBuilder()
                .expireAfterWrite(preference.expireTimeAccountsDB, TimeUnit.SECONDS)
                .build());

        GuavaCache cacheMovieInfo = new GuavaCache(CACHE_MOVIE_INFO, CacheBuilder.newBuilder()
                .expireAfterWrite(preference.expireTimeMovieInfo, TimeUnit.SECONDS)
                .build());

        cacheManager.setCaches(Arrays.asList(cacheAccounts, cacheAccountsDB, cacheMovieInfo));

        return cacheManager;
    }

    @Override
    public CacheResolver cacheResolver()
    {
        return null;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator()
    {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler()
    {
        return null;
    }
}