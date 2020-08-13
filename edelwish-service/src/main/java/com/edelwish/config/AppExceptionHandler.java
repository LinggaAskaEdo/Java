package com.edelwish.config;

import com.edelwish.exception.*;
import com.edelwish.model.Response;
import com.edelwish.preference.ConstantPreference;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AppExceptionHandler
{
    @ExceptionHandler(value = { BadRequestException.class })
    @ResponseBody
    protected ResponseEntity<String> badRequest(BadRequestException ex)
    {
        return new ResponseEntity<>(bodyResponse(ConstantPreference.RESPONSE_CODE_BAD_REQUEST, ConstantPreference.RESPONSE_MESSAGE_BAD_REQUEST, ex.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    @ResponseBody
    protected ResponseEntity<String> notFound(NotFoundException ex)
    {
        return new ResponseEntity<>(bodyResponse(ConstantPreference.RESPONSE_CODE_NOT_FOUND, ConstantPreference.RESPONSE_MESSAGE_NOT_FOUND, ex.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { ConflictException.class })
    @ResponseBody
    protected ResponseEntity<String> conflict(ConflictException ex)
    {
        return new ResponseEntity<>(bodyResponse(ConstantPreference.RESPONSE_CODE_CONFLICT, ConstantPreference.RESPONSE_MESSAGE_CONFLICT, ex.toString()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { NoContentException.class })
    @ResponseBody
    protected ResponseEntity<String> noContent()
    {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = { InternalServerErrorException.class })
    @ResponseBody
    protected ResponseEntity<String> serviceUnavailable(InternalServerErrorException ex)
    {
        return new ResponseEntity<>(bodyResponse(ConstantPreference.RESPONSE_CODE_INTERNAL_SERVER_ERROR, ConstantPreference.RESPONSE_MESSAGE_INTERNAL_SERVER_ERROR, ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    protected ResponseEntity<String> generalException(Exception ex)
    {
        return new ResponseEntity<>(bodyResponse(ConstantPreference.RESPONSE_CODE_INTERNAL_SERVER_ERROR, ConstantPreference.RESPONSE_MESSAGE_INTERNAL_SERVER_ERROR, ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String bodyResponse(int code, String message, String detailMessage)
    {
        return new Gson().toJson(new Response(code, message, detailMessage, "ERROR", true));
    }
}
