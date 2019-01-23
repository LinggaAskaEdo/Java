package id.co.homecredit.cc.pin.service;

import net.homecredit.party_ws._2016_1.CustomerPersonSearchResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService
{
    private static final Logger logger = LogManager.getLogger();

    private WSService wsService;

    @Autowired
    public AccountService(WSService wsService)
    {
        this.wsService = wsService;
    }

    public String getCuid(String userId)
    {
        String cuid = null;

        try
        {
            logger.debug("userId: {}", userId);

            //CustomerPersonSearch - SOAP
            CustomerPersonSearchResponse customerPersonSearchResponse = wsService.getCuid(userId);

            if (null != customerPersonSearchResponse && null != customerPersonSearchResponse.getItems() && !customerPersonSearchResponse.getItems().isEmpty()
                    && null != customerPersonSearchResponse.getItems().get(0).getExternalId()
                    && customerPersonSearchResponse.getItems().get(0).getExternalId() > 0)
            {
                cuid = String.valueOf(customerPersonSearchResponse.getItems().get(0).getExternalId());
            }
        }
        catch (Exception e)
        {
            logger.error("Error when getCuid: {}", e);
        }

        return cuid;
    }
}