package com.example.service.queues.config;

import com.example.service.queues.exception.BadRequestException;
import com.example.service.queues.exception.InternalServerErrorException;
import com.example.service.queues.model.Response;
import com.example.service.queues.preference.ConstantPreference;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler
{
    @ExceptionHandler(value = { BadRequestException.class })
    protected ResponseEntity<String> badRequest(BadRequestException ex)
    {
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyResponse(ConstantPreference.RESPONSE_CODE_BAD_REQUEST, ConstantPreference.RESPONSE_MESSAGE_BAD_REQUEST, ex.toString()));
    }

    @ExceptionHandler(value = { InternalServerErrorException.class })
    protected ResponseEntity<String> serviceUnavailable(InternalServerErrorException ex)
    {
        return ResponseEntity
                .status(500)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyResponse(ConstantPreference.RESPONSE_CODE_INTERNAL_SERVER_ERROR, ConstantPreference.RESPONSE_MESSAGE_INTERNAL_SERVER_ERROR, ex.toString()));
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<String> generalException(Exception ex)
    {
        return ResponseEntity
                .status(500)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyResponse(ConstantPreference.RESPONSE_CODE_INTERNAL_SERVER_ERROR, ConstantPreference.RESPONSE_MESSAGE_INTERNAL_SERVER_ERROR, ex.toString()));
    }

    private String bodyResponse(int code, String message, String detailMessage)
    {
        return new Gson().toJson(new Response(code, message, detailMessage, "ERROR", true));
    }
}