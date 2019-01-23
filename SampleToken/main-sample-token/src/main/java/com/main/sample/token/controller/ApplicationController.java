package com.main.sample.token.controller;

import com.common.sample.token.security.Secured;
import com.common.sample.token.security.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController
{
    private SecurityContextHolder contextHolder;

    @Autowired
    public ApplicationController(SecurityContextHolder contextHolder)
    {
        this.contextHolder = contextHolder;
    }

    @Secured
    @RequestMapping(value = "/main/testToken", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getContractPayment()
    {
        if (null != contextHolder.getSubject())
        {
            System.out.println("Token: " + contextHolder.getSubject());
        }
        else
        {
            System.out.println("Token is empty !!!");
        }
    }
}