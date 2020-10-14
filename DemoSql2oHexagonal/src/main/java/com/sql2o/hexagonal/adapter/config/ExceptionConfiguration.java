package com.sql2o.hexagonal.adapter.config;

import com.sql2o.hexagonal.application.model.exception.BadRequestException;
import com.sql2o.hexagonal.application.model.exception.InternalServerErrorException;
import com.sql2o.hexagonal.application.model.exception.NoContentException;
import com.sql2o.hexagonal.application.model.exception.NotFoundException;
import com.sql2o.hexagonal.adapter.preference.ConstantPreference;
import com.sql2o.hexagonal.application.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionConfiguration
{
    @ExceptionHandler(value = { NoContentException.class })
    @ResponseBody
    protected ResponseEntity<Response> noContent()
    {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = { BadRequestException.class })
    @ResponseBody
    protected ResponseEntity<Response> badRequest(BadRequestException ex)
    {
        return new ResponseEntity<>(bodyResponse(ConstantPreference.RESPONSE_CODE_BAD_REQUEST, ConstantPreference.RESPONSE_MESSAGE_BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    @ResponseBody
    protected ResponseEntity<Response> notFound(NotFoundException ex)
    {
        return new ResponseEntity<>(bodyResponse(ConstantPreference.RESPONSE_CODE_NOT_FOUND, ConstantPreference.RESPONSE_MESSAGE_NOT_FOUND, ex.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { InternalServerErrorException.class })
    @ResponseBody
    protected ResponseEntity<Response> serviceUnavailable(InternalServerErrorException ex)
    {
        return new ResponseEntity<>(bodyResponse(ConstantPreference.RESPONSE_CODE_INTERNAL_SERVER_ERROR, ConstantPreference.RESPONSE_MESSAGE_INTERNAL_SERVER_ERROR, ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    protected ResponseEntity<Response> generalException(Exception ex)
    {
        return new ResponseEntity<>(bodyResponse(ConstantPreference.RESPONSE_CODE_INTERNAL_SERVER_ERROR, ConstantPreference.RESPONSE_MESSAGE_INTERNAL_SERVER_ERROR, ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Response bodyResponse(int code, String message, String detailMessage)
    {
        return new Response(code, message, detailMessage, "ERROR", true);
    }
}