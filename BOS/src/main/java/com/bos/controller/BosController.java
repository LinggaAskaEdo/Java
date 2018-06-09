/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.bos.controller;

import com.bos.constant.MessagePreference;
import com.bos.model.Request;
import com.bos.model.User;
import com.bos.service.BOSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BosController
{
    private final Logger log = LoggerFactory.getLogger(BosController.class);

    @Autowired
    private BOSService service;

    public String requestHandler(Request request)
    {
        log.debug("Request: {}", request.toString());

        if (request.getPhone() != null && request.getToken() != null && request.getMessage() != null)
        {
            //load user by token
            User user = service.loadUserByToken(request.getToken());

            if (user != null)
            {
                log.debug("user: {}", user);

                //check expired
                //boolean tokenExpired = service.checkTokenExpired(user.getUserTokenExpired());

                //check store open
                //boolean storeOpen = service.checkStoreOpen(user.getUserOpenTime(), user.getUserCloseTime());

                //log.debug("isExpired: {}", tokenExpired);
                //log.debug("storeOpen: {}", storeOpen);

                //if (tokenExpired)
                if (false)
                {
                    return MessagePreference.MESSAGE_TOKEN_EXPIRED;
                }
                //else if (!storeOpen)
                else if (false)
                {
                    return MessagePreference.MESSAGE_STORE_CLOSE;
                }
                else
                {
                    return service.checkMessage(user, request);
                }
            }
            else
            {
                return MessagePreference.MESSAGE_INVALID_USER;
            }
        }
        else
        {
            return MessagePreference.MESSAGE_BAD_REQUEST;
        }
    }
}