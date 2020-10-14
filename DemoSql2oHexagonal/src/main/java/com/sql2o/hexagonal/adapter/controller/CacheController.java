package com.sql2o.hexagonal.adapter.controller;

import com.sql2o.hexagonal.adapter.preference.ConfigPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CacheController
{
    private final ConfigPreference preference;
    private final CacheManager cacheManager;

    @Autowired
    public CacheController(ConfigPreference preference, CacheManager cacheManager)
    {
        this.preference = preference;
        this.cacheManager = cacheManager;
    }

    @DeleteMapping(value = "/cache/{cache-name}")
    public ResponseEntity<String> clearCache(@RequestHeader("token-cache") String tokenCache, @PathVariable("cache-name") String cacheName)
    {
        try
        {
            if (tokenCache.equalsIgnoreCase(preference.cacheToken))
            {
                Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();

                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("Failed", HttpStatus.UNAUTHORIZED);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Failed", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}