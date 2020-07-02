package mls.lookupservice.task;

import mls.lookupservice.config.AppConfig;
import mls.lookupservice.dao.MlsDao;
import mls.lookupservice.function.DataFunction;
import mls.lookupservice.model.MlsFiles;
import mls.lookupservice.preference.Preference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lingga on 23/03/17.
 */

@Component
public class DataService
{
    private final Logger log = LoggerFactory.getLogger(DataService.class);

    private final AppConfig appConfig;
    private final DataFunction dataFunction;
    private final Environment env;
    private final MlsDao mlsDao;

    @Autowired
    public DataService(AppConfig appConfig, DataFunction dataFunction, Environment env, MlsDao mlsDao)
    {
        this.appConfig = appConfig;
        this.dataFunction = dataFunction;
        this.env = env;
        this.mlsDao = mlsDao;
    }

    @Scheduled(fixedRateString = "${scheduler.time.ms}")
    public void reportCurrentTime() throws InterruptedException
    {
        log.info("Start MLS Lookup Service with mode: OFFLINE");

        boolean isFullExist = false;
        boolean isDiffExist = false;
        String fullFileName = null;
        String lastCounter = null;
        String lastMarker = null;

        List<String> newFiles = dataFunction.getListFiles(appConfig.pathDownload);
        List<MlsFiles> filterMlsFiles = new ArrayList<>();

        if (newFiles != null && newFiles.size() > 0)
        {
            boolean statusCellDB = mlsDao.checkCellDB() > 0;
            boolean statusFullFiles = newFiles.stream().anyMatch(str -> str.trim().contains(appConfig.patternFull));

            if (!statusCellDB && !statusFullFiles)
            {
                log.error("Error, your cell DB is empty. Make sure you have 1 full MLS files");
            }
            else if (statusCellDB && statusFullFiles) //process if list contains full files
            {
                //sort list so full file to be at the top
                Comparator comparator = Collections.reverseOrder();
                newFiles.sort(comparator);

                for (String result : newFiles)
                {
                    log.debug("Result: {}", result);

                    if (dataFunction.isMlsFullFile(result) && dataFunction.compareCounterMarker(dataFunction.splitFileName(result, appConfig.patternFull,
                            appConfig.formatFileCompress)) && !isFullExist)
                    {
                        System.out.println("AAA");
                        MlsFiles mlsFiles = new MlsFiles(result, MlsFiles.TYPE_A);
                        filterMlsFiles.add(mlsFiles);

                        isFullExist = true;
                        fullFileName = result;
                    }
                    else if (isFullExist && dataFunction.isMlsFile(result))
                    {
                        System.out.println("BBB");
                        if (dataFunction.compareCounterMarker(dataFunction.splitFileName(fullFileName, appConfig.patternFull, appConfig.formatFileCompress),
                                dataFunction.splitFileName(result, appConfig.patternDiff, appConfig.formatFileCompress)))
                        {
                            MlsFiles mlsFiles = new MlsFiles(result, MlsFiles.TYPE_B);
                            filterMlsFiles.add(mlsFiles);
                        }
                    }
                }
            }
            else if (statusCellDB) //process if list only contains diff files
            {
                System.out.println("CCC");
                //sort list by name
                Collections.sort(newFiles);

                for (String result : newFiles)
                {
                    if (dataFunction.compareCounterMarker(dataFunction.splitFileName(result, appConfig.patternDiff, appConfig.formatFileCompress)))
                    {
                        MlsFiles mlsFiles = new MlsFiles(result, "B");
                        filterMlsFiles.add(mlsFiles);
                    }
                }
            }
        }

        if (filterMlsFiles.size() != 0)
        {
            log.info("Found new {} file(s)", filterMlsFiles.size());

            for (int i = 0; i < filterMlsFiles.size(); i++)
            {
                log.info("File ({}): {}", i + 1, filterMlsFiles.get(i).getPath());
            }

            filterMlsFiles.sort(MlsFiles.COMPARE_BY_TYPE);

            //Update full
            if (filterMlsFiles.get(0).getPath().contains(appConfig.patternFull))
            {
                String fullName = filterMlsFiles.get(0).getPath();

                //download, extract & dump full file
                log.info("Starting update full cell data !!!");

                //extract file
                boolean statusExtract = dataFunction.extractFile(false,false, fullName);

                if (statusExtract)
                {
                    log.info(env.getProperty("extract.success"));

                    //truncate table
                    log.info(env.getProperty("truncate.start"));
                    truncateTable();

                    //dump file to table
                    log.info(env.getProperty("dump.start"));
                    boolean dumpStatus = dumpData(fullName.replace(appConfig.formatFileCompress, appConfig.formatFileOri), appConfig.patternFull);

                    filterMlsFiles.remove(0);

                    if (filterMlsFiles.size() > 0)
                    {
                        filterMlsFiles.sort(MlsFiles.COMPARE_BY_PATH);
                        isDiffExist = true;
                    }
                    else
                    {
                        //get last counter & marker
                        lastCounter = dataFunction.extractCounterMarker(appConfig.counterFileName, appConfig.patternFull, appConfig.formatFileCompress, fullName);
                        lastMarker = dataFunction.extractCounterMarker(appConfig.markerFileName, appConfig.patternFull, appConfig.formatFileCompress, fullName);
                    }

                    if (dumpStatus)
                    {
                        //no need download diff files with 0 counter
                        if (lastCounter != null)
                        {
                            lastCounter = Integer.toString(Integer.parseInt(lastCounter) + 1);

                            //Update counter & marker based on last files
                            dataFunction.updateCounterMarker(appConfig.counterFileName, lastCounter);
                            dataFunction.updateCounterMarker(appConfig.markerFileName, lastMarker);
                        }
                    }
                }
                else
                {
                    log.info("Extract fail");
                }

                Thread.sleep(3000);
            }

            //Update normally if list not empty
            if (isDiffExist)
            {
                for (int i = 0; i < filterMlsFiles.size(); i++)
                {
                    log.info("File ({}): {}", i + 1, filterMlsFiles.get(i).getPath());

                    String fileName = filterMlsFiles.get(i).getPath();

                    //extract file
                    boolean statusExtract = dataFunction.extractFile(false,false, fileName);

                    if (statusExtract)
                    {
                        log.info(env.getProperty("extract.success"));

                        //proses update database
                        log.info(env.getProperty("dump.start"));
                        boolean dumpStatus = dumpData(fileName.replace(appConfig.formatFileCompress, appConfig.formatFileOri), appConfig.patternDiff);

                        //get last counter & marker
                        lastCounter = dataFunction.extractCounterMarker(appConfig.counterFileName, appConfig.patternDiff, appConfig.formatFileCompress, fileName);
                        lastMarker = dataFunction.extractCounterMarker(appConfig.markerFileName, appConfig.patternDiff, appConfig.formatFileCompress, fileName);

                        if (dumpStatus)
                        {
                            //Update counter & marker based on last files
                            dataFunction.updateCounterMarker(appConfig.counterFileName, lastCounter);
                            dataFunction.updateCounterMarker(appConfig.markerFileName, lastMarker);
                        }
                    }
                    else
                    {
                        log.info(env.getProperty("extract.fail"));
                    }

                    Thread.sleep(3000);
                }
            }
        }
        else
        {
            log.info(env.getProperty("no.new.files"));
        }

        //clear list
        if (newFiles != null)
        {
            newFiles.clear();
        }

        filterMlsFiles.clear();

        //======Set Delay======
        Thread.sleep(30000);

        log.info("Start MLS Lookup Service with mode: ONLINE");

        //generate URL
        boolean status = true;

        String fileName = dataFunction.generateFileName(false);
        String codeName = dataFunction.generateCodeName(dataFunction.readCounter());
        String fullName = fileName + codeName;
        String url = dataFunction.generateUrl(false, fullName);

        //check url with new counter & marker
        boolean statusURL = dataFunction.checkExistURL(url);

        if (dataFunction.checkThreshold(statusURL)) //if cell db is out-of-date
        {
            log.info(env.getProperty("status.url.fail"));

            //download, extract & dump full file
            log.info("Starting update full cell data !!!");

            //set value counter to 0
            dataFunction.updateCounterMarker(appConfig.counterFileName, "0");

            //generate full URL
            fileName = dataFunction.generateFileName(true);
            codeName = dataFunction.generateCodeName(dataFunction.readCounter());
            fullName = fileName + codeName;
            url = dataFunction.generateUrl(true, fullName);

            //download full cell file then extract
            log.info("Downloading file {}", url);
            boolean statusDownload = dataFunction.downloadFile(true, url, fullName);

            if (statusDownload)
            {
                log.info(env.getProperty("download.success"));

                //extract file
                boolean statusExtract = dataFunction.extractFile(true,true, fullName);

                if (statusExtract)
                {
                    log.info(env.getProperty("extract.success"));

                    //truncate table
                    log.info(env.getProperty("truncate.start"));
                    truncateTable();

                    //dump file to table
                    log.info(env.getProperty("dump.start"));
                    boolean dumpStatus = dumpData(appConfig.pathDownload + appConfig.patternFull + fullName + appConfig.formatFileOri, appConfig.patternFull);

                    //generate counter to 1
                    if (dumpStatus)
                    {
                        dataFunction.updateCounterMarker(appConfig.counterFileName, "1");
                    }

                    Thread.sleep(3000);
                }
                else
                {
                    log.info(env.getProperty("extract.fail"));
                }
            }
            else
            {
                log.info(env.getProperty("download.fail"));
            }

            status = false;
        }
        else if (!statusURL) //if response is 404
        {
            if (!dataFunction.checkThresholdHour())
            {
                log.info(env.getProperty("file.exist.download.next.warning"));

                int existCounterInt = Integer.parseInt(dataFunction.readCounter());
                boolean statusNextCounter = true;
                boolean statusRoundNextCounter = true;
                String nextCounter = String.valueOf(existCounterInt);

                while (statusNextCounter)
                {
                    if (nextCounter.equalsIgnoreCase(env.getProperty("counter.file.max")))
                    {
                        if (statusRoundNextCounter)
                        {
                            fileName = dataFunction.getMarkerNextDay(fileName);
                            nextCounter = "0";
                        }
                        else
                        {
                            status = false;
                            break;
                        }
                    }
                    else
                    {
                        statusRoundNextCounter = false;
                        nextCounter = String.valueOf(Integer.parseInt(nextCounter) + 1);
                    }

                    codeName = dataFunction.generateCodeName(nextCounter);
                    fullName = fileName + codeName;
                    url = dataFunction.generateUrl(false, fullName);

                    if (dataFunction.checkExistURL(url))
                    {
                        dataFunction.updateCounterMarker(appConfig.counterFileName, nextCounter);

                        if (statusRoundNextCounter)
                        {
                            dataFunction.updateCounterMarker(appConfig.markerFileName, fileName);
                            statusRoundNextCounter = false;
                        }

                        statusNextCounter = false;
                    }

                    Thread.sleep(10000);

                }
            }
        }

        if (status)
        {
            log.info(env.getProperty("status.url.success"));

            //download, extract & dump diff file
            log.info("Downloading file {}", url);

            //download file
            boolean statusDownload = dataFunction.downloadFile(false, url, fullName);

            if (statusDownload)
            {
                log.info(env.getProperty("download.success"));

                //extract file
                boolean statusExtract = dataFunction.extractFile(true,false, fullName);

                if (statusExtract)
                {
                    log.info(env.getProperty("extract.success"));

                    //proses update database
                    log.info(env.getProperty("dump.start"));
                    boolean dumpStatus = dumpData(appConfig.pathDownload + appConfig.patternDiff + fullName + appConfig.formatFileOri, appConfig.patternDiff);

                    //check counter & marker
                    if (dumpStatus)
                    {
                        dataFunction.checkCounterAndMarker();
                    }

                    Thread.sleep(3000);
                }
                else
                {
                    log.info(env.getProperty("extract.fail"));
                }
            }
            else
            {
                log.info(env.getProperty("download.fail"));
            }
        }

        //======Set Delay======
        Thread.sleep(30000);

        log.info("Start MLS Lookup Service with mode: CLEAN UP");

        List<String> resultOldFiles = dataFunction.getListAllFiles(appConfig.pathDownload);
        List<String> oldFiles = new ArrayList<>();
        List<String> oldFullFiles = new ArrayList<>();

        if (resultOldFiles.size() > 0)
        {
            for (String aResult : resultOldFiles)
            {
                if (dataFunction.isMlsFile(aResult))
                {
                    //checking that files is old based on date
                    if (!dataFunction.compareCounterMarker(dataFunction.checkingFileNames(aResult)))
                    {
                        oldFiles.add(aResult);
                    }
                }
                else if (dataFunction.isMlsFullFile(aResult))
                {
                    oldFullFiles.add(aResult);
                }
            }

            if (oldFullFiles.size() > 0)
            {
                //don't delete last files with format .csv.gz
                log.info("Found {} old full file(s)", oldFullFiles.size());

                for (int i = 0; i < oldFullFiles.size(); i++)
                {
                    log.info("File ({}): {}", i + 1, oldFullFiles.get(i));

                    //process deleting files except last files
                    if (!(i == oldFullFiles.size() - 1))
                    {
                        dataFunction.deleteFile(oldFullFiles.get(i));
                    }
                }
            }

            if (oldFiles.size() != 0)
            {
                log.info("Found {} old diff file(s)", oldFiles.size());

                for (int i = 0; i < oldFiles.size(); i++)
                {
                    log.info("File ({}): {}", i + 1, oldFiles.get(i));

                    //process deleting files
                    dataFunction.deleteFile(oldFiles.get(i));
                }
            }
        }
        else
        {
            log.info(env.getProperty("no.old.files"));
        }

        //clear list
        resultOldFiles.clear();
        oldFiles.clear();
        oldFullFiles.clear();
    }

    private void truncateTable()
    {
        if (mlsDao.truncateTable())
        {
            log.info(env.getProperty("truncate.success"));
        }
        else
        {
            log.info(env.getProperty("truncate.fail"));
        }
    }

    private boolean dumpData(String filePath, String type)
    {
        boolean status;

        //change status to use backup database
        Preference.STATUS_TABLE = Preference.CELL_DB_STATUS_DISABLE;
        log.info("PRIMARY CELL_DB status: {}, using SECONDARY CELL_DB", Preference.STATUS_TABLE);

        if (type.equalsIgnoreCase(appConfig.patternFull))
        {
            if (mlsDao.batchFullProcess(filePath))
            {
                log.info(env.getProperty("dump.success"));
                status = true;
            }
            else
            {
                log.info(env.getProperty("dump.fail"));
                status = false;
            }
        }
        else
        {
            if (mlsDao.batchProcess(filePath))
            {
                log.info(env.getProperty("dump.success"));
                status = true;
            }
            else
            {
                log.info(env.getProperty("dump.fail"));
                status = false;
            }
        }

        //change status to use primary database
        Preference.STATUS_TABLE = Preference.CELL_DB_STATUS_ENABLE;
        log.info("PRIMARY CELL_DB status: {}, back using PRIMARY CELL_DB", Preference.STATUS_TABLE);

        return status;
    }
}