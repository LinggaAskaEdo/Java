package com.sql2o.hexagonal.adapter.config;

import com.google.common.cache.CacheBuilder;
import com.sql2o.hexagonal.adapter.preference.ConfigPreference;
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

@EnableCaching
@Configuration
public class CacheConfiguration implements CachingConfigurer
{
    public static final String BANK_CACHE = "bankCache";
    public static final String MOVIE_CACHE = "movieCache";
    public static final String STUDENT_CACHE = "studentCache";

    private final ConfigPreference preference;

    @Autowired
    public CacheConfiguration(ConfigPreference preference)
    {
        this.preference = preference;
    }

    @Bean
    @Override
    public CacheManager cacheManager()
    {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        GuavaCache cacheBank = new GuavaCache(BANK_CACHE, CacheBuilder.newBuilder().expireAfterWrite(preference.cacheExpiryBank, TimeUnit.SECONDS).build());
        GuavaCache cacheMovie = new GuavaCache(MOVIE_CACHE, CacheBuilder.newBuilder().expireAfterWrite(preference.cacheExpiryMovie, TimeUnit.MINUTES).build());
        GuavaCache cacheStudent = new GuavaCache(STUDENT_CACHE, CacheBuilder.newBuilder().expireAfterWrite(preference.cacheExpiryStudent, TimeUnit.SECONDS).build());

//        CaffeineCache cacheBank = new CaffeineCache(BANK_CACHE, Caffeine.newBuilder().expireAfterWrite(preference.cacheExpiryBank, TimeUnit.SECONDS).build());
//        CaffeineCache cacheMovie = new CaffeineCache(MOVIE_CACHE, Caffeine.newBuilder().expireAfterWrite(preference.cacheExpiryMovie, TimeUnit.MINUTES).build());
//        CaffeineCache cacheStudent = new CaffeineCache(STUDENT_CACHE, Caffeine.newBuilder().expireAfterWrite(preference.cacheExpiryStudent, TimeUnit.SECONDS).build());

        cacheManager.setCaches(Arrays.asList(cacheBank, cacheMovie, cacheStudent));

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