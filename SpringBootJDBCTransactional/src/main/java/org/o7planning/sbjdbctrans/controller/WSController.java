package org.o7planning.sbjdbctrans.controller;

import org.o7planning.sbjdbctrans.service.WSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WSController
{
    private WSService service;

    @Autowired
    public WSController(WSService service)
    {
        this.service = service;
    }

    @GetMapping(value = "/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> detailMovie(@RequestParam(value = "titleKey", defaultValue = "tt2911666") String titleKey)
    {
        return service.getDetailMovie(titleKey);
    }

    @GetMapping(value = "/v1/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> detailAccount(@RequestParam(value = "accountId", defaultValue = "1") Long accountId)
    {
        return service.getDetailAccount(accountId);
    }
}