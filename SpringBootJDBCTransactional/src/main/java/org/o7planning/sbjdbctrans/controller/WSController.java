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

    @GetMapping(value = "/v1/movie/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> detailMovie(@RequestParam(defaultValue = "tt2911666", name = "titleKey") String titleKey)
    {
        return service.getDetailMovie(titleKey);
    }
}