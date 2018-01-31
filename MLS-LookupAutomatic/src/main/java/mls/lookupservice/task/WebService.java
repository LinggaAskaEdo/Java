package mls.lookupservice.task;

import com.google.gson.Gson;
import mls.lookupservice.constant.ServiceConstant;
import mls.lookupservice.dao.MlsDao;
import mls.lookupservice.function.WebFunction;
import mls.lookupservice.model.CellDB;
import mls.lookupservice.model.Response;
import mls.lookupservice.model.ResponseMessage;
import mls.lookupservice.preference.Preference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Lingga on 23/03/17.
 */

@RestController
public class WebService
{
    private final Logger log = LoggerFactory.getLogger(WebService.class);

    private final MlsDao mlsDao;

    private final WebFunction webFunction;

    @Autowired
    WebService(MlsDao mlsDao, WebFunction webFunction)
    {
        this.mlsDao = mlsDao;
        this.webFunction = webFunction;
    }

    @RequestMapping(value = ServiceConstant.SERVICE_NAME + "/" + ServiceConstant.GET_LOCATION, method = RequestMethod.POST)
    String calculatePosition(@RequestBody CellDB cellDB)
    {
        if (Preference.STATUS.equalsIgnoreCase(Preference.CELL_DB_STATUS_ENABLE))
        {
            log.info("PRIMARY CELL_DB status: {}", Preference.STATUS);
        }
        else
        {
            log.info("PRIMARY CELL_DB status: {}, using SECONDARY CELL_DB", Preference.STATUS);
        }

        List<CellDB> cellDBList;
        Response response = new Response();
        ResponseMessage responseMessage = new ResponseMessage();

        try
        {
            String errorMessage = webFunction.checkCellDB(cellDB);

            if (errorMessage == null)
            {
                String radio;

                if (cellDB.getCell() > 65535)
                {
                    radio = WebFunction.CONSTANT_WCDMA;
                }
                else
                {
                    radio = WebFunction.CONSTANT_GSM;
                }

                log.info("calculatePosition ~ radio: {}, mcc: {}, net: {}, area: {}, cell: {}", radio, cellDB.getMcc(), cellDB.getNet(), cellDB.getArea(), cellDB.getCell());

                cellDBList = mlsDao.getLocation(radio, cellDB.getMcc(), cellDB.getNet(), cellDB.getArea(), cellDB.getCell());

                if (cellDBList.size() > 0)
                {
                    log.debug("cellDBList: {}", cellDBList.get(0).toString());

                    responseMessage.setStatus(1);
                    responseMessage.setMessage("");

                    response.setId(cellDBList.get(0).getId());
                    response.setRadio(cellDBList.get(0).getRadio());
                    response.setMcc(cellDBList.get(0).getMcc());
                    response.setMnc(cellDBList.get(0).getNet());
                    response.setLac(cellDBList.get(0).getArea());
                    response.setCell_id(cellDBList.get(0).getCell());

                    String cellRef = String.valueOf(cellDBList.get(0).getMcc()) + "." + String.valueOf(cellDBList.get(0).getNet()) + "." +
                            String.valueOf(cellDBList.get(0).getArea()) + "." + String.valueOf(cellDBList.get(0).getCell());
                    response.setCell_ref(cellRef);

                    response.setUnit(cellDBList.get(0).getUnit());
                    response.setLongitude(cellDBList.get(0).getLon());
                    response.setLatitude(cellDBList.get(0).getLat());
                    response.setRanges(cellDBList.get(0).getRanges());
                    response.setSamples(cellDBList.get(0).getSamples());
                    response.setChangeable(cellDBList.get(0).isChangeable());
                    response.setCreated(cellDBList.get(0).getCreated());
                    response.setUpdated(cellDBList.get(0).getUpdated());
                    response.setAverage_signal(cellDBList.get(0).getAverage_signal());

                    responseMessage.setResponse(response);

                    log.info("response ~ {}", responseMessage.toString());
                }
                else
                {
                    log.debug("Error message: {}", WebFunction.EMPTY_RESULT);

                    ResponseMessage message = new ResponseMessage(0, WebFunction.EMPTY_RESULT);
                    return new Gson().toJson(message);
                }
            }
            else
            {
                log.debug("Error message: {}", errorMessage);

                ResponseMessage message = new ResponseMessage(0, errorMessage);
                return new Gson().toJson(message);
            }
        }
        catch (Exception e)
        {
            log.error("Exception calculatePosition: {}", e.toString());
        }

        return new Gson().toJson(responseMessage);
    }

    @ExceptionHandler(Exception.class)
    public void hadleExceptions(Exception e)
    {
        log.error("Error message: {}", e.getMessage());
    }
}