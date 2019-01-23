package id.co.homecredit.cc.pin.controller;

import com.google.gson.Gson;
import id.co.homecredit.cc.pin.model.Request;
import id.co.homecredit.cc.pin.model.Response;
import id.co.homecredit.cc.pin.preference.ConstantPreference;
import id.co.homecredit.cc.pin.service.AccountService;
import id.co.homecredit.cc.pin.service.PINService;
import id.co.homecredit.portal.common.core.security.Secured;
import id.co.homecredit.portal.common.core.security.SecurityContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PINController
{
    private static final Logger logger = LogManager.getLogger();

    private SecurityContextHolder contextHolder;
    private AccountService accountService;
    private PINService pinService;

    @Autowired
    public PINController(SecurityContextHolder contextHolder, AccountService accountService, PINService pinService)
    {
        this.contextHolder = contextHolder;
        this.accountService = accountService;
        this.pinService = pinService;
    }

    @Secured
    @RequestMapping(value = "/generateSession", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String generateSession(@RequestBody Request request)
    {
        Response response = new Response();

        if (null != contextHolder.getSubject() && null != request.getConnectionId())
        {
            String cuid = accountService.getCuid(contextHolder.getSubject());

            if (null != cuid)
            {
                logger.debug("cuid: {}", cuid);

                response = pinService.generateSession(cuid, request.getConnectionId());
            }
            else
            {
                response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_CUID_NOT_EXIST);
            }
        }
        else
        {
            response.setStatus(ConstantPreference.RESPONSE_CODE_BAD_REQUEST);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_BAD_REQUEST);
        }

        return new Gson().toJson(response);
    }

    @Secured
    @RequestMapping(value = "/setPin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String setPin(@RequestBody Request request)
    {
        Response response = new Response();

        if (null != contextHolder.getSubject() && null != request.getConnectionId() && null != request.getEncryptedPinBlock()
                && null != request.getSessionZpkKey())
        {
            String cuid = accountService.getCuid(contextHolder.getSubject());

            if (null != cuid)
            {
                logger.debug("cuid: {}", cuid);

                response = pinService.setPin(cuid, request.getConnectionId(), request.getEncryptedPinBlock(), request.getSessionZpkKey());
            }
            else
            {
                response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_CUID_NOT_EXIST);
            }
        }
        else
        {
            response.setStatus(ConstantPreference.RESPONSE_CODE_BAD_REQUEST);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_BAD_REQUEST);
        }

        return new Gson().toJson(response);
    }

    @RequestMapping(value = "/test/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> test(@PathVariable String userId) throws Exception
    {
        if (userId.equalsIgnoreCase("1"))
        {
            throw new IllegalArgumentException();
        }
        else if (userId.equalsIgnoreCase("2"))
        {
            throw new NoSuchFieldException();
        }
        else if (userId.equalsIgnoreCase("3"))
        {
            throw new Exception();
        }

        return new ResponseEntity<>(new Response(ConstantPreference.RESPONSE_CODE_OK, ConstantPreference.RESPONSE_MESSAGE_OK), HttpStatus.OK);
    }
}