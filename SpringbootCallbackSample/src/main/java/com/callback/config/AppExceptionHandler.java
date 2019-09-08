package com.callback.config;

import com.callback.exception.CuidNotExistException;
import com.callback.model.Response;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AppExceptionHandler
{
    @ExceptionHandler(value = { CuidNotExistException.class })
    @ResponseBody
    protected ResponseEntity<String> cuidNotFound(CuidNotExistException ex)
    {
        return new ResponseEntity<>(new Gson().toJson(new Response(HttpStatus.NOT_FOUND.toString(), "Cuid Not Exist")), HttpStatus.OK);
    }

//    @ExceptionHandler(value = { BadRequestException.class })
//    @ResponseBody
//    protected ResponseEntity<String> badRequest(BadRequestException ex)
//    {
//        return new ResponseEntity<>(new Gson().toJson(new Response(HttpStatus.BAD_REQUEST.toString(), ConstantPreference.RESPONSE_MESSAGE_BAD_REQUEST)), HttpStatus.OK);
//    }
//
//    @ExceptionHandler(value = { NoContentException.class })
//    @ResponseBody
//    protected ResponseEntity<String> noContent(NoContentException ex)
//    {
//        return new ResponseEntity<>(new Gson().toJson(new Response(HttpStatus.NO_CONTENT.toString(), ConstantPreference.RESPONSE_MESSAGE_NO_CONTENT)), HttpStatus.OK);
//    }
//
//    @ExceptionHandler(value = { InternalServerErrorException.class })
//    @ResponseBody
//    protected ResponseEntity<String> serviceUnavailable(InternalServerErrorException ex)
//    {
//        return new ResponseEntity<>(new Gson().toJson(new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.toString())), HttpStatus.OK);
//    }
//
//    @ExceptionHandler(value = { Exception.class })
//    @ResponseBody
//    protected ResponseEntity<String> generalException(Exception ex)
//    {
//        return new ResponseEntity<>(new Gson().toJson(new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.toString())), HttpStatus.OK);
//    }
}
