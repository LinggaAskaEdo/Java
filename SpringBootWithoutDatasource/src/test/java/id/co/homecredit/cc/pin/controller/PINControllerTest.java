package id.co.homecredit.cc.pin.controller;

import com.google.gson.Gson;
import id.co.homecredit.cc.pin.model.Request;
import id.co.homecredit.cc.pin.model.Response;
import id.co.homecredit.cc.pin.preference.ConstantPreference;
import id.co.homecredit.cc.pin.service.AccountService;
import id.co.homecredit.cc.pin.service.PINService;
import id.co.homecredit.portal.common.core.security.SecurityContextHolder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PINControllerTest
{
    private String userId;
    private String connectionId;
    private String cuid;
    private String encryptedPinBlock;
    private String sessionZpkKey;

    @BeforeMethod
    public void initialize()
    {
        userId = "81244444444";
        connectionId = "dc86094a-81fe-4293-bc28-1d1e4251fb59";
        cuid = "1234";
        encryptedPinBlock = "1313512edq21";
        sessionZpkKey = "nqinoi2n1-9hnaksndkandlsakl";
    }

    @Test
    public void testGenerateSession_Error()
    {
        System.out.println("Test GenerateSession - Error");

        /*mock Request*/
        Request request = new Request();
        request.setConnectionId(connectionId);

        /*mock SecurityContextHolder*/
        final SecurityContextHolder securityContextHolder = mock(SecurityContextHolder.class);
        when(securityContextHolder.getSubject()).thenReturn(userId);

        /*mock AccountService*/
        final AccountService accountService = mock(AccountService.class);
        when(accountService.getCuid(userId)).thenReturn(null);

        final PINController pinController = new PINController(securityContextHolder, accountService, mock(PINService.class));
        assertThat(new Gson().fromJson(pinController.generateSession(request), Response.class).getStatus()).isEqualTo(ConstantPreference.RESPONSE_CODE_ERROR);
        assertThat(new Gson().fromJson(pinController.generateSession(request), Response.class).getMessage()).isEqualTo(ConstantPreference.RESPONSE_MESSAGE_CUID_NOT_EXIST);
    }

    @Test
    public void testGenerateSession_BadRequest()
    {
        System.out.println("Test GenerateSession - BadRequest");

        /*mock Request*/
        Request request = new Request();

        final PINController pinController = new PINController(mock(SecurityContextHolder.class), mock(AccountService.class), mock(PINService.class));
        assertThat(new Gson().fromJson(pinController.generateSession(request), Response.class).getStatus()).isEqualTo(ConstantPreference.RESPONSE_CODE_BAD_REQUEST);
        assertThat(new Gson().fromJson(pinController.generateSession(request), Response.class).getMessage()).isEqualTo(ConstantPreference.RESPONSE_MESSAGE_BAD_REQUEST);
    }

    @Test
    public void testGenerateSession_Okay()
    {
        System.out.println("Test GenerateSession - Okay");

        /*mock Request*/
        Request request = new Request();
        request.setConnectionId(connectionId);

        /*mock Response*/
        Response response = new Response();
        response.setStatus(ConstantPreference.RESPONSE_CODE_OK);
        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);

        /*mock SecurityContextHolder*/
        final SecurityContextHolder securityContextHolder = mock(SecurityContextHolder.class);
        when(securityContextHolder.getSubject()).thenReturn(userId);

        /*mock AccountService*/
        final AccountService accountService = mock(AccountService.class);
        when(accountService.getCuid(userId)).thenReturn(cuid);

        /*mock PinService*/
        final PINService pinService = mock(PINService.class);
        when(pinService.generateSession(cuid, connectionId)).thenReturn(response);

        final PINController pinController = new PINController(securityContextHolder, accountService, pinService);
        assertThat(new Gson().fromJson(pinController.generateSession(request), Response.class).getStatus()).isEqualTo(ConstantPreference.RESPONSE_CODE_OK);
        assertThat(new Gson().fromJson(pinController.generateSession(request), Response.class).getMessage()).isEqualTo(ConstantPreference.RESPONSE_MESSAGE_OK);
    }

    @Test
    public void testSetPin_Error()
    {
        System.out.println("Test SetPin - Error");

        /*mock Request*/
        Request request = new Request();
        request.setConnectionId(connectionId);
        request.setEncryptedPinBlock(encryptedPinBlock);
        request.setSessionZpkKey(sessionZpkKey);

        /*mock SecurityContextHolder*/
        final SecurityContextHolder securityContextHolder = mock(SecurityContextHolder.class);
        when(securityContextHolder.getSubject()).thenReturn(userId);

        /*mock AccountService*/
        final AccountService accountService = mock(AccountService.class);
        when(accountService.getCuid(userId)).thenReturn(null);

        final PINController pinController = new PINController(securityContextHolder, accountService, mock(PINService.class));
        assertThat(new Gson().fromJson(pinController.setPin(request), Response.class).getStatus()).isEqualTo(ConstantPreference.RESPONSE_CODE_ERROR);
        assertThat(new Gson().fromJson(pinController.setPin(request), Response.class).getMessage()).isEqualTo(ConstantPreference.RESPONSE_MESSAGE_CUID_NOT_EXIST);
    }

    @Test
    public void testSetPin_BadRequest()
    {
        System.out.println("Test SetPin - Bad Request");

        /*mock Request*/
        Request request = new Request();

        final PINController pinController = new PINController(mock(SecurityContextHolder.class), mock(AccountService.class), mock(PINService.class));
        assertThat(new Gson().fromJson(pinController.setPin(request), Response.class).getStatus()).isEqualTo(ConstantPreference.RESPONSE_CODE_BAD_REQUEST);
        assertThat(new Gson().fromJson(pinController.setPin(request), Response.class).getMessage()).isEqualTo(ConstantPreference.RESPONSE_MESSAGE_BAD_REQUEST);
    }

    @Test
    public void testSetPin_Okay()
    {
        /*mock Request*/
        Request request = new Request();
        request.setConnectionId(connectionId);
        request.setEncryptedPinBlock(encryptedPinBlock);
        request.setSessionZpkKey(sessionZpkKey);

        /*mock Response*/
        Response response = new Response();
        response.setStatus(ConstantPreference.RESPONSE_CODE_OK);
        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);

        /*mock SecurityContextHolder*/
        final SecurityContextHolder securityContextHolder = mock(SecurityContextHolder.class);
        when(securityContextHolder.getSubject()).thenReturn(userId);

        /*mock AccountService*/
        final AccountService accountService = mock(AccountService.class);
        when(accountService.getCuid(userId)).thenReturn(cuid);

        /*mock PinService*/
        final PINService pinService = mock(PINService.class);
        when(pinService.setPin(cuid, connectionId, encryptedPinBlock, sessionZpkKey)).thenReturn(response);

        final PINController pinController = new PINController(securityContextHolder, accountService, pinService);
        assertThat(new Gson().fromJson(pinController.setPin(request), Response.class).getStatus()).isEqualTo(ConstantPreference.RESPONSE_CODE_OK);
        assertThat(new Gson().fromJson(pinController.setPin(request), Response.class).getMessage()).isEqualTo(ConstantPreference.RESPONSE_MESSAGE_OK);
    }
}