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
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration implements CachingConfigurer
{
    public static final String BANK_CACHE = "BANK_CACHE";
    public static final String MOVIE_CACHE = "MOVIE_CACHE";
    public static final String STUDENT_CACHE = "STUDENT_CACHE";
    public static final String EMPLOYEE_CACHE = "EMPLOYEE_CACHE";

    private final ConfigPreference preference;

    @Autowired
    public CacheConfiguration(ConfigPreference preference)
    {
        this.preference = preference;
    }

    @Bean
    @Primary
    @Override
    public CacheManager cacheManager()
    {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        GuavaCache cacheBank = new GuavaCache(BANK_CACHE, CacheBuilder.newBuilder().expireAfterWrite(preference.cacheExpiryBank, TimeUnit.SECONDS).build());
        GuavaCache cacheMovie = new GuavaCache(MOVIE_CACHE, CacheBuilder.newBuilder().expireAfterWrite(preference.cacheExpiryMovie, TimeUnit.MINUTES).build());
        GuavaCache cacheStudent = new GuavaCache(STUDENT_CACHE, CacheBuilder.newBuilder().expireAfterWrite(preference.cacheExpiryStudent, TimeUnit.SECONDS).build());

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

    @Bean
    public JedisConnectionFactory jedisConnectionFactory()
    {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(preference.redisHost);
        redisStandaloneConfiguration.setPort(preference.redisPort);
        redisStandaloneConfiguration.setDatabase(preference.redisDatabase);

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public CacheManager redisCacheManager()
    {
        RedisCacheConfiguration confBank = RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofSeconds(30));

        RedisCacheConfiguration confMovie = RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofMinutes(60));

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        cacheConfigurations.put(BANK_CACHE, confBank);
        cacheConfigurations.put(MOVIE_CACHE, confMovie);

        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(jedisConnectionFactory())
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate()
    {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        RedisSerializer<String> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);

        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}