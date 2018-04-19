/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.controller;

import com.back.olshop.constant.ApplicationStatus;
import com.back.olshop.constant.MessagePreference;
import com.back.olshop.model.Request;
import com.back.olshop.model.Response;
import com.back.olshop.service.BOSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BOSController
{
    private final Logger log = LoggerFactory.getLogger(BOSController.class);

    @Autowired
    private BOSService service;

    @RequestMapping(value = "request", method = RequestMethod.POST)
    ResponseEntity<?> requestHandler(@RequestBody Request request)
    {
        System.out.println("Request: " + request.toString());

        if (request.getToken() != null && request.getMessage() != null)
        {
            boolean tokenStatus = true; //validate token

            if (tokenStatus)
            {
                boolean messageStatus = service.checkMessage(request.getMessage());

                if (messageStatus)
                {
                    return new ResponseEntity<>(new Response(ApplicationStatus.SUCCESS.toString(), MessagePreference.MESSAGE_VALID_REQUEST), HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity<>(new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_REQUEST), HttpStatus.OK);
                }
            }
            else
            {
                return new ResponseEntity<>(new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_TOKEN), HttpStatus.OK);
            }
        }
        else
        {
            return new ResponseEntity<>(new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_BAD_REQUEST), HttpStatus.OK);
        }
    }
}