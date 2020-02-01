package com.pdf.parser.util;

import com.google.gson.Gson;
import com.pdf.parser.dao.ParserDAO;
import com.pdf.parser.form.Main;
import com.pdf.parser.model.Content;
import com.pdf.parser.model.Data;
import com.pdf.parser.model.Transaction;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.xssf.usermodel.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;

public class GeneratorUtil
{
    public boolean processExcel(String number)
    {
        boolean result = false;
        int totalDataValueInt = 0;
        String totalDataType = "";
        String saranaNumber = "";
        String tanggalValue = "";

        ParserDAO parserDAO = new ParserDAO();

        System.out.println("Number: " + number);

        Transaction transaction = parserDAO.getTransactionByNumber(number);

        if (null != transaction)
        {
            Content content = new Gson().fromJson(transaction.getBodyContent(), Content.class);

            int sizeOfContent = content.getDataList().size();

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
                    {
                        String[] splits = content.getNomorPengajuan().split("-");

                        Cell splits12 = row.createCell(6);
                        splits12.setCellValue(splits[0] + "-" + splits[1] + "-");
                        splits12.setCellStyle(generateBoldBorderCenterStyle(workbook, 2, 1,false,11,false));

                        row.createCell(7).setCellValue(splits[2]);

                        row.createCell(8).setCellValue("-" + splits[3]);

                        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,8,9));
                    }
                    else if (rowNum == 3)
                    {
                        String[] splits = content.getNomorTanggalPendaftaran().split("/");

                        Cell splits12 = row.createCell(6);
                        splits12.setCellValue(splits[0] + "/" + splits[1] + "/");
                        splits12.setCellStyle(generateBoldBorderCenterStyle(workbook, 2, 1,false,11,false));

                        row.createCell(7).setCellValue(splits[2]);

                        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,7,8));

                        tanggalValue = splits[2];
                        Cell splits3 = row.createCell(9);
                        splits3.setCellValue(tanggalValue);
                        splits3.setCellStyle(generateBoldBorderCenterStyle(workbook, 2, 1,false,11,false));

                        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,9,10));
                    }
                    else if (rowNum == 4)
                    {
                        Cell petiKemas = row.createCell(6);
                        petiKemas.setCellValue(content.getNomorPetiKemas());
                        petiKemas.setCellStyle(generateBoldBorderCenterStyle(workbook, 1,2, true, 12,false));

                        CellRangeAddress regionPetiKemas = new CellRangeAddress(rowNum, rowNum,6,7);

                        PropertyTemplate propertyTemplate = new PropertyTemplate();
                        propertyTemplate.drawBorders(regionPetiKemas, BorderStyle.THIN, IndexedColors.BLACK.getIndex(), BorderExtent.ALL);
                        propertyTemplate.applyBorders(sheet);

                        sheet.addMergedRegion(regionPetiKemas);

                        Cell seal = row.createCell(8);
                        seal.setCellValue("SEAL");
                        seal.setCellStyle(generateBoldBorderCenterStyle(workbook, 1,2, true, 12,false));

                        Cell petiKemasOther = row.createCell(9);
                        petiKemasOther.setCellValue("                                  ");
                        petiKemasOther.setCellStyle(generateBoldBorderCenterStyle(workbook, 1,2, true, 12,false));

                        CellRangeAddress regionPetiKemasOther = new CellRangeAddress(rowNum, rowNum,9,11);

                        PropertyTemplate propertyTemplate2 = new PropertyTemplate();
                        propertyTemplate2.drawBorders(regionPetiKemasOther, BorderStyle.THIN, IndexedColors.BLACK.getIndex(), BorderExtent.ALL);
                        propertyTemplate2.applyBorders(sheet);

                        sheet.addMergedRegion(regionPetiKemasOther);
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
                        negaraTujuan.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,false,12,true));
                    }
                    else if (rowNum == 10)
                    {
                        String[] splitSarana = content.getKonsolidasi().getSarana().split("\\s+");
                        saranaNumber = splitSarana[splitSarana.length - 1];

                        Cell sarana = row.createCell(10);
                        sarana.setCellValue(content.getKonsolidasi().getSarana().replace(saranaNumber, ""));
                        sarana.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,false,12,true));
                    }
                    else if (rowNum == 11)
                    {
                        Cell noFlight = row.createCell(10);
                        noFlight.setCellValue(saranaNumber + "-" + content.getKonsolidasi().getNoFlight());
                        noFlight.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,false,12,true));
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
                        noPeb.setCellStyle(generateBoldBorderCenterStyle(workbook, 0, 2,true,12,false));

                        Cell tglPeb = row.createCell(4);
                        tglPeb.setCellValue("TGL PEB");
                        tglPeb.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,12,false));

                        Cell noNpe = row.createCell(6);
                        noNpe.setCellValue("NO NPE");
                        noNpe.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2, true,12,false));

                        Cell tglNpe = row.createCell(7);
                        tglNpe.setCellValue("TGL NPE");
                        tglNpe.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,12,false));
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
                            dataNoPeb.setCellStyle(generateBoldBorderCenterStyle(workbook, 1, 2, false,11,false));

                            Cell dataTglPeb = rowData.createCell(4);
                            dataTglPeb.setCellValue(data.getTanggalPEB());
                            dataTglPeb.setCellStyle(generateBoldBorderCenterStyle(workbook,1,2,false,11,false));

                            Cell dataNoNpe = rowData.createCell(6);
                            dataNoNpe.setCellValue(data.getNomorNPE());
                            dataNoNpe.setCellStyle(generateBoldBorderCenterStyle(workbook,1,2,false, 11,false));

                            Cell dataTglNpe = rowData.createCell(7);
                            dataTglNpe.setCellValue(data.getTanggalNPE());
                            dataTglNpe.setCellStyle(generateBoldBorderCenterStyle(workbook,1,2,false, 11,false));

                            rowData.createCell(9).setCellValue(data.getKeterangan());

                            Cell dataTotalKeterangan = rowData.createCell(11);
                            String totalDataValueString = totalKeterangan[lengthTotalKeterangan - 2];
                            dataTotalKeterangan.setCellValue(totalDataValueString);
                            dataTotalKeterangan.setCellStyle(generateBoldBorderCenterStyle(workbook,2,2,false, 11,false));

                            totalDataValueInt = totalDataValueInt + Integer.parseInt(totalDataValueString);

                            totalDataType = totalKeterangan[lengthTotalKeterangan - 1] + "N";
                            rowData.createCell(12).setCellValue(totalDataType);

                            noData++;
                            rowNum++;
                        }
                    }
                    else if (rowNum == (16 + sizeOfContent + 2))
                    {
                        Cell totalData = row.createCell(10);
                        totalData.setCellValue("TOTAL");
                        totalData.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,12,false));

                        Cell totalDataValue = row.createCell(11);
                        totalDataValue.setCellValue(totalDataValueInt);
                        totalDataValue.setCellStyle(generateBoldBorderCenterStyle(workbook,2,2,true,12,false));

                        row.createCell(12).setCellValue(totalDataType);
                    }
                    else if (rowNum == (16 + sizeOfContent + 3))
                        row.createCell(2).setCellValue("Segel Kertas No :");
                    else if (rowNum == (16 + sizeOfContent + 4))
                        row.createCell(2).setCellValue("Tanggal :");
                    else if (rowNum == (16 + sizeOfContent + 6))
                    {
                        Cell city = row.createCell(9);
                        city.setCellValue("JAKARTA");
                        city.setCellStyle(generateBoldBorderCenterStyle(workbook,2,2,false,11,false));

                        Cell tanggal = row.createCell(10);
                        tanggal.setCellValue(tanggalValue);
                        tanggal.setCellStyle(generateBoldBorderCenterStyle(workbook,1,1,false,11,false));

                        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,10,11));
                    }
                    else if (rowNum == (16 + sizeOfContent + 12))
                    {
                        Cell penanggungJawab = row.createCell(9);
                        penanggungJawab.setCellValue("TETEKI BUDI RAHARJO");
                        penanggungJawab.setCellStyle(generateBoldBorderCenterStyle(workbook,1,2,false,11,false));
                        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,9,11));
                    }
                    else if (rowNum == (16 + sizeOfContent + 15))
                        row.createCell(4).setCellValue("PT. KHARISMA ADHI NUSANTARA");
                    else if (rowNum == (16 + sizeOfContent + 16))
                        row.createCell(4).setCellValue(content.getKonsolidasi().getSarana().replace(saranaNumber, ""));
                    else if (rowNum == (16 + sizeOfContent + 17))
                        row.createCell(4).setCellValue(saranaNumber + "-" + content.getKonsolidasi().getNoFlight());
                    else if (rowNum == (16 + sizeOfContent + 19))
                    {
                        row.createCell(10).setCellValue("Tanjung Priok");
                    }
                    else if (rowNum == (16 + sizeOfContent + 20))
                    {
                        row.createCell(10).setCellValue("JAKARTA");
                    }
                    else if (rowNum == (16 + sizeOfContent + 25))
                    {
                        row.createCell(4).setCellValue("CONTAINER EXPORT CFS");

                        Cell totalDataValue = row.createCell(8);
                        totalDataValue.setCellValue(totalDataValueInt);
                        totalDataValue.setCellStyle(generateBoldBorderCenterStyle(workbook,2,2,true,12,false));

                        Cell totalData = row.createCell(9);
                        totalData.setCellValue("Total");
                        totalData.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,11,false));
                    }
                    else if (rowNum == (16 + sizeOfContent + 26))
                    {
                        Cell ukuranPetiKemas = row.createCell(1);
                        ukuranPetiKemas.setCellValue(content.getUkuranPetiKemas());
                        ukuranPetiKemas.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,false,14,false));

                        row.createCell(4).setCellValue("DEST");

                        Cell dest = row.createCell(5);
                        dest.setCellValue(content.getKonsolidasi().getNegaraTujuan());
                        dest.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,14,false));
                        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,5,6));

                        row.createCell(7).setCellValue(totalDataType + "S");
                        row.createCell(8).setCellValue(totalDataType + "S");
                    }
                    else if (rowNum == (16 + sizeOfContent + 27))
                    {
                        row.createCell(4).setCellValue("BOOKING");

                        Cell dest = row.createCell(5);
                        dest.setCellValue("                        ");
                        dest.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,14,false));
                        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,5,6));
                    }
                    else if (rowNum == (16 + sizeOfContent + 28))
                    {
                        Cell containerNo = row.createCell(4);
                        containerNo.setCellValue("CONTAINER NO");
                        containerNo.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,12,false));

                        Cell containerNoValue = row.createCell(5);
                        containerNoValue.setCellValue(content.getNomorPetiKemas());
                        containerNoValue.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,14,false));
                        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,5,6));

                        Cell pkbeNo = row.createCell(10);
                        pkbeNo.setCellValue("PKBE No:");
                        pkbeNo.setCellStyle(generateBoldBorderCenterStyle(workbook,2,2,false,11,false));

                        String[] splits = content.getNomorTanggalPendaftaran().split("/");

                        Cell pkbeNoValue = row.createCell(11);
                        pkbeNoValue.setCellValue(splits[0] + "/" + splits[1]);
                        pkbeNoValue.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,11,false));
                    }
                    else if (rowNum == (16 + sizeOfContent + 29))
                    {
                        Cell sealNo = row.createCell(4);
                        sealNo.setCellValue("SEAL NO");
                        sealNo.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,12,false));

                        Cell sealNoValue = row.createCell(5);
                        sealNoValue.setCellValue("                   ");
                        sealNoValue.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,14,false));
                        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,5,6));

                        Cell ajuNo = row.createCell(10);
                        ajuNo.setCellValue("Aju No:");
                        ajuNo.setCellStyle(generateBoldBorderCenterStyle(workbook,2,2,false,11,false));

                        String[] splits = content.getNomorPengajuan().split("-");

                        Cell ajuNoValue = row.createCell(11);
                        ajuNoValue.setCellValue(splits[splits.length - 1]);
                        ajuNoValue.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,true,11,false));
                    }
                    else if (rowNum == (16 + sizeOfContent + 30))
                    {
                        Cell traffic = row.createCell(4);
                        traffic.setCellValue("Traffic Cordinator :");
                        traffic.setCellStyle(generateBoldBorderCenterStyle(workbook,0,2,false,8,false));
                    }
                    else if (rowNum == (16 + sizeOfContent + 31))
                    {
                        for (int i = 1; i <= 2; i ++)
                        {
                            sheet.createRow(rowNum + i);
                            rowNum++;
                        }

                        Cell note = row.createCell(4);
                        note.setCellValue("Note :");
                        note.setCellStyle(generateBoldBorderCenterStyle(workbook,0, 2,false,11,false));

                        Cell attached = row.createCell(5);
                        attached.setCellValue("ATTACHED CONTAINER IS UNDER TRUCKING LIABILITIES");
                        attached.setCellStyle(generateBoldBorderCenterStyle(workbook,0,1,true,14,true));

                        CellRangeAddress region = new CellRangeAddress(rowNum - 2, rowNum,5,8);

                        PropertyTemplate propertyTemplate = new PropertyTemplate();
                        propertyTemplate.drawBorders(region, BorderStyle.THIN, IndexedColors.RED.getIndex(), BorderExtent.ALL);
                        propertyTemplate.applyBorders(sheet);

                        sheet.addMergedRegion(region);
                    }
                    else if (rowNum == (16 + sizeOfContent + 36))
                    {
                        String newTanggalValue = tanggalValue.replace("-", "/");

                        Cell tanggal = row.createCell(10);
                        tanggal.setCellValue(newTanggalValue);
                        tanggal.setCellStyle(generateBoldBorderCenterStyle(workbook,1,1,false,11,false));

                        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum,10,11));
                    }
                    else if (rowNum == (16 + sizeOfContent + 40))
                    {
                        row.createCell(9).setCellValue("TETEKI BUDI RAHARJO");
                    }
                    else if (rowNum == (16 + sizeOfContent + 41))
                    {
                        statusRow = false;

                        // Resize all columns to fit the content size
                        for (int i = 0; i < 12; i++)
                        {
                            sheet.autoSizeColumn(i);
                        }
                    }

                    rowNum++;
                }

                // All done
                String excellFullPath = parentPath + "/Report-" + transaction.getNoContent() + ".xlsx";
                
                FileOutputStream fileOut = new FileOutputStream(excellFullPath);
                workbook.write(fileOut);
                fileOut.close();

                result = true;
                
                //Preview Excel File
                File file = new File(excellFullPath);
                
                if (file.exists()) 
                	Desktop.getDesktop().open(file);
                
            }
            catch (Exception e)
            {
                System.out.print("Error when write & open excel : ");
                e.printStackTrace();
            }
        }

        return result;
    }

    private XSSFCellStyle generateBoldBorderCenterStyle(XSSFWorkbook workbook, int align, int verticalAlign, boolean isBold, int fontSize, boolean isRed)
    {
        // Create Cell Style - BOLD
        XSSFCellStyle style = workbook.createCellStyle();

        if (align == 0)
            style.setAlignment(HorizontalAlignment.LEFT);
        else if (align == 1)
            style.setAlignment(HorizontalAlignment.CENTER);
        else if (align == 2)
            style.setAlignment(HorizontalAlignment.RIGHT);

        if (verticalAlign == 0)
            style.setVerticalAlignment(VerticalAlignment.TOP);
        else if (verticalAlign == 1)
            style.setVerticalAlignment(VerticalAlignment.CENTER);
        else if (verticalAlign == 2)
            style.setVerticalAlignment(VerticalAlignment.BOTTOM);

        XSSFFont font = workbook.createFont();

        if (isBold)
            font.setBold(true);

        font.setFontHeightInPoints((short) fontSize);

        if (isRed)
        {
            XSSFColor xssfColor = new XSSFColor(new java.awt.Color(255, 24, 24), index -> new byte[0]);
            font.setColor(xssfColor);
        }

        style.setFont(font);

        return style;
    }
}