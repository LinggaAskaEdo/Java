package mls.lookupservice.task;

import mls.lookupservice.config.AppConfig;
import mls.lookupservice.dao.MlsDao;
import mls.lookupservice.function.DataFunction;
import mls.lookupservice.function.DataFunctionTest;
import mls.lookupservice.model.MlsFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Lingga on 13/04/17.
 */

@Test(threadPoolSize = 4, invocationCount = 10)
public class DataServiceTest
{
    private final Logger log = LoggerFactory.getLogger(DataFunctionTest.class);

    private List<String> result = new ArrayList<>();

    @BeforeMethod
    public void setUp() throws Exception
    {
        result.add("MLS-full-cell-export-2017-02-15T000000.csv.gz");
        result.add("MLS-full-cell-export-2017-01-15T000000.csv.gz");

        result.add("MLS-full-cell-export-2017-03-15T000000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-15T010000.csv.gz");

        result.add("MLS-full-cell-export-2017-03-18T000000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-18T000000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-18T010000.csv.gz");

        result.add("MLS-full-cell-export-2017-03-17T000000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T000000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T010000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T020000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T030000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T040000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T050000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T060000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T070000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T080000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T090000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T100000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T110000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T120000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T130000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T140000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T150000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T160000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T170000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T180000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T190000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T210000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T210000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T220000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T230000.csv.gz");

        //sort asc collection
        //Collections.sort(result);

        //sort desc collection
        Comparator comparator = Collections.reverseOrder();
        result.sort(comparator);
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
        result.clear();
    }

    @Test
    public void testNewOfflineUpdate() throws Exception
    {
        log.info("test new offline update");

        boolean isFullExist = false;

        List<String> resultFiltered = new ArrayList<>();

        final MlsDao mlsDao = mock(MlsDao.class);
        when(mlsDao.readMarkerDao()).thenReturn("2016-03-14");
        when(mlsDao.readCounterDao()).thenReturn("0");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "patternDiff", "MLS-diff-cell-export-");
        ReflectionTestUtils.setField(appConfig, "patternFull", "MLS-full-cell-export-");
        ReflectionTestUtils.setField(appConfig, "formatFileCompress", ".csv.gz");
        ReflectionTestUtils.setField(appConfig, "counterFilePrefix", "T");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mlsDao);

        for (String aResult : result)
        {
            //log.info("result: {}", aResult);

            if (aResult.contains(appConfig.patternDiff))
            {
                if (dataFunction.isMlsFile(aResult) && dataFunction.compareCounterMarker(dataFunction.splitFileName(aResult, appConfig.patternDiff,
                        appConfig.formatFileCompress)))
                {
                    resultFiltered.add(aResult);
                }
            }
            else if (aResult.contains(appConfig.patternFull))
            {
                if (dataFunction.isMlsFullFile(aResult) && dataFunction.compareCounterMarker(dataFunction.splitFileName(aResult, appConfig.patternFull,
                        appConfig.formatFileCompress)) && !isFullExist)
                {
                    resultFiltered.add(aResult);
                    isFullExist = true;

                    when(mlsDao.readMarkerDao()).thenReturn("2017-03-18");
                }
            }
        }

        if (resultFiltered.size() > 0)
        {
            for (String aResultFiltered : resultFiltered)
            {
                log.info("resultFiltered: {}", aResultFiltered);
            }
        }
    }

    @Test
    public void testNewOfflineUpdateScanner() throws Exception
    {
        log.info("test new offline update scan");

        boolean isFullExist = false;
        String fullFileName = null;

        final MlsDao mlsDao = mock(MlsDao.class);
        when(mlsDao.readMarkerDao()).thenReturn("2016-02-11");
        when(mlsDao.readCounterDao()).thenReturn("0");
        when(mlsDao.checkCellDB()).thenReturn(1);

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "pathDownload", "/home/edo/MLS/test/");
        ReflectionTestUtils.setField(appConfig, "patternDiff", "MLS-diff-cell-export-");
        ReflectionTestUtils.setField(appConfig, "patternFull", "MLS-full-cell-export-");
        ReflectionTestUtils.setField(appConfig, "formatFileCompress", ".csv.gz");
        ReflectionTestUtils.setField(appConfig, "counterFilePrefix", "T");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mlsDao);

        List<String> newFiles = dataFunction.getListFiles(appConfig.pathDownload);
        List<MlsFiles> filterMlsFiles = new ArrayList<>();

        boolean statusCellDB = mlsDao.checkCellDB() > 0;
        boolean statusFullFiles = newFiles.stream().anyMatch(str -> str.trim().contains(appConfig.patternFull));

        log.info("statusCellDB: {}, statusFullFiles: {}", statusCellDB, statusFullFiles);

        /*
        1. if statusCellDB = false && statusFullFiles = false ~~> don't run, gives error messages
        2. if statusCellDB = true && statusFullFiles = true ~~> newFiles array short by DESC, get only 1 full file and diff file with same marker
        3. if statusCellDB = true && statusFullFiles = false ~~> newFiles array short by ASC
        */

        if (!statusCellDB && !statusFullFiles)
        {
            log.error("Error, your cell DB is empty. Make sure you have 1 full MLS files");
        }
        else if (statusCellDB && statusFullFiles)
        {
            log.info("newFiles list short by DESC");

            Comparator comparator = Collections.reverseOrder();
            newFiles.sort(comparator);

            for (String result : newFiles)
            {
                if (dataFunction.isMlsFullFile(result) && dataFunction.compareCounterMarker(dataFunction.splitFileName(result, appConfig.patternFull,
                        appConfig.formatFileCompress)) && !isFullExist)
                {
                    MlsFiles mlsFiles = new MlsFiles(result, "A");
                    filterMlsFiles.add(mlsFiles);

                    isFullExist = true;
                    fullFileName = result;
                }
                else if (isFullExist && fullFileName != null && dataFunction.isMlsFile(result))
                {
                    if (dataFunction.compareCounterMarker(dataFunction.splitFileName(fullFileName, appConfig.patternFull, appConfig.formatFileCompress),
                            dataFunction.splitFileName(result, appConfig.patternDiff, appConfig.formatFileCompress)))
                    {
                        MlsFiles mlsFiles = new MlsFiles(result, "B");
                        filterMlsFiles.add(mlsFiles);
                    }
                }
            }
        }
        else if (statusCellDB)
        {
            log.info("newFiles list short by ASC");

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

        if (filterMlsFiles.size() != 0)
        {
            log.info("Found new {} file(s)", filterMlsFiles.size());

            filterMlsFiles.sort(MlsFiles.COMPARE_BY_TYPE);

            for (int i = 0; i < filterMlsFiles.size(); i++)
            {
                log.info("File 0 ({}): {}", i + 1, filterMlsFiles.get(i).getPath());
            }

            if (filterMlsFiles.get(0).getPath().contains(appConfig.patternFull))
            {
                /*
                1. full update
                2. delete full file from list
                3. sort list by path
                */

                for (int i = 0; i < filterMlsFiles.size(); i++)
                {
                    log.info("File 1 ({}): {}", i + 1, filterMlsFiles.get(i).getPath());
                }

                filterMlsFiles.remove(0); //remove full file from list
                filterMlsFiles.sort(MlsFiles.COMPARE_BY_PATH);

                for (int i = 0; i < filterMlsFiles.size(); i++)
                {
                    log.info("File 2 ({}): {}", i + 1, filterMlsFiles.get(i).getPath());
                }
            }

            //Update normally then update counter & marker based on last files
            for (int i = 0; i < filterMlsFiles.size(); i++)
            {
                log.info("File 3 ({}): {}", i + 1, filterMlsFiles.get(i).getPath());
            }
        }
    }
}