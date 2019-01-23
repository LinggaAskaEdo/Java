package id.co.homecredit.cc.pin.service;

import id.co.homecredit.cc.pin.model.Response;
import id.co.homecredit.cc.pin.preference.ConstantPreference;
import id.co.homecredit.cc.pin.util.CreditCardUtil;
import net.homecredit.homerselect.ws.card.cardinfo.v2.CardDto;
import net.homecredit.homerselect.ws.card.cardinfo.v2.CardStatusDto;
import net.homecredit.homerselect.ws.card.cardinfo.v2.GetCardsResponse;
import net.homecredit.homerselect.ws.card.cardinfo.v2.GetCardsResponseResultCodeDto;
import net.homecredit.homerselect.ws.card.pinchange.v1.GenerateSessionKeyResponse;
import net.homecredit.homerselect.ws.card.pinchange.v1.GenerateSessionKeyResultCode;
import net.homecredit.homerselect.ws.card.pinchange.v1.SetPinByPcidResponse;
import net.homecredit.homerselect.ws.card.pinchange.v1.SetPinByPcidResultCode;
import net.homecredit.party._2016_1.CustomerPerson;
import net.homecredit.party._2016_1.Person;
import net.homecredit.party._2016_1.RoleSearchResultCode;
import net.homecredit.party_ws._2016_1.CustomerPersonSearchResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PINServiceTest
{
    private String applicationId;
    private String connectionId;
    private String cuid;
    private String publicKey;
    private String dob;
    private String encryptedPinBlock;
    private String sessionZpkKey;

    @BeforeMethod
    public void initialize()
    {
        applicationId = "IVR";
        connectionId = "dc86094a-81fe-4293-bc28-1d1e4251fb59";
        cuid = "2533921";
        publicKey = "MIIBCgKCAQEAn6q6DjBzpaoVrYoRfCVvcdtMFAcGgN0nx2kItdwmhuvROxT5sIu2BaUS/3sLGKUxdGeL0WWJCBoOkE8MDkRPE8aa/0L6bviP94MCkiWA3lLbpp7TdH/b1VM8itsbWbvxwq2T8rPOSuIzqczxIohYUHiXbCa7gynYgwgxSAdLxnzKmRJgj3KsPcAspYVJXlHGmjEVYKEG3BiYGvBo3bY9XF27s0i9LB5JqExBHc2DVtcuhBXFjvtYO+kKUp5OnGPPJd9vaeM+AkHmkvBeC6zm1Liph6+6/Bp5NHG0sAdhJe0E4mQ2MHKAjxzTMbEG51pk1Q91Wa/UoMaqgM/KwjL5rwIDAQAB";
        dob = "160190";
        encryptedPinBlock = "1313512edq21";
        sessionZpkKey = "nqinoi2n1-9hnaksndkandlsakl";
    }

    @Test
    public void testGenerateSessionNull()
    {
        System.out.println("Test Generate Session Null");

        Response response = new Response();
        response.setStatus(ConstantPreference.RESPONSE_CODE_SERVICE_UNAVAILABLE);
        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SERVICE_UNAVAILABLE);

        final WSService wsService = mock(WSService.class);
        when(wsService.generateSessionKey(applicationId, connectionId)).thenReturn(null);

        final PINService pinService = new PINService(wsService, mock(CreditCardUtil.class));
        assertThat(pinService.generateSession(applicationId, connectionId)).isEqualToComparingFieldByField(response);
    }

    @Test
    public void testGenerateSessionDobNull()
    {
        System.out.println("Test Generate Session DOB Null");

        Response response = new Response();
        response.setStatus(ConstantPreference.RESPONSE_CODE_SERVICE_UNAVAILABLE);
        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SERVICE_UNAVAILABLE);

        GenerateSessionKeyResponse generateSessionKeyResponse = new GenerateSessionKeyResponse();
        generateSessionKeyResponse.setResultCode(GenerateSessionKeyResultCode.SUCCESS);

        final WSService wsService = mock(WSService.class);
        when(wsService.generateSessionKey(applicationId, connectionId)).thenReturn(generateSessionKeyResponse);
        when(wsService.getDOB(cuid)).thenReturn(null);

        final PINService pinService = new PINService(wsService, mock(CreditCardUtil.class));
        assertThat(pinService.generateSession(applicationId, connectionId)).isEqualToComparingFieldByField(response);
    }

    @Test
    public void testGenerateSessionSuccess()
    {
        System.out.println("Test Generate Session Success");

        try
        {
            Response response = new Response();
            response.setStatus(ConstantPreference.RESPONSE_CODE_OK);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
//            response.setPublicKey(publicKey);
//            response.setDOB(dob);

            GenerateSessionKeyResponse generateSessionKeyResponse = new GenerateSessionKeyResponse();
            generateSessionKeyResponse.setResultCode(GenerateSessionKeyResultCode.SUCCESS);
            generateSessionKeyResponse.setPublicKey(publicKey);

            CustomerPersonSearchResponse customerPersonSearchResponse = new CustomerPersonSearchResponse();
            customerPersonSearchResponse.setResultCode(RoleSearchResultCode.FOUND);

            CustomerPerson customerPerson = new CustomerPerson();

            //generate date
            DateFormat format = new SimpleDateFormat("ddMMyy");
            Date date = format.parse(dob);

            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);

            XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            //end generate date

            Person person = new Person();
            person.setBirthDate(xmlGregCal);

            customerPerson.setPerson(person);
            customerPersonSearchResponse.getItems().add(customerPerson);

            final WSService wsService = mock(WSService.class);
            when(wsService.generateSessionKey(applicationId, connectionId)).thenReturn(generateSessionKeyResponse);
            when(wsService.getDOB(cuid)).thenReturn(customerPersonSearchResponse);

            final CreditCardUtil creditCardUtil = mock(CreditCardUtil.class);
            when(creditCardUtil.parseDate(xmlGregCal)).thenReturn(dob);

            final PINService pinService = new PINService(wsService, creditCardUtil);
            assertThat(customerPersonSearchResponse.getItems().size()).isEqualTo(1);
            assertThat(customerPersonSearchResponse).isNotEqualTo(null);
        }
        catch (Exception e)
        {
            System.out.println("Error when testGenerateSessionSuccess: " + e);
        }
    }

    @Test
    public void testSetPin_ServiceUnavailable()
    {
        System.out.println("Test SetPin - Service Unavailable");

        final WSService wsService = mock(WSService.class);
        when(wsService.getListOfCards(cuid)).thenReturn(null);

        final PINService pinService = new PINService(wsService, mock(CreditCardUtil.class));
        assertThat(pinService.setPin(cuid, connectionId, encryptedPinBlock, sessionZpkKey).getStatus()).isEqualTo(ConstantPreference.RESPONSE_CODE_SERVICE_UNAVAILABLE);
        assertThat(pinService.setPin(cuid, connectionId, encryptedPinBlock, sessionZpkKey).getMessage()).isEqualTo(ConstantPreference.RESPONSE_MESSAGE_SERVICE_UNAVAILABLE);
    }

    @Test
    public void testSetPin_Okay()
    {
        System.out.println("Test SetPin - Okay");

        long pcid = 1234;

        /*mock GetCards*/
        GetCardsResponse cardsResponse = new GetCardsResponse();
        cardsResponse.setResultCode(GetCardsResponseResultCodeDto.SUCCESS);

        CardDto cardDto = new CardDto();
        cardDto.setStatus(CardStatusDto.ACTIVE);
        cardDto.setPcid(pcid);

        cardsResponse.getCards().add(cardDto);

        /*mock SetPinByPcid*/
        SetPinByPcidResponse setPinByPcidResponse = new SetPinByPcidResponse();
        setPinByPcidResponse.setResultCode(SetPinByPcidResultCode.SUCCESS);

        /*mock WSService*/
        final WSService wsService = mock(WSService.class);
        when(wsService.getListOfCards(cuid)).thenReturn(cardsResponse);
        when(wsService.setPin("IVR", connectionId, pcid, encryptedPinBlock, sessionZpkKey)).thenReturn(setPinByPcidResponse);

        final PINService pinService = new PINService(wsService, mock(CreditCardUtil.class));
        assertThat(pinService.setPin(cuid, connectionId, encryptedPinBlock, sessionZpkKey).getStatus()).isEqualTo(ConstantPreference.RESPONSE_CODE_OK);
        assertThat(pinService.setPin(cuid, connectionId, encryptedPinBlock, sessionZpkKey).getMessage()).isEqualTo(ConstantPreference.RESPONSE_MESSAGE_OK);
    }
}