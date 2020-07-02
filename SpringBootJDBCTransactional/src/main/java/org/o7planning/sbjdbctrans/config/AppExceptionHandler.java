package org.o7planning.sbjdbctrans.config;

import com.google.gson.Gson;
import org.o7planning.sbjdbctrans.exception.*;
import org.o7planning.sbjdbctrans.model.Response;
import org.o7planning.sbjdbctrans.preference.ConstantPreference;
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
        return new ResponseEntity<>(new Gson().toJson(new Response(HttpStatus.NOT_FOUND.toString(), ConstantPreference.RESPONSE_MESSAGE_CUID_NOT_EXIST)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { BadRequestException.class })
    @ResponseBody
    protected ResponseEntity<String> badRequest(BadRequestException ex)
    {
        return new ResponseEntity<>(new Gson().toJson(new Response(HttpStatus.BAD_REQUEST.toString(), ConstantPreference.RESPONSE_MESSAGE_BAD_REQUEST)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { NoContentException.class })
    @ResponseBody
    protected ResponseEntity<String> noContent(NoContentException ex)
    {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(value = { InternalServerErrorException.class })
    @ResponseBody
    protected ResponseEntity<String> serviceUnavailable(InternalServerErrorException ex)
    {
        return new ResponseEntity<>(new Gson().toJson(new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.toString())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    protected ResponseEntity<String> generalException(Exception ex)
    {
        return new ResponseEntity<>(new Gson().toJson(new Response(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.toString())), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
