package org.o7planning.sbjdbctrans.config;

import org.o7planning.sbjdbctrans.preference.ConfigPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport
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
    public JedisConnectionFactory jedisConnectionFactory()
    {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(preference.redisHost);
        config.setPort(preference.redisPort);
        config.setDatabase(preference.redisDatabase);

        return new JedisConnectionFactory(config);
    }

    @Bean
    public CacheManager cacheManager(@Qualifier("jedisConnectionFactory") JedisConnectionFactory connectionFactory)
    {
        RedisCacheConfiguration confTimeAccounts = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofSeconds(preference.expireTimeAccounts));

        RedisCacheConfiguration confTimeAccountsDB = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofSeconds(preference.expireTimeAccountsDB));

        RedisCacheConfiguration confTimeMovieInfo = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofSeconds(preference.expireTimeMovieInfo));

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        cacheConfigurations.put(CACHE_ACCOUNTS, confTimeAccounts);
        cacheConfigurations.put(CACHE_ACCOUNTS_DB, confTimeAccountsDB);
        cacheConfigurations.put(CACHE_MOVIE_INFO, confTimeMovieInfo);

        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }
}