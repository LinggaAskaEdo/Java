package mls.lookupservice.task;

import com.google.gson.Gson;
import mls.lookupservice.dao.MlsDao;
import mls.lookupservice.function.WebFunction;
import mls.lookupservice.model.CellDB;
import mls.lookupservice.model.Response;
import mls.lookupservice.model.ResponseMessage;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Lingga on 23/03/17.
 */

public class WebServiceTest
{
    @Test
    public void testCalculatePositionSuccess() throws Exception
    {
        final CellDB cellDB = new CellDB();
        cellDB.setMcc(202);
        cellDB.setNet(1);
        cellDB.setArea(33);
        cellDB.setCell(7652);

        String radio = cellDB.getCell() > 65535 ? WebFunction.CONSTANT_WCDMA : WebFunction.CONSTANT_GSM;

        final CellDB cellDBResult = new CellDB();
        cellDBResult.setId(9937820);
        cellDBResult.setRadio("GSM");
        cellDBResult.setMcc(202);
        cellDBResult.setNet(1);
        cellDBResult.setArea(33);
        cellDBResult.setCell(7652);
        cellDBResult.setUnit("");
        cellDBResult.setLon(23.7795834);
        cellDBResult.setLat(37.9868125);
        cellDBResult.setRanges(10258);
        cellDBResult.setSamples("335");
        cellDBResult.setChangeable(true);
        cellDBResult.setCreated(null);
        cellDBResult.setUpdated(null);
        cellDBResult.setAverage_signal("");

        final List<CellDB> cellDBList = new ArrayList<>();
        cellDBList.add(cellDBResult);

        final Response response = new Response();
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

        final ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(1);
        responseMessage.setMessage("");
        responseMessage.setResponse(response);

        final MlsDao mlsDao = mock(MlsDao.class);
        when(mlsDao.getLocation(radio, cellDB.getMcc(), cellDB.getNet(), cellDB.getArea(), cellDB.getCell())).thenReturn(cellDBList);

        final WebFunction webFunction = mock(WebFunction.class);
        when(webFunction.checkCellDB(cellDB)).thenReturn(null);

        final WebService webService = new WebService(mlsDao, webFunction);
        assertThat(webService.calculatePosition(cellDB)).isEqualTo(new Gson().toJson(responseMessage));
    }

    @Test
    public void testCalculatePositionEmpty() throws Exception
    {
        final CellDB cellDB = new CellDB();
        cellDB.setMcc(202);
        cellDB.setNet(1);
        cellDB.setArea(33);
        cellDB.setCell(7652);

        String radio = cellDB.getCell() > 65535 ? WebFunction.CONSTANT_WCDMA : WebFunction.CONSTANT_GSM;

        final List<CellDB> cellDBList = new ArrayList<>();

        final ResponseMessage responseMessage = new ResponseMessage(0, WebFunction.EMPTY_RESULT);

        final MlsDao mlsDao = mock(MlsDao.class);
        when(mlsDao.getLocation(radio, cellDB.getMcc(), cellDB.getNet(), cellDB.getArea(), cellDB.getCell())).thenReturn(cellDBList);

        final WebFunction webFunction = mock(WebFunction.class);
        when(webFunction.checkCellDB(cellDB)).thenReturn(null);

        final WebService webService = new WebService(mlsDao, webFunction);
        assertThat(webService.calculatePosition(cellDB)).isEqualTo(new Gson().toJson(responseMessage));
    }
}