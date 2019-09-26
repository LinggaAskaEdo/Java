package com.springboot.timeout.controller;

import java.util.concurrent.Callable;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.springboot.timeout.exception.InterruptedException;
import com.springboot.timeout.model.Response;

@RestController
public class HelloController 
{
	@RequestMapping("/")
//	@Timed
//	@Transactional(timeout = 120)
    public String index()
	{
        return "Greetings from Spring Boot!";
    }
	
	@GetMapping("/a")
	public Callable<String> testA()
	{
	    return new Callable<String>()
	    {
	        @Override
	        public String call() throws Exception 
	        {
	            Thread.sleep(8000); //this will cause a timeout
	            
	            return "foobar";
	        }
	    };
	}
	
	@GetMapping(value = "/b", produces = MediaType.APPLICATION_JSON_VALUE)
	public Callable<ResponseEntity<String>> testB()
	{
		return new Callable<ResponseEntity<String>>()
	    {
	        @Override
	        public ResponseEntity<String> call()
	        {
	            try 
	            {
					Thread.sleep(8000);
					
					Response response = new Response();
		            response.setStatus("200");
		            response.setMessage("Berhasil");
		            
		            return new ResponseEntity<>(new Gson().toJson(response), HttpStatus.OK);
				} 
	            catch (java.lang.InterruptedException e)
	            {
	            	throw new InterruptedException();
				}
	        }
	    };
	}
}