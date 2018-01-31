package mls.lookupservice.function;

import mls.lookupservice.config.AppConfig;
import mls.lookupservice.dao.MlsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Lingga on 23/03/17.
 */

@Test(threadPoolSize = 4, invocationCount = 10)
public class DataFunctionTest
{
    private final Logger log = LoggerFactory.getLogger(DataFunctionTest.class);

    private List<String> result = new ArrayList<>();

    @BeforeMethod
    public void setUp() throws Exception
    {
        /*result.add("MLS-diff-cell-export-2017-03-17T000000.csv.gz");
        result.add("MLS-diff-cell-export-2017-03-17T010000.csv.gz");*/

        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T000000.csv.gz");
        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T010000.csv.gz");
        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T020000.csv.gz");
        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T030000.csv.gz");
        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T040000.csv.gz");
        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T050000.csv.gz");
        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T060000.csv.gz");
        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T070000.csv.gz");
        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T080000.csv.gz");
        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T090000.csv.gz");
        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T100000.csv.gz");
        result.add("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T110000.csv.gz");
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
        result.clear();
    }

    @Test
    public void testGenerateFileName() throws Exception
    {
        log.info("test generate file name");

        final MlsDao mlsDao = mock(MlsDao.class);
        when(mlsDao.readMarkerDao()).thenReturn("2017-03-13");

        final DataFunction dataFunction = new DataFunction(mock(AppConfig.class), mock(Environment.class), mlsDao);
        //assertThat(dataFunction.generateFileName(true)).isEqualTo("2017-04-13");
        assertThat(dataFunction.generateFileName(false)).isEqualTo("2017-03-13");
    }

    @Test
    public void testGenerateCodeName() throws Exception
    {
        log.info("test generate code name");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "counterFileLength", 6);
        ReflectionTestUtils.setField(appConfig, "counterFilePrefix", "T");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mock(MlsDao.class));
        assertThat(dataFunction.generateCodeName("11")).isEqualTo("T110000");
    }

    @Test
    public void testGenerateUrl() throws Exception
    {
        log.info("test generate url");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "urlSource", "https://d17pt8qph6ncyq.cloudfront.net/export/");
        ReflectionTestUtils.setField(appConfig, "patternFull", "MLS-full-cell-export-");
        ReflectionTestUtils.setField(appConfig, "patternDiff", "MLS-diff-cell-export-");
        ReflectionTestUtils.setField(appConfig, "formatFileCompress", ".csv.gz");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mock(MlsDao.class));
        assertThat(dataFunction.generateUrl(true, "2017-03-21T130000"))
                .isEqualTo("https://d17pt8qph6ncyq.cloudfront.net/export/MLS-full-cell-export-2017-03-21T130000.csv.gz");
        assertThat(dataFunction.generateUrl(false, "2017-03-21T130000"))
                .isEqualTo("https://d17pt8qph6ncyq.cloudfront.net/export/MLS-diff-cell-export-2017-03-21T130000.csv.gz");
    }

    @Test
    public void testReadCounter() throws Exception
    {
        log.info("test read counter");

        final MlsDao mlsDao = mock(MlsDao.class);
        when(mlsDao.readCounterDao()).thenReturn("11");

        final DataFunction dataFunction = new DataFunction(mock(AppConfig.class), mock(Environment.class), mlsDao);
        assertThat(dataFunction.readCounter()).isEqualTo("11");
    }

    @Test
    public void testExtractCounterMarker() throws Exception
    {
        log.info("test extract counter marker");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "counterFileName", "counter");
        ReflectionTestUtils.setField(appConfig, "markerFileName", "marker");
        ReflectionTestUtils.setField(appConfig, "patternDiff", "MLS-diff-cell-export-");
        ReflectionTestUtils.setField(appConfig, "patternFull", "MLS-full-cell-export-");
        ReflectionTestUtils.setField(appConfig, "formatFileCompress", ".csv.gz");
        ReflectionTestUtils.setField(appConfig, "formatFileOri", ".csv");
        ReflectionTestUtils.setField(appConfig, "counterFilePrefix", "T");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mock(MlsDao.class));
        assertThat(dataFunction.extractCounterMarker(appConfig.counterFileName, appConfig.patternFull, appConfig.formatFileCompress,
                "/home/edo/MLS/test/MLS-full-cell-export-2017-03-09T000000.csv.gz")).isEqualTo("0");
        assertThat(dataFunction.extractCounterMarker(appConfig.markerFileName, appConfig.patternDiff, appConfig.formatFileCompress,
                "/home/edo/MLS/test/MLS-diff-cell-export-2017-03-09T030000.csv.gz")).isEqualTo("2017-03-09");
    }

    @Test
    public void testDownloadFile() throws Exception
    {
        log.info("test download file");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "settingProxyHost", "10.32.16.22");
        ReflectionTestUtils.setField(appConfig, "settingProxyPort", "3128");
        ReflectionTestUtils.setField(appConfig, "pathDownload", "/home/edo/Downloads/tempLookup/");
        ReflectionTestUtils.setField(appConfig, "patternFull", "MLS-full-cell-export-");
        ReflectionTestUtils.setField(appConfig, "patternDiff", "MLS-diff-cell-export-");
        ReflectionTestUtils.setField(appConfig, "formatFileCompress", ".csv.gz");

        final Environment environment = mock(Environment.class);
        when(environment.getProperty("file.exist.download.warning")).thenReturn("File doesn't exist or has not been uploaded !!!");

        final DataFunction dataFunction = new DataFunction(appConfig, environment, mock(MlsDao.class));
        assertThat(dataFunction.downloadFile(true, "https://d17pt8qph6ncyq.cloudfront.net/export/MLS-full-cell-export-2017-03-21T130000.csv.gz",
                "2017-03-21T130000")).isEqualTo(false);
    }

    @Test
    public void testCheckExistURL() throws Exception
    {
        log.info("test check exist url");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "settingProxyHost", "10.32.16.22");
        ReflectionTestUtils.setField(appConfig, "settingProxyPort", "3128");
        ReflectionTestUtils.setField(appConfig, "responseCodeNotExist", 404);

        final Environment environment = mock(Environment.class);
        when(environment.getProperty("status.url.fail")).thenReturn("Your cell data is out of date !!!");
        when(environment.getProperty("status.url.success")).thenReturn("Your cell data is up to date !!!");

        final DataFunction dataFunction = new DataFunction(appConfig, environment, mock(MlsDao.class));
        assertThat(dataFunction.checkExistURL("https://d17pt8qph6ncyq.cloudfront.net/export/MLS-full-cell-export-2017-03-21T030000.csv.gz")).isEqualTo(false);
    }

    @Test
    public void testCheckThreshold() throws Exception
    {
        log.info("test check threshold");

        final MlsDao mlsDao = mock(MlsDao.class);
        when(mlsDao.readCounterDao()).thenReturn("10");
        when(mlsDao.readMarkerDao()).thenReturn("2017-07-31");

        final DataFunction dataFunction = new DataFunction(mock(AppConfig.class), mock(Environment.class), mlsDao);
        assertThat(dataFunction.checkThreshold(true)).isEqualTo(false);
    }

    @Test
    public void testCheckThresholdHour() throws Exception
    {
        log.info("test check threshold hour");

        final MlsDao mlsDao = mock(MlsDao.class);
        when(mlsDao.readCounterDao()).thenReturn("0");

        final DataFunction dataFunction = new DataFunction(mock(AppConfig.class), mock(Environment.class), mlsDao);
        assertThat(dataFunction.checkThresholdHour()).isEqualTo(false);
    }

    @Test
    public void testExtractFile() throws Exception
    {
        log.info("test extract file");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "compressBufferSize", 1024);
        ReflectionTestUtils.setField(appConfig, "pathDownload", "/home/edo/Downloads/tempLookup/");
        ReflectionTestUtils.setField(appConfig, "patternFull", "MLS-full-cell-export-");
        ReflectionTestUtils.setField(appConfig, "patternDiff", "MLS-diff-cell-export-");
        ReflectionTestUtils.setField(appConfig, "formatFileCompress", ".csv.gz");
        ReflectionTestUtils.setField(appConfig, "formatFileOri", ".csv");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mock(MlsDao.class));
        assertThat(dataFunction.extractFile(true, false, "MLS-full-cell-export-2017-03-21T030000.csv.gz")).isEqualTo(false);
    }

    @Test
    public void testGetListFiles() throws Exception
    {
        log.info("test get list files");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "formatFileCompress", ".csv.gz");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mock(MlsDao.class));
        assertThat(dataFunction.getListFiles("/home/edo/MLS/2017/03/17/").size()).isEqualTo(0);
    }

    @Test
    public void testGetListAllFiles() throws Exception
    {
        log.info("test get list all files");

        final DataFunction dataFunction = new DataFunction(mock(AppConfig.class), mock(Environment.class), mock(MlsDao.class));
        assertThat(dataFunction.getListAllFiles("/home/edo/MLS/2017/03/17/").size()).isEqualTo(0);
    }

    @Test
    public void testSplitFileName() throws Exception
    {
        log.info("test split file name");

        final DataFunction dataFunction = new DataFunction(mock(AppConfig.class), mock(Environment.class), mock(MlsDao.class));
        assertThat(dataFunction.splitFileName("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T030000.csv.gz", "MLS-diff-cell-export-",
                ".csv.gz")).isEqualTo("2017-03-17T030000");
    }

    @Test
    public void testCheckingFileNames() throws Exception
    {
        log.info("test checking file names");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "patternDiff", "MLS-diff-cell-export-");
        ReflectionTestUtils.setField(appConfig, "formatFileCompress", ".csv.gz");
        ReflectionTestUtils.setField(appConfig, "formatFileOri", ".csv");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mock(MlsDao.class));
        assertThat(dataFunction.checkingFileNames("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T030000.csv.gz")).isEqualTo("2017-03-17T030000");
    }

    @Test
    public void testIsMlsFile() throws Exception
    {
        log.info("test is mls file");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "patternDiff", "MLS-diff-cell-export-");
        ReflectionTestUtils.setField(appConfig, "formatFileCompress", ".csv.gz");
        ReflectionTestUtils.setField(appConfig, "formatFileOri", ".csv");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mock(MlsDao.class));
        assertThat(dataFunction.isMlsFile("/home/edo/MLS/2017/03/17/MLS-diff-cell-export-2017-03-17T030000.csv.gz")).isEqualTo(true);
    }

    @Test
    public void testIsMlsFullFile() throws Exception
    {
        log.info("test is mls full file");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "patternFull", "MLS-full-cell-export-");
        ReflectionTestUtils.setField(appConfig, "formatFileCompress", ".csv.gz");
        ReflectionTestUtils.setField(appConfig, "formatFileOri", ".csv");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mock(MlsDao.class));
        assertThat(dataFunction.isMlsFullFile("/home/edo/MLS/2017/03/17/MLS-full-cell-export-2017-03-17T030000.csv.gz")).isEqualTo(true);
    }

    @Test
    public void testCompareCounterMarker() throws Exception
    {
        log.info("test compare counter marker");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "counterFilePrefix", "T");

        final MlsDao mlsDao = mock(MlsDao.class);
        when(mlsDao.readMarkerDao()).thenReturn("2017-10-09");
        when(mlsDao.readCounterDao()).thenReturn("8");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mlsDao);
        assertThat(dataFunction.compareCounterMarker("2017-10-09T080000")).isEqualTo(false);
    }

    @Test
    public void testCompareCounterMarkerParams() throws Exception
    {
        log.info("test compare counter marker params");

        final AppConfig appConfig = mock(AppConfig.class);
        ReflectionTestUtils.setField(appConfig, "counterFilePrefix", "T");

        final MlsDao mlsDao = mock(MlsDao.class);
        when(mlsDao.readMarkerDao()).thenReturn("2017-03-20");
        when(mlsDao.readCounterDao()).thenReturn("3");

        final DataFunction dataFunction = new DataFunction(appConfig, mock(Environment.class), mlsDao);
        assertThat(dataFunction.compareCounterMarker("2017-03-20T000000", "2017-03-20T050000")).isEqualTo(true);
    }

    @Test
    public void testGetMarkerNextDay() throws Exception
    {
        log.info("test get marker next day");

        final DataFunction dataFunction = new DataFunction(mock(AppConfig.class), mock(Environment.class), mock(MlsDao.class));
        assertThat(dataFunction.getMarkerNextDay("2017-07-18")).isEqualTo("2017-07-19");
        assertThat(dataFunction.getMarkerNextDay("2017-07-31")).isEqualTo("2017-08-01");
    }

    @Test
    public void testGetDifferentDays() throws Exception
    {
        log.info("test get different days");

        final DataFunction dataFunction = new DataFunction(mock(AppConfig.class), mock(Environment.class), mock(MlsDao.class));
        //assertThat(dataFunction.getDiffDays("2017-06-31", "2017-08-01")).isEqualTo(1L);
        assertThat(dataFunction.getDiffDays(2017, 7, 31, 2017,8,1)).isEqualTo(1);
    }
}