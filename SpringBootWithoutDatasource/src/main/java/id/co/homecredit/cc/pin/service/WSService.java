package id.co.homecredit.cc.pin.service;

import id.co.homecredit.portal.ws.CardInfo;
import id.co.homecredit.portal.ws.PartyV1Service;
import id.co.homecredit.portal.ws.PinChange;
import net.homecredit.homerselect.ws.card.cardinfo.v2.GetCardsRequest;
import net.homecredit.homerselect.ws.card.cardinfo.v2.GetCardsResponse;
import net.homecredit.homerselect.ws.card.pinchange.v1.GenerateSessionKeyRequest;
import net.homecredit.homerselect.ws.card.pinchange.v1.GenerateSessionKeyResponse;
import net.homecredit.homerselect.ws.card.pinchange.v1.SetPinByPcidRequest;
import net.homecredit.homerselect.ws.card.pinchange.v1.SetPinByPcidResponse;
import net.homecredit.party_ws._2016_1.CustomerPersonSearchRequest;
import net.homecredit.party_ws._2016_1.CustomerPersonSearchResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class WSService
{
    private static final Logger logger = LogManager.getLogger();

    private CardInfo cardInfo;
    private PinChange pinChange;
    private PartyV1Service partyV1Service;

    @Autowired
    public WSService(CardInfo cardInfo, PinChange pinChange, PartyV1Service partyV1Service)
    {
        this.cardInfo = cardInfo;
        this.pinChange = pinChange;
        this.partyV1Service = partyV1Service;
    }

    GetCardsResponse getListOfCards(String cuid)
    {
        GetCardsResponse response = null;

        try
        {
            GetCardsRequest request = new GetCardsRequest();
            request.setHolderCuid(Long.valueOf(cuid));

            response = cardInfo.getCardsResponse(request);
        }
        catch (Exception e)
        {
            logger.error("Error when getListOfCards: {}", e);
        }

        return response;
    }

    GenerateSessionKeyResponse generateSessionKey(String applicationId, String connectionId)
    {
        GenerateSessionKeyResponse response = null;

        try
        {
            GenerateSessionKeyRequest request = new GenerateSessionKeyRequest();
            request.setApplicationId(applicationId);
            request.setConnectionId(connectionId);

            response = pinChange.generateSessionKeyResponse(request);
        }
        catch (Exception e)
        {
            logger.error("Error when generateSessionKey: {}", e);
        }

        return response;
    }

    SetPinByPcidResponse setPin(String applicationId, String connectionId, long pcid, String pinBlock, String sessionZpkKey)
    {
        SetPinByPcidResponse response = null;

        try
        {
            SetPinByPcidRequest request = new SetPinByPcidRequest();
            request.setApplicationId(applicationId);
            request.setConnectionId(connectionId);
            request.setPcid(pcid);
            request.setEncryptedPinBlock(pinBlock);
            request.setSessionZpkKey(sessionZpkKey);
            request.setSuppressFee(false);

            response = pinChange.setPinByPcidResponse(request);
        }
        catch (Exception e)
        {
            logger.error("Error when setPin: {}", e);
        }

        return response;
    }

    CustomerPersonSearchResponse getDOB(String cuid)
    {
        CustomerPersonSearchResponse response = null;

        try
        {
            CustomerPersonSearchRequest request = new CustomerPersonSearchRequest();
            request.setFilter("externalId.eq("+ cuid +")");

            response = partyV1Service.customerPersonSearch(request);
        }
        catch (Exception e)
        {
            logger.error("Error when getDOB: {}", e);
        }

        return response;
    }

    CustomerPersonSearchResponse getCuid(String userId)
    {
        CustomerPersonSearchResponse response = new CustomerPersonSearchResponse();

        try
        {
            CustomerPersonSearchRequest request = new CustomerPersonSearchRequest();
            request.setFilter("phoneNumbers.number.eq(" + userId + ").and(phoneNumbers.classification.eq(PRIMARY_MOBILE))");

            response = partyV1Service.customerPersonSearch(request);
        }
        catch (Exception e)
        {
            logger.error("Error when getCuid: {}", e);
        }

        return response;
    }
}