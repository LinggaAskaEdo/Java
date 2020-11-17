package id.co.qris.generator.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import id.co.qris.generator.preference.ConfigPreference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class QrisUtil
{
    private final ConfigPreference preference;
    private final VelocityEngine velocityEngine;

    @Autowired
    public QrisUtil(ConfigPreference preference, VelocityEngine velocityEngine)
    {
        this.preference = preference;
        this.velocityEngine = velocityEngine;
    }

    public List<String> getQrisData(String sourcePath)
    {
        List<String> result = new ArrayList<>();
        File file = new File(sourcePath);

        try (FileInputStream fileInputStream = new FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream))
        {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet)
            {
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();

                    if (row.getRowNum() >= 0)
                    {
                        //To filter column headings
                        if (cell.getColumnIndex() == 0)
                        {
                            result.add(cell.getStringCellValue());
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            //Do nothing
        }

        return result;
    }

    public boolean generatePdf(List<String> qrisDataList, String destPath)
    {
        boolean result = false;

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri(destPath);

        File directory = new File(destPath);

        String writeFilePath = directory.getAbsolutePath() + File.separator + preference.baseNameResult + generateDate() + ".pdf";

        boolean isDirectoryCreated = directory.exists();

        if (!isDirectoryCreated)
        {
            isDirectoryCreated = directory.mkdirs();
        }

        if (isDirectoryCreated)
        {
            List<ByteArrayInputStream> streams = new ArrayList<>();

            for (String qrisData : qrisDataList)
            {
                Map<String, Object> model = new HashMap<>();
                model.put("background", preference.basePathBackground);
                model.put("title", getTitle(qrisData));
                model.put("nmid", getNmid(qrisData));
                model.put("qris", generateQris(qrisData));

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, preference.basePathTemplate, "UTF-8", model).getBytes());
                streams.add(byteArrayInputStream);
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(writeFilePath)))
            {
                InputStream everything = new SequenceInputStream(Collections.enumeration(streams));

                HtmlConverter.convertToPdf(everything, fileOutputStream, converterProperties);
            }
            catch (Exception e)
            {
                //Do nothing
            }

            result = true;
        }

        return result;
    }

    private String getTitle(String data)
    {
        return "AAA";
    }

    private String getNmid(String data)
    {
        return "BBB";
    }

    private String generateQris(String data)
    {
        String result = null;

        try
        {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 350, 350);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            result = Base64.getEncoder().encodeToString(pngOutputStream.toByteArray());
        }
        catch (Exception e)
        {
            //Do nothing
        }

        return result;
    }

    private String generateDate()
    {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        return dateFormat.format(date);
    }
}
