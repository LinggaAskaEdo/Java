package com.sql2o.hexagonal.adapter.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.sql2o.hexagonal.application.model.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.text.*;
import java.util.*;

@RestController
public class PdfController
{
    private static final Logger logger = LogManager.getLogger();

    private static final String CONTRACT_NUMBER = "400001064";
    private static final String BASE_URI = "/Users/lingga.putra/Downloads/MiniCFA";
    private static final String OUTPUT_PATH = BASE_URI + File.separator + "result";
    private static final String TEMPLATE_PATH = BASE_URI + File.separator + "template" + File.separator + "MiniCFA.vm";

    private final VelocityEngine velocityEngine;

    @Autowired
    public PdfController(VelocityEngine velocityEngine)
    {
        this.velocityEngine = velocityEngine;
    }

    @GetMapping("/pdf/test")
    public ResponseEntity<Response> checkBalances()
    {
        Response response = new Response();

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String folderDate = dateFormat.format(date);

//        final ICssApplier customImageCssApplier = new BlockCssApplier() {
//            @Override
//            public void apply(ProcessorContext context, IStylesContainer stylesContainer, ITagWorker tagWorker) {
//                super.apply(context, stylesContainer, tagWorker);
//                if (tagWorker.getElementResult() instanceof Image) {
//                    Image img = (Image) tagWorker.getElementResult();
//                    if (img.getImageWidth() > 500) {
//                        img.setWidth(UnitValue.createPercentValue(100));
//                    }
//                }
//            }
//        };

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri(BASE_URI);
//        converterProperties.setCssApplierFactory()

        String referenceNumber = "PL1234567890";

        Map<String, Object> model = new HashMap<>();
        model.put("creditorsName", "PT Home Credit Indonesia");
        model.put("interestRate", "3.49");
        model.put("transactionDateTime", "04 November 2020, 14:44:00");
        model.put("referenceNumber", referenceNumber);
        model.put("customerName", "Mast Jaya");
        model.put("NIK", "34350110989990");
        model.put("merchantName", "HYPERMART");
        model.put("category", "Kebutuhan Sehari-hari");
        model.put("amount", generateThousandSeparator("50000"));
        model.put("tenor", "3");
        model.put("annualInterestRate", "3.49");
        model.put("monthlyInstallmentAmount", generateThousandSeparator("45000"));
        model.put("dueDate", "28");
        model.put("date", "17 September 2020, 18:54:53");

        File directory = new File(OUTPUT_PATH + File.separator + folderDate + File.separator + CONTRACT_NUMBER + RandomStringUtils.randomNumeric(1));
        String writeFilePath = directory.getAbsolutePath() + File.separator + "PerjanjianPembiayaanMultiguna_" + referenceNumber + ".pdf";

        if (!directory.exists())
        {
            boolean status = directory.mkdirs();

            logger.debug(status ? "{} created" : "{} create failed", directory.getAbsolutePath());
        }
        else
        {
            logger.debug( "{} already exist", directory.getAbsolutePath());
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(writeFilePath));
             ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, TEMPLATE_PATH, "UTF-8", model).getBytes()))
        {
            HtmlConverter.convertToPdf(byteArrayInputStream, fileOutputStream, converterProperties);

            byte[] fileContent = FileUtils.readFileToByteArray(new File(writeFilePath));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            logger.info(encodedString);
        }
        catch (Exception e)
        {
            logger.error("Error create document from VM Template", e);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String generateThousandSeparator(String number)
    {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        formatter.setDecimalFormatSymbols(symbols);

        return formatter.format(new BigInteger(number));
    }
}