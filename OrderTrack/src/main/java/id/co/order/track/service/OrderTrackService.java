package id.co.order.track.service;

import com.google.gson.Gson;
import id.co.order.track.model.Request;
import id.co.order.track.util.OrderTrackUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderTrackService
{
    private static final Logger logger = LogManager.getLogger();

    private final OrderTrackUtil util;

    @Autowired
    public OrderTrackService(OrderTrackUtil util)
    {
        this.util = util;
    }

    public void processData(String token, String path)
    {
        logger.debug("token: {}, path: {}", token, path);

        List<Request> requestList = util.readData(path);

        if (!requestList.isEmpty())
        {
            for (Request request : requestList)
            {
                try
                {
                    String payload = new Gson().toJson(request);

                    logger.debug("Request body: {}",payload);

                    util.postMarketplace(token, payload);

                    Thread.sleep(3000);
                }
                catch (Exception e)
                {
                    //Do nothing
                }
            }
        }
        else
        {
            logger.debug("Request is empty !!!");
        }
    }
}
