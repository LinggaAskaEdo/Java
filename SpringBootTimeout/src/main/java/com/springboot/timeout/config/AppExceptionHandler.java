package com.springboot.timeout.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.Gson;
import com.springboot.timeout.exception.InterruptedException;
import com.springboot.timeout.model.Response;

@ControllerAdvice
public class AppExceptionHandler
{	
	@ExceptionHandler(value = { InterruptedException.class })
    protected ResponseEntity<String> interrupt(InterruptedException ex)
    {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
        return new ResponseEntity<String>(new Gson().toJson(new Response(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())
        		, "The request was interrupted because of timeout"))
        		, headers
        		, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}