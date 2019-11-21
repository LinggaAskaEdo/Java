package com.pdf.parser.util;

import com.google.gson.Gson;
import com.pdf.parser.dao.ParserDAO;
import com.pdf.parser.form.Main;
import com.pdf.parser.model.Content;
import com.pdf.parser.model.Data;
import com.pdf.parser.model.Transaction;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;

public class GeneratorUtil
{
    private int totalDataValueInt = 0;
    private String totalDataType = "";
    private String saranaNumber = "";

    public boolean processExcel(String number)
    {
        boolean result = false;

        ParserDAO parserDAO = new ParserDAO();

        System.out.println("Number: " + number);

        Transaction transaction = parserDAO.getTransactionByNumber(number);

        if (null != transaction)
        {
            Content content = new Gson().fromJson(transaction.getBodyContent(), Content.class);

            File jarPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            String parentPath = jarPath.getParentFile().getAbsolutePath();

            System.out.println("propertiesPath: " + parentPath);

            try (XSSFWorkbook workbook = new XSSFWorkbook())
            {
                // Create a Sheet
                XSSFSheet sheet = workbook.createSheet(transaction.getNoContent());

                boolean statusRow = true;
                int rowNum = 2;

                while (statusRow)
                {
                    Row row = sheet.createRow(rowNum);

                    System.out.println("rowNum: " + rowNum);

                    if (rowNum == 2)
                        row.createCell(6).setCellValue(content.getNomorPengajuan());
                    else if (rowNum == 3)
                        row.createCell(6).setCellValue(content.getNomorTanggalPendaftaran());
                    else if (rowNum == 4)
                    {
                        Cell petiKemas = row.createCell(6);
                        petiKemas.setCellValue(content.getNomorPetiKemas());
                        petiKemas.setCellStyle(generateBoldBorderCenterStyle(workbook, 1,true, true, true,false));

                        Cell seal = row.createCell(7);
                        seal.setCellValue("SEAL");
                        seal.setCellStyle(generateBoldBorderCenterStyle(workbook, 1,false, true, true,false));

                        Cell petiKemasOther = row.createCell(8);
                        petiKemasOther.setCellValue("                                  ");
                        petiKemasOther.setCellStyle(generateBoldBorderCenterStyle(workbook, 1,true, true, true,false));
                    }
                    else if (rowNum == 5)
                        row.createCell(6).setCellValue(content.getUkuranPetiKemas());
                    else if (rowNum == 6)
                    {
                        row.createCell(6).setCellValue(content.getTempatStuffing());
                        row.createCell(10).setCellValue(content.getTanggalStuffing());
                    }
                    else if (rowNum == 7)
                        row.createCell(10).setCellValue(content.getKonsolidasi().getPabeanAsal());
                    else if (rowNum == 8)
                        row.createCell(10).setCellValue(content.getKonsolidasi().getPabeanEkspor());
                    else if (rowNum == 9)
                    {
                        Cell negaraTujuan = row.createCell(10);
                        negaraTujuan.setCellValue(content.getKonsolidasi().getNegaraTujuan());
                        negaraTujuan.setCellStyle(generateBoldBorderCenterStyle(workbook,0,false,false,true,true));
                    }
                    else if (rowNum == 10)
                    {
                        String[] splitSarana = content.getKonsolidasi().getSarana().split("\\s+");
                        saranaNumber = splitSarana[splitSarana.length - 1];

                        Cell sarana = row.createCell(10);
                        sarana.setCellValue(content.getKonsolidasi().getSarana().replace(saranaNumber, ""));
                        sarana.setCellStyle(generateBoldBorderCenterStyle(workbook,0,false,false,true,true));
                    }
                    else if (rowNum == 11)
                    {
                        Cell noFlight = row.createCell(10);
                        noFlight.setCellValue(saranaNumber + "-" + content.getKonsolidasi().getNoFlight());
                        noFlight.setCellStyle(generateBoldBorderCenterStyle(workbook,0,false,false,true,true));
                    }
                    else if (rowNum == 12)
                    {
                        row.createCell(9).setCellValue("Booking No:");

                        //set value booking number
                    }
                    else if (rowNum == 13)
                    {
                        row.createCell(9).setCellValue("Pelabuhan Bongkar:");

                        //set value pelabuhan bongkar
                    }
                    else if (rowNum == 15)
                    {
                        Cell noPeb = row.createCell(3);
                        noPeb.setCellValue("NO PEB");
                        noPeb.setCellStyle(generateBoldBorderCenterStyle(workbook, 0, false,true,true,false));

                        Cell tglPeb = row.createCell(4);
                        tglPeb.setCellValue("TGL PEB");
                        tglPeb.setCellStyle(generateBoldBorderCenterStyle(workbook,0,false,true,true,false));

                        Cell noNpe = row.createCell(6);
                        noNpe.setCellValue("NO NPE");
                        noNpe.setCellStyle(generateBoldBorderCenterStyle(workbook,0,false, true,true,false));

                        Cell tglNpe = row.createCell(7);
                        tglNpe.setCellValue("TGL NPE");
                        tglNpe.setCellStyle(generateBoldBorderCenterStyle(workbook,0,false,true,true,false));
                    }
                    else if (rowNum == 16)
                    {
                        int noData = 1;

                        for (Data data : content.getDataList())
                        {
                            Row rowData = sheet.createRow(rowNum);
                            String[] pebs = data.getNomorPEB().split("/");
                            String[] totalKeterangan = data.getKeterangan().split("\\s+");
                            int lengthTotalKeterangan = totalKeterangan.length;

                            rowData.createCell(1).setCellValue(noData);
                            rowData.createCell(2).setCellValue(pebs[0] + " /");

                            Cell dataNoPeb = rowData.createCell(3);
                            dataNoPeb.setCellValue(pebs[1]);
                            dataNoPeb.setCellStyle(generateBoldBorderCenterStyle(workbook, 1, false, false,false,false));

                            Cell dataTglPeb = rowData.createCell(4);
                            dataTglPeb.setCellValue(data.getTanggalPEB());
                            dataTglPeb.setCellStyle(generateBoldBorderCenterStyle(workbook,1,false,false,false,false));

                            Cell dataNoNpe = rowData.createCell(6);
                            dataNoNpe.setCellValue(data.getNomorNPE());
                            dataNoNpe.setCellStyle(generateBoldBorderCenterStyle(workbook,1,false,false, false,false));

                            Cell dataTglNpe = rowData.createCell(7);
                            dataTglNpe.setCellValue(data.getTanggalNPE());
                            dataTglNpe.setCellStyle(generateBoldBorderCenterStyle(workbook,1,false,false, false,false));

                            rowData.createCell(9).setCellValue(data.getKeterangan());

                            Cell dataTotalKeterangan = rowData.createCell(11);
                            String totalDataValueString = totalKeterangan[lengthTotalKeterangan - 2];
                            dataTotalKeterangan.setCellValue(totalDataValueString);
                            dataTotalKeterangan.setCellStyle(generateBoldBorderCenterStyle(workbook,2,false,false, false,false));

                            totalDataValueInt = totalDataValueInt + Integer.parseInt(totalDataValueString);

                            totalDataType = totalKeterangan[lengthTotalKeterangan - 1] + "N";
                            rowData.createCell(12).setCellValue(totalDataType);

                            noData++;
                            rowNum++;
                        }
                    }
                    else if (rowNum == (16 + content.getDataList().size() + 2))
                    {
                        Cell totalData = row.createCell(10);
                        totalData.setCellValue("TOTAL");
                        totalData.setCellStyle(generateBoldBorderCenterStyle(workbook,0,false,true,true,false));

                        Cell totalDataValue = row.createCell(11);
                        totalDataValue.setCellValue(totalDataValueInt);
                        totalDataValue.setCellStyle(generateBoldBorderCenterStyle(workbook,2,false,true,true,false));

                        row.createCell(12).setCellValue(totalDataType);
                    }
                    else if (rowNum == (16 + content.getDataList().size()) + 3)
                        statusRow = false;

                    rowNum++;
                }

                // Resize all columns to fit the content size
                for (int i = 0; i < 12; i++)
                {
                    sheet.autoSizeColumn(i);
                }

                // All done
                FileOutputStream fileOut = new FileOutputStream(parentPath + "/Report-" + transaction.getNoContent() +".xlsx");
                workbook.write(fileOut);
                fileOut.close();

                result = true;
            }
            catch (Exception e)
            {
                System.out.print("Error when write to excel : ");
                e.printStackTrace();
            }
        }

        return result;
    }

    private XSSFCellStyle generateBoldBorderCenterStyle(XSSFWorkbook workbook, int align, boolean isBorder, boolean isBold, boolean isUpperCase, boolean isRed)
    {
        // Create Cell Style - BOLD
        XSSFCellStyle style = workbook.createCellStyle();

        if (align == 0)
            style.setAlignment(HorizontalAlignment.LEFT);
        else if (align == 1)
            style.setAlignment(HorizontalAlignment.CENTER);
        else if (align == 2)
            style.setAlignment(HorizontalAlignment.RIGHT);

        XSSFFont font = workbook.createFont();

        if (isBold)
            font.setBold(true);

        if (isUpperCase)
            font.setFontHeightInPoints((short) 12);

        if (isRed)
        {
            XSSFColor xssfColor = new XSSFColor(new java.awt.Color(255, 24, 24), index -> new byte[0]);
            font.setColor(xssfColor);
        }

        style.setFont(font);

        if (isBorder)
        {
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
        }

        return style;
    }
}