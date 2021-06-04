package id.co.qris.generator.service;

import id.co.qris.generator.util.QrisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QrisService
{
    private final QrisUtil util;

    @Autowired
    public QrisService(QrisUtil util)
    {
        this.util = util;
    }

    public boolean generateQris(String type, String sourcePath, String destPath, String size)
    {
        boolean result = false;

        try
        {
            List<String> qrisDataList = util.getQrisData(type, sourcePath);

            if (!qrisDataList.isEmpty())
            {
                result = util.generatePdf(qrisDataList, destPath, type, size);
            }
        }
        catch (Exception e)
        {
            //Do nothing
        }

        return result;
    }
}