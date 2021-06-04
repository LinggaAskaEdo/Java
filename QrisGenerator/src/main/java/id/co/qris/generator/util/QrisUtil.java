package id.co.qris.generator.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import id.co.qris.generator.preference.ConfigPreference;
import id.co.qris.generator.preference.ConstantPreference;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.velocity.app.VelocityEngine;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
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

    public String decryptKey(String key)
    {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("macc");
        encryptor.setAlgorithm("PBEWithMD5AndDES");

        return encryptor.decrypt(key);
    }

    public List<String> getQrisData(String type, String sourcePath)
    {
        List<String> result = new ArrayList<>();
        File file = new File(sourcePath);

        if (type.equalsIgnoreCase(ConstantPreference.TYPE_WECHAT))
        {
            getDataWeChat(file, result);
        }
        else
        {
            getDataCIMB(file, result);
        }

        return result;
    }

    private void getDataWeChat(File file, List<String> result)
    {
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
    }

    private void getDataCIMB(File file, List<String> result)
    {
        try (Scanner scanner = new Scanner(file))
        {
            while (scanner.hasNextLine())
                result.add(scanner.nextLine());
        }
        catch (Exception e)
        {
            //Do nothing
        }
    }

    public boolean generatePdf(List<String> qrisDataList, String destPath, String type, String size)
    {
        boolean result = false;

        File directory = new File(destPath);

        String writeFilePath = directory.getAbsolutePath() + File.separator + preference.baseNameResult + type.toUpperCase() + "-" + size.toUpperCase() + "-" + generateDateTime() + ".pdf";

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
                model.put("title", getTitle(type, qrisData));
                model.put("nmid", getNmid(type, qrisData));
                model.put("qris", generateQris(type, qrisData));

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, preference.basePathTemplate, "UTF-8", model).getBytes());
                streams.add(byteArrayInputStream);
            }

//            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(writeFilePath)))
//            {
//                InputStream everything = new SequenceInputStream(Collections.enumeration(streams));
//
//                ConverterProperties converterProperties = new ConverterProperties();
//                converterProperties.setBaseUri(destPath);
//
//                HtmlConverter.convertToPdf(everything, fileOutputStream, converterProperties);
//            }
//            catch (Exception e)
//            {
//                //Do nothing
//            }

            try (PdfWriter pdfWriter = new PdfWriter(writeFilePath); PdfDocument pdfDocument = new PdfDocument(pdfWriter); InputStream everything = new SequenceInputStream(Collections.enumeration(streams)))
            {
                pdfDocument.setTagged();

                PageSize pageSize;

                if (size.equalsIgnoreCase(ConstantPreference.SIZE_A5))
                    pageSize = PageSize.A5;
                else if (size.equalsIgnoreCase(ConstantPreference.SIZE_A6))
                    pageSize = PageSize.A6;
                else
                    pageSize = PageSize.A4;

                pdfDocument.setDefaultPageSize(pageSize);

                ConverterProperties properties = new ConverterProperties();
                properties.setBaseUri(destPath);

                MediaDeviceDescription mediaDeviceDescription = new MediaDeviceDescription(MediaType.SCREEN);
                mediaDeviceDescription.setWidth(pageSize.getWidth());
                properties.setMediaDeviceDescription(mediaDeviceDescription);

                HtmlConverter.convertToPdf(everything, pdfDocument, properties);
            }
            catch (Exception e)
            {
                //Do nothing
            }

            qrisDataList.clear();
            streams.clear();
            result = true;
        }

        return result;
    }

    private String generateDateTime()
    {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");

        return dateFormat.format(date);
    }

    private String getTitle(String type, String data)
    {
        String title = "";

        try
        {
            if (type.equalsIgnoreCase(ConstantPreference.TYPE_WECHAT))
                title = StringUtils.substringBetween(data, "5802ID", "60").substring(4).trim();
            else
                title = StringUtils.substring(data, 98, 123).trim();
        }
        catch (Exception e)
        {
            //Do nothing
        }

        return title;
    }

    private String getNmid(String type, String data)
    {
        String nmid = "";

        try
        {
            if (type.equalsIgnoreCase(ConstantPreference.TYPE_WECHAT))
                nmid = StringUtils.substringBetween(data, "0215", "5204");
            else
                nmid = StringUtils.substring(data, 180, 195).trim();
        }
        catch (Exception e)
        {
            //Do nothing
        }

        return nmid;
    }

    private String generateQris(String type, String data)
    {
        String result = null;

        try
        {
            if (type.equalsIgnoreCase(ConstantPreference.TYPE_CIMB))
                data = generateQrisCIMB(data);

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

    private String generateQrisCIMB(String data)
    {
        String sdpfi = StringUtils.substring(data, 0, 2).trim();
        String sdpoi = StringUtils.substring(data, 2, 4).trim();
        String sdbdom = StringUtils.substring(data, 4, 36).trim();
        String sdmcpn = StringUtils.substring(data, 36, 55).trim();
        String sdmid = StringUtils.substring(data, 55, 70).trim();
        String sdmcia = StringUtils.substring(data, 70, 73).trim();
        String sdmcp = StringUtils.substring(data, 73, 92).trim();
        String sdmcc = StringUtils.substring(data, 92, 96).trim();
        String sdmccc = StringUtils.substring(data, 96, 98).trim();
        String sdmcnm = StringUtils.substring(data, 98, 123).trim();
        String sdmccy = StringUtils.substring(data, 123, 138).trim();
        String sdmcpc = StringUtils.substring(data, 138, 148).trim();
        String sdndom = StringUtils.substring(data, 148, 180).trim();
        String sdnmid = StringUtils.substring(data, 180, 195).trim();
        String sdtipt = StringUtils.substring(data, 195, 197).trim();

        StringBuilder result = new StringBuilder();

        //TAG-00
        result.append("00").append(String.format(ConstantPreference.FORMAT_LENGTH, sdpfi.length())).append(sdpfi);

        //TAG-01

        result.append("01").append(String.format(ConstantPreference.FORMAT_LENGTH, sdpoi.length())).append(sdpoi);

        //TAG-04
        if (!sdmcp.isEmpty())
            result.append("04").append("15").append(StringUtils.substring(sdmcp, 0, 15));

        //TAG-26
        StringBuilder tag26 = new StringBuilder();
        tag26.append("00").append("19").append(StringUtils.substring(sdbdom, 0, 19));
        tag26.append("01").append("18").append(StringUtils.substring(sdmcpn, 0, 18));
        tag26.append("02").append("15").append(StringUtils.substring(sdmid, 0, 15));
        tag26.append("03").append("03").append(StringUtils.substring(sdmcia, 0, 3));

        result.append("26").append(tag26.length()).append(tag26);

        //TAG-51
        if (!sdnmid.isEmpty())
        {
            StringBuilder tag51 = new StringBuilder();
            tag51.append("00").append(String.format(ConstantPreference.FORMAT_LENGTH, sdndom.length())).append(sdndom);
            tag51.append("02").append(String.format(ConstantPreference.FORMAT_LENGTH, sdnmid.length())).append(sdnmid);
            tag51.append("03").append("03").append(StringUtils.substring(sdmcia, 0, 3));

            result.append("51").append(tag51.length()).append(tag51);
        }

        //TAG-52
        result.append("52").append("04").append(sdmcc);

        //TAG-53
        result.append("53").append("03").append(sdmccc.equalsIgnoreCase("ID") ? "360" : "");

        //TAG-55
        if (!(sdtipt.equalsIgnoreCase("00") || sdtipt.equalsIgnoreCase("02")))
            result.append("55").append(String.format(ConstantPreference.FORMAT_LENGTH, sdtipt.length())).append(sdtipt);

        //TAG-58
        result.append("58").append("02").append(sdmccc);

        //TAG-59
        result.append("59").append(String.format(ConstantPreference.FORMAT_LENGTH, sdmcnm.length())).append(sdmcnm);

        //TAG-60
        result.append("60").append(String.format(ConstantPreference.FORMAT_LENGTH, sdmccy.length())).append(sdmccy);

        //TAG-61
        result.append("61").append(String.format(ConstantPreference.FORMAT_LENGTH, sdmcpc.length())).append(sdmcpc);

        //TAG-63
        result.append("63").append("04");
        result.append(generateCRC(result.toString().getBytes()));

        return result.toString();
    }

    private String generateCRC(byte[] data)
    {
        int crc = 0xFFFF;          // initial value
        int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12)

        for (byte b : data)
        {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b   >> (7-i) & 1) == 1);
                boolean c15 = ((crc >> 15    & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
            }
        }

        crc &= 0xffff;

        return Integer.toHexString(crc);
    }
}
