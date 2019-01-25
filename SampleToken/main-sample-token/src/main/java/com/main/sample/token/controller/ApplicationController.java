package com.main.sample.token.controller;

import com.common.sample.token.security.Secured;
import com.common.sample.token.security.SecurityContextHolder;
import com.google.gson.Gson;
import com.main.sample.token.model.LoginRequest;
import com.main.sample.token.model.Response;
import com.main.sample.token.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController
{
    private SecurityContextHolder contextHolder;
    private ApplicationService applicationService;

    @Autowired
    public ApplicationController(SecurityContextHolder contextHolder, ApplicationService applicationService)
    {
        this.contextHolder = contextHolder;
        this.applicationService = applicationService;
    }

    @Secured
    @RequestMapping(value = "/main/testToken", method = RequestMethod.GET)
    public void testToken()
    {
        if (null != contextHolder.getSubject())
        {
            System.out.println("User ID: " + contextHolder.getSubject());
        }
        else
        {
            System.out.println("User ID is empty !!!");
        }
    }

    @RequestMapping(value = "/main/testLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String testLogin(@RequestBody LoginRequest request)
    {
        Response response = new Response();

        if (null != request && request.getPin().equalsIgnoreCase("askacool"))
        {
            response = applicationService.issuedToken(request);
//            response = applicationService.issuedToken(contextHolder.getSubject(), "NOT-GUEST");
        }
        else
        {
            response.setStatus("503");
            response.setMessage("There is planned service outage. We should specify response headers or error object with more details on service outage");
        }

        return new Gson().toJson(response);
    }
}