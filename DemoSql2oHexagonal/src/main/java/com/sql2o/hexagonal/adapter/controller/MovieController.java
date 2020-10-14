package com.sql2o.hexagonal.adapter.controller;

import com.sql2o.hexagonal.application.model.Response;
import com.sql2o.hexagonal.application.port.incoming.MovieUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController
{
    private final MovieUseCase movieUseCase;

    public MovieController(MovieUseCase movieUseCase)
    {
        this.movieUseCase = movieUseCase;
    }

    @GetMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> detailMovie(@RequestParam(value = "titleKey", defaultValue = "tt2911666") String titleKey)
    {
        return new ResponseEntity<>(movieUseCase.detailMovie(titleKey), HttpStatus.OK);
    }
}