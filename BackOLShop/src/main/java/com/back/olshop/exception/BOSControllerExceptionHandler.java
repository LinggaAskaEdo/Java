/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.exception;

import com.back.olshop.constant.ApplicationStatus;
import com.back.olshop.constant.MessagePreference;
import com.back.olshop.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;

@ControllerAdvice(annotations = RestController.class)
public class BOSControllerExceptionHandler
{
    private final Logger log = LoggerFactory.getLogger(BOSControllerExceptionHandler.class);

    @ExceptionHandler(NoResultException.class)
    @ResponseBody
    public ResponseEntity<?> catchNoResultException(NoResultException e)
    {
        log.error("catchNoResultException: {}", e);
        return new ResponseEntity<>(new Response(ApplicationStatus.FAILED.toString(), MessagePreference.DATA_NOT_FOUND), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> catchException(Exception e)
    {
        log.error("catchException: {}", e);
        return new ResponseEntity<>(new Response(ApplicationStatus.FAILED.toString(), MessagePreference.EXCEPTION), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}