package org.o7planning.sbjdbctrans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CacheController
{
    private final CacheManager cacheManager;

    @Autowired
    public CacheController(CacheManager cacheManager)
    {
        this.cacheManager = cacheManager;
    }

    @GetMapping(value = "/clear-cache/{cache-name}")
    public ResponseEntity<String> clearCache(@PathVariable("cache-name") String cacheName)
    {
        try
        {
            Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();

            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Failed", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}