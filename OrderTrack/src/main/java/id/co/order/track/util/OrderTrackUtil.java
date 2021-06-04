package id.co.order.track.util;

import com.google.gson.Gson;
import id.co.order.track.model.Request;
import id.co.order.track.model.Transaction;
import id.co.order.track.preference.ConfigPreference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class OrderTrackUtil
{
    private static final Logger logger = LogManager.getLogger();

    private final ConfigPreference preference;
    private final RestTemplate restTemplate;

    @Autowired
    public OrderTrackUtil(ConfigPreference preference, RestTemplate restTemplate)
    {
        this.preference = preference;
        this.restTemplate = restTemplate;
    }

    public List<Request> readData(String path)
    {
        List<Request> requestList = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(path); XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream))
        {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet)
            {
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    generateRequest(requestList, row, cell);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error when generateRequest: ", e);
        }

        return requestList;
    }

    private void generateRequest(List<Request> requestList, Row row, Cell cell)
    {
        if (row.getRowNum() >= 0)
        {
            //To filter column headings
            if (cell.getColumnIndex() == 0 && null != row.getCell(0) && !row.getCell(0).toString().isEmpty())
            {
                Request request = new Request();
                request.setPaymentType(row.getCell(0).toString());
                request.setTransactionId(row.getCell(1).toString());
                request.setOrderId(row.getCell(2).toString());
                request.setCreatedAt(row.getCell(3).toString());
                request.setInformationCommodity(row.getCell(4).toString());
                request.setAirWillBillNumber(null == row.getCell(5) ? "" : row.getCell(5).toString());
                request.setExpeditionName(null == row.getCell(6) ? "" : row.getCell(6).toString());
                request.setReceiverName(row.getCell(7).toString());
                request.setReceiverRelation(row.getCell(8).toString());
                request.setDeliveryAddress(row.getCell(9).toString());
                request.setStatusType(row.getCell(10).toString());
                request.setStatus(row.getCell(11).toString());

                requestList.add(request);
            }
        }
    }

    public void postMarketplace(String token, String payload)
    {
        try
        {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("Accept-Charset", "utf-8");
            headers.set("Authorization", "Bearer " + token);

            ResponseEntity<Transaction> response = restTemplate.exchange(preference.restMarketplaceUrl, HttpMethod.POST, new HttpEntity<>(payload, headers), Transaction.class);

            logger.debug("Response status: {}", response.getStatusCode().value());
            logger.debug("Response body: {}", new Gson().toJson(response.getBody()));
        }
        catch (Exception e)
        {
            logger.debug("Error when postMarketplace", e);
        }
    }
}