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

    public boolean generateQris(String sourcePath, String destPath)
    {
        boolean result = false;

        try
        {
            List<String> qrisDataList = util.getQrisData(sourcePath);

            if (!qrisDataList.isEmpty())
            {
                result = util.generatePdf(qrisDataList, destPath);
            }
        }
        catch (Exception e)
        {
            //Do nothing
        }

        return result;
    }
}