package id.co.homecredit.cc.pin.service;

import id.co.homecredit.cc.pin.model.Response;
import id.co.homecredit.cc.pin.preference.ConstantPreference;
import id.co.homecredit.cc.pin.util.CreditCardUtil;
import net.homecredit.homerselect.ws.card.cardinfo.v2.CardDto;
import net.homecredit.homerselect.ws.card.cardinfo.v2.GetCardsResponse;
import net.homecredit.homerselect.ws.card.pinchange.v1.GenerateSessionKeyResponse;
import net.homecredit.homerselect.ws.card.pinchange.v1.SetPinByPcidResponse;
import net.homecredit.party_ws._2016_1.CustomerPersonSearchResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PINService
{
    private static final Logger logger = LogManager.getLogger();

    private WSService wsService;
    private CreditCardUtil util;

    @Autowired
    public PINService(WSService wsService, CreditCardUtil util)
    {
        this.wsService = wsService;
        this.util = util;
    }

    public Response generateSession(String cuid, String connectionId)
    {
        Response response = new Response();

        try
        {
            //GenerateSessionKey - SOAP
            GenerateSessionKeyResponse generateSessionKeyResponse = wsService.generateSessionKey(ConstantPreference.APPLICATION_ID_IVR, connectionId);

            if (null != generateSessionKeyResponse)
            {
                if (generateSessionKeyResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.RESPONSE_SUCCESS))
                {
                    //CustomerPersonGet - SOAP
                    CustomerPersonSearchResponse customerPersonSearchResponse = wsService.getDOB(cuid);

                    if (null != customerPersonSearchResponse && null != customerPersonSearchResponse.getItems() && !customerPersonSearchResponse.getItems().isEmpty())
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_OK);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
//                        response.setPublicKey(generateSessionKeyResponse.getPublicKey());
//                        response.setDOB(util.parseDate(customerPersonSearchResponse.getItems().get(0).getPerson().getBirthDate()));
                    }
                    else
                    {
                        logger.debug("Failed get data from CustomerPersonGet");

                        response.setStatus(ConstantPreference.RESPONSE_CODE_SERVICE_UNAVAILABLE);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SERVICE_UNAVAILABLE);
                    }
                }
                else if (generateSessionKeyResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SESSION_RESPONSE_CONNECTION_ID_ALREADY_USED))
                {
                    response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                    response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SESSION_CONNECTION_ID_ALREADY_USED);
                }
                else if (generateSessionKeyResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SESSION_RESPONSE_HSM_COMMUNICATION_FAILED))
                {
                    response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                    response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SESSION_HSM_COMMUNICATION_FAILED);
                }
                else if (generateSessionKeyResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SESSION_RESPONSE_INVALID_APPLICATION_ID))
                {
                    response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                    response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SESSION_INVALID_APPLICATION_ID);
                }
                else if (generateSessionKeyResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SESSION_RESPONSE_OTHER_ERROR))
                {
                    response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                    response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SESSION_OTHER_ERROR);
                }
            }
            else
            {
                logger.debug("Failed get data from GenerateSessionKey");

                response.setStatus(ConstantPreference.RESPONSE_CODE_SERVICE_UNAVAILABLE);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SERVICE_UNAVAILABLE);
            }
        }
        catch (Exception e)
        {
            logger.error("Error when generateSession: {}", e);

            response.setStatus(ConstantPreference.RESPONSE_CODE_SERVICE_UNAVAILABLE);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SERVICE_UNAVAILABLE);
        }

        return response;
    }

    public Response setPin(String cuid, String connectionId, String encryptedPinBlock, String sessionZpkKey)
    {
        Response response = new Response();

        try
        {
            //GetCards - SOAP
            GetCardsResponse cardsResponse = wsService.getListOfCards(cuid);

            if (null != cardsResponse && null != cardsResponse.getResultCode()
                    && cardsResponse.getResultCode().toString().equalsIgnoreCase(ConstantPreference.RESPONSE_SUCCESS)
                    && !cardsResponse.getCards().isEmpty())
            {
                long pcid = 0;

                for (CardDto cardDto : cardsResponse.getCards())
                {
                    if (cardDto.getStatus().value().equalsIgnoreCase(ConstantPreference.CARD_STATUS_ACTIVE))
                    {
                        pcid = cardDto.getPcid();
                        break;
                    }
                }

                //SetPinByPcid - SOAP
                SetPinByPcidResponse setPinByPcidResponse = wsService.setPin(ConstantPreference.APPLICATION_ID_IVR, connectionId, pcid, encryptedPinBlock, sessionZpkKey);

                if (null != setPinByPcidResponse && null != setPinByPcidResponse.getResultCode())
                {
                    if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.RESPONSE_SUCCESS))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_OK);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_OK);
                    }
                    else if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SET_PIN_CARD_NOT_FOUND))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SET_PIN_CARD_NOT_FOUND);
                    }
                    else if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SET_PIN_CONNECTION_ID_NOT_FOUND))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SET_PIN_CONNECTION_ID_NOT_FOUND);
                    }
                    else if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SET_PIN_INVALID_APPLICATION_ID))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SET_PIN_INVALID_APPLICATION_ID);
                    }
                    else if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SET_PIN_NO_FREE_SLOT_IN_HSM_FOR_APPLICATION_ID))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SET_PIN_NO_FREE_SLOT_IN_HSM_FOR_APPLICATION_ID);
                    }
                    else if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SET_PIN_KEY_LOADING_FAILED))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SET_PIN_KEY_LOADING_FAILED);
                    }
                    else if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SET_PIN_KEY_IMPORT_FAILED))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SET_PIN_KEY_IMPORT_FAILED);
                    }
                    else if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SET_PIN_HSM_TRANSLATION_FAILED))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SET_PIN_HSM_TRANSLATION_FAILED);
                    }
                    else if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SET_PIN_PAN_DECIPHERING_FAILED))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SET_PIN_PAN_DECIPHERING_FAILED);
                    }
                    else if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SET_PIN_PROCESSOR_COMMUNICATION_FAILED))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SET_PIN_PROCESSOR_COMMUNICATION_FAILED);
                    }
                    else if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SET_PIN_WRONG_PIN_LENGTH))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SET_PIN_WRONG_PIN_LENGTH);
                    }
                    else if (setPinByPcidResponse.getResultCode().value().equalsIgnoreCase(ConstantPreference.SET_PIN_OTHER_ERROR))
                    {
                        response.setStatus(ConstantPreference.RESPONSE_CODE_ERROR);
                        response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SET_PIN_OTHER_ERROR);
                    }
                }
                else
                {
                    logger.debug("Failed get data from SetPinByPcid");

                    response.setStatus(ConstantPreference.RESPONSE_CODE_SERVICE_UNAVAILABLE);
                    response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SERVICE_UNAVAILABLE);
                }
            }
            else
            {
                logger.debug("Failed get data from GetCards");

                response.setStatus(ConstantPreference.RESPONSE_CODE_SERVICE_UNAVAILABLE);
                response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SERVICE_UNAVAILABLE);
            }
        }
        catch (Exception e)
        {
            logger.error("Error when setPin: {}", e);

            response.setStatus(ConstantPreference.RESPONSE_CODE_SERVICE_UNAVAILABLE);
            response.setMessage(ConstantPreference.RESPONSE_MESSAGE_SERVICE_UNAVAILABLE);
        }

        return response;
    }
}