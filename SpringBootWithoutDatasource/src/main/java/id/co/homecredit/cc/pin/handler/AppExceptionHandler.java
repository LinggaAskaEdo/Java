package id.co.homecredit.cc.pin.handler;

import id.co.homecredit.cc.pin.model.Response;
import id.co.homecredit.cc.pin.preference.ConstantPreference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Response> handleAnyException()
    {
        return new ResponseEntity<>(new Response(ConstantPreference.RESPONSE_CODE_SERVICE_UNAVAILABLE, ConstantPreference.RESPONSE_MESSAGE_SERVICE_UNAVAILABLE), HttpStatus.OK);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<Response> handleIllegalArgumentException()
    {
        return new ResponseEntity<>(new Response(ConstantPreference.RESPONSE_CODE_BAD_REQUEST, ConstantPreference.RESPONSE_MESSAGE_BAD_REQUEST), HttpStatus.OK);
    }

    @ExceptionHandler(value = { NoSuchFieldException.class })
    public ResponseEntity<Response> handleNoSuchFieldException()
    {
        return new ResponseEntity<>(new Response(ConstantPreference.RESPONSE_CODE_NO_CONTENT, ConstantPreference.RESPONSE_MESSAGE_NO_CONTENT), HttpStatus.OK);
    }
}