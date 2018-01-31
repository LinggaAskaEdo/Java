package mls.lookupservice.function;

import mls.lookupservice.config.AppConfig;
import mls.lookupservice.dao.MlsDao;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * Created by Lingga on 02/11/16.
 */

@Service
@Transactional
public class DataFunction
{
    private final Logger log = LoggerFactory.getLogger(DataFunction.class);

    private final AppConfig appConfig;

    private final Environment env;

    private final MlsDao mlsDao;

    @Autowired
    public DataFunction(AppConfig appConfig, Environment env, MlsDao mlsDao)
    {
        this.appConfig = appConfig;
        this.env = env;
        this.mlsDao = mlsDao;
    }

    public String generateFileName(boolean status)
    {
        String name = "";

        if (status)
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try
            {
                name = simpleDateFormat.format(new Date());
                updateCounterMarker(appConfig.markerFileName, name);
            }
            catch (Exception e)
            {
                log.error("generateFileName: {}", e.toString());
            }
        }
        else
        {
            name = readMarker();
        }

        return name;
    }

    public String generateCodeName(String counterValue)
    {
        String codeName;
        StringBuilder extendName = new StringBuilder();

        int lengthCounter = counterValue.length();

        if (lengthCounter == 1)
        {
            counterValue = "0" + counterValue;
        }

        for (int x = 0; x < (appConfig.counterFileLength - counterValue.length()); x++)
        {
            extendName.append("0");
        }

        codeName = appConfig.counterFilePrefix + counterValue + extendName;

        return codeName;
    }

    public String generateUrl(boolean status, String name)
    {
        String url;

        if (status)
        {
            url = appConfig.urlSource + appConfig.patternFull + name + appConfig.formatFileCompress;
        }
        else
        {
            url = appConfig.urlSource + appConfig.patternDiff + name + appConfig.formatFileCompress;
        }

        return url;
    }

    private String readMarker()
    {
        return mlsDao.readMarkerDao();
    }

    private void updateMarker()
    {
        String strYear, strMonth, strDay;

        String markerValue = readMarker();

        String[] output = markerValue.split("-");

        int year = Integer.parseInt(output[0]);
        int month = Integer.parseInt(output[1]);
        int day = Integer.parseInt(output[2]);

        int maxDay = getMaxDayOfMonth(year, month);

        //log.debug("year: {}, month: {}, day: {}, maxDay: {}", year, month, day, maxDay);

        if (day == maxDay)
        {
            if (month == 12)
            {
                year = year + 1;
                month = 1;
                day = 1;
            }
            else
            {
                month = month + 1;
                day = 1;
            }
        }
        else
        {
            day = day + 1;
        }

        strYear = String.valueOf(year);

        if (String.valueOf(month).length() == 1)
        {
            strMonth = "0" + String.valueOf(month);
        }
        else
        {
            strMonth = String.valueOf(month);
        }

        if (String.valueOf(day).length() == 1)
        {
            strDay = "0" + String.valueOf(day);
        }
        else
        {
            strDay = String.valueOf(day);
        }

        String newMarker = strYear + "-" + strMonth + "-" + strDay;

        updateCounterMarker(appConfig.markerFileName, newMarker);
    }

    public String readCounter()
    {
        return mlsDao.readCounterDao();
    }

    private void updateCounter()
    {
        String counterValue = readCounter();
        String currentLine = String.valueOf((Integer.parseInt(counterValue) + 1));
        updateCounterMarker(appConfig.counterFileName, currentLine);
    }

    private void resetCounter()
    {
        updateCounterMarker(appConfig.counterFileName, appConfig.counterFileMin);
    }

    public void updateCounterMarker(String name, String value)
    {
        if (name.equalsIgnoreCase(appConfig.counterFileName))
        {
            mlsDao.updateCounterDao(value);
        }
        else if (name.equalsIgnoreCase(appConfig.markerFileName))
        {
            mlsDao.updateMarkerDao(value);
        }
    }

    public String extractCounterMarker(String typeFile, String patternFile, String formatFile, String fileName)
    {
        String result = null;
        String[] parts = new String[2];

        Pattern pattern = Pattern.compile(patternFile + "(.*?)" + formatFile);
        Matcher matcher = pattern.matcher(fileName);

        while (matcher.find())
        {
            parts = matcher.group(1).split(appConfig.counterFilePrefix);
        }

        if (typeFile.equalsIgnoreCase(appConfig.counterFileName))
        {
            result = getNumberFromCounter(parts[1]);
        }
        else if (typeFile.equalsIgnoreCase(appConfig.markerFileName))
        {
            result = parts[0];
        }

        return result;
    }

    public boolean downloadFile(boolean pattern, String urlPath, String fileName)
    {
        boolean status = false;

        try
        {
            if (appConfig.settingProxyEnable)
            {
                System.setProperty("https.proxyHost", appConfig.settingProxyHost);
                System.setProperty("https.proxyPort", appConfig.settingProxyPort);
            }

            URL url = new URL(urlPath);
            InputStream inputStream = url.openStream();
            Files.copy(inputStream, Paths.get(appConfig.pathDownload + (pattern ? appConfig.patternFull : appConfig.patternDiff) + fileName +
                            appConfig.formatFileCompress), StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();

            status = true;
        }
        catch (Exception e)
        {
            if (e instanceof FileNotFoundException)
            {
                log.info(env.getProperty("file.exist.download.warning"));
            }
            else
            {
                log.error("downloadFile: {}", e.toString());
            }
        }

        return status;
    }

    private int getMaxDayOfMonth(int year, int month)
    {
        int maxDay;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        return maxDay;
    }

    public boolean checkExistURL(String fullUrl)
    {
        boolean status = false;

        try
        {
            if (appConfig.settingProxyEnable)
            {
                System.setProperty("https.proxyHost", appConfig.settingProxyHost);
                System.setProperty("https.proxyPort", appConfig.settingProxyPort);
            }

            URL url = new URL(fullUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();

            log.debug("URL: {}, status: {}", fullUrl, code);

            if (code != appConfig.responseCodeNotExist)
            {
                status = true;
            }
        }
        catch (IOException e)
        {
            log.error("checkLastUrl: {}", e.toString());
        }

        return status;
    }

    public boolean checkThreshold(boolean statusURL)
    {
        String marker = readMarker();

        int markerYear = 0;
        int markerMonth = 0;
        int markerDate = 0;

        if (!(marker.equalsIgnoreCase("")))
        {
            String[] splitMarker = marker.split("-");

            markerYear = Integer.parseInt(splitMarker[0]);
            markerMonth = Integer.parseInt(splitMarker[1]);
            markerDate = Integer.parseInt(splitMarker[2]);
        }

        //int counter = Integer.parseInt(readCounter());
        //int threshold = appConfig.thresholdLimitMinute;

        //int hour = LocalDateTime.now().getHour();
        //int minute = LocalDateTime.now().getMinute();

        int month = LocalDateTime.now().getMonth().getValue();
        int year = LocalDateTime.now().getYear();
        int date = LocalDateTime.now().getDayOfMonth();

        /*System.out.println("Hour: " + hour + ", Counter: " + counter + ", Minute: " + minute + ", Threshold: " + threshold);
        System.out.println("Date: " + date + ", Month: " + month + ", Year: " + year);
        System.out.println("Date marker: " + markerDate + ", Month marker: " + markerMonth + ", Year marker: " + markerYear);*/

        /*log.debug("Hour: {}, Counter: {}, Minute: {}, Threshold: {}", hour, counter, minute, threshold);
        log.debug("Date: {}, Month: {}, Year: {}", date, month, year);
        log.debug("Date marker: {}, Month marker: {}, Year marker:", markerDate, markerMonth, markerYear);*/

        /*if (!statusURL && (Math.abs(markerDate - date) >= appConfig.thresholdLimitDay)) //constant 3 days long
        {
            return true;
        }
        else if ((hour - counter == 1) && (minute > threshold)) //constant 1 hour different
        {
            //update counter & marker then check next counter & marker
            checkCounterAndMarker();
        }*/
        /*else if ((year != markerYear) || (month != markerMonth))
        {
            return true;
        }*/

        return !statusURL && getDiffDays(markerYear, markerMonth, markerDate, year, month, date) >= appConfig.thresholdLimitDay;
    }

    public boolean checkThresholdHour()
    {
        boolean status = false;
        int counterDb = Integer.parseInt(readCounter());
        int counterTime = LocalDateTime.now().getHour();

        //log.debug("counterDb: {}, counterFileDifferent: {}, counterTime: {}", counterDb, appConfig.counterFileDifferent, counterTime);

        if ((counterDb + appConfig.counterFileTimeDifferent) == counterTime || (counterDb + appConfig.counterFileTimeDifferent) > counterTime)
        {
            status = true;
        }

        return status;
    }

    public boolean extractFile(boolean online, boolean pattern, String fullName)
    {
        boolean status = false;
        String strCompress;
        String strUncompress;

        byte[] buffer = new byte[appConfig.compressBufferSize];

        if (online)
        {
            strCompress = appConfig.pathDownload + (pattern ? appConfig.patternFull : appConfig.patternDiff) + fullName + appConfig.formatFileCompress;
            strUncompress = appConfig.pathDownload + (pattern ? appConfig.patternFull : appConfig.patternDiff) + fullName + appConfig.formatFileOri;
        }
        else
        {
            strCompress = fullName;
            strUncompress = strCompress.replace(appConfig.formatFileCompress, appConfig.formatFileOri);
        }

        try
        {
            GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(strCompress));
            FileOutputStream out = new FileOutputStream(strUncompress);

            int len;
            while ((len = gzis.read(buffer)) > 0)
            {
                out.write(buffer, 0, len);
            }

            gzis.close();
            out.close();

            status = true;
        }
        catch (Exception e)
        {
            log.error("extractFile: {}", e.toString());
        }

        return status;
    }

    public List<String> getListFiles(String pathDownload)
    {
        List<String> listFiles = new ArrayList<>();

        File dir = new File(pathDownload);
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(appConfig.formatFileCompress));

        if (files != null)
        {
            for (File csvFile : files)
            {
                listFiles.add(csvFile.toString());
            }

            Collections.sort(listFiles);
            //Comparator comparator = Collections.reverseOrder();
            //listFiles.sort(comparator);
        }

        return listFiles;
    }

    public List<String> getListAllFiles(String pathDownload)
    {
        List<String> listFiles = new ArrayList<>();

        File dir = new File(pathDownload);
        File[] files = dir.listFiles();

        if (files != null)
        {
            for (File csvFile : files)
            {
                listFiles.add(csvFile.toString());
            }

            Collections.sort(listFiles);
        }

        return listFiles;
    }

    public String splitFileName(String fileName, String strPattern, String strFormat)
    {
        String result = null;

        Pattern pattern = Pattern.compile(strPattern + "(.*?)" + strFormat);
        Matcher matcher = pattern.matcher(fileName);

        while (matcher.find())
        {
            result = matcher.group(1);
        }

        System.out.println("splitFileName: " + result);

        return result;
    }

    public String checkingFileNames(String fileName)
    {
        String result = null;

        if (fileName.contains(appConfig.patternDiff))
        {
            if (fileName.contains(appConfig.formatFileCompress))
            {
                result = splitFileName(fileName, appConfig.patternDiff, appConfig.formatFileCompress);
            }
            else
            {
                result = splitFileName(fileName, appConfig.patternDiff, appConfig.formatFileOri);
            }
        }

        return result;
    }

    public boolean isMlsFile(String fileName)
    {
        boolean result = false;

        if ((fileName.contains(appConfig.patternDiff)) && (fileName.contains(appConfig.formatFileCompress) || fileName.contains(appConfig.formatFileOri)))
        {
            result = true;
        }

        return result;
    }

    public boolean isMlsFullFile(String fileName)
    {
        boolean status = false;

        if ((fileName.contains(appConfig.patternFull)) && (fileName.contains(appConfig.formatFileCompress) || fileName.contains(appConfig.formatFileOri)))
        {
            status = true;
        }

        System.out.println("isMlsFullFile: " + status);

        return status;
    }

    private String getNumberFromCounter(String counter)
    {
        String result;

        result = counter.substring(0, 2);

        if ((result.charAt(0)) == '0')
            result = String.valueOf(result.charAt(1));

        return result;
    }

    public boolean compareCounterMarker(String counterMarker, String counterMarker2)
    {
        boolean status = false;

        String[] parts = counterMarker.split(appConfig.counterFilePrefix);
        String marker = parts[0];
        String counter = getNumberFromCounter(parts[1]);

        String[] parts2 = counterMarker2.split(appConfig.counterFilePrefix);
        String marker2 = parts2[0];
        String counter2 = getNumberFromCounter(parts2[1]);

        if (marker.equalsIgnoreCase(marker2) && (Integer.parseInt(counter2) > Integer.parseInt(counter)))
        {
            status = true;
        }

        return status;
    }

    public boolean compareCounterMarker(String counterMarker)
    {
        boolean status = false;

        String[] parts = counterMarker.split(appConfig.counterFilePrefix);
        String marker = parts[0];
        String counter = getNumberFromCounter(parts[1]);

        System.out.println("marker: " + marker + ", counter: " + counter);
        //log.debug("marker: {}, counter: {}", marker, counter);

        if (checkMarker(marker, counter))
        {
            status = true;
        }

        System.out.println("compareCounterMarker: " + status);

        return status;
    }

    private boolean checkMarker(String marker, String counter)
    {
        boolean status = false;

        String markerDb = readMarker();

        Date dateMarker = parseStringDate(marker);
        Date dateMarkerDb = parseStringDate(markerDb);

        System.out.println("dateMarker: " + dateMarker + ", dateMarkerDb: " + dateMarkerDb);
        //log.debug("dateMarker: {}, dateMarkerDb: {}", dateMarker, dateMarkerDb);

        if ((dateMarker.after(dateMarkerDb)))
        {
            status = true;
        }
        else if (dateMarker.equals(dateMarkerDb))
        {
            status = checkCounter(counter);
        }

        System.out.println("checkMarker: " + status);

        return status;
    }

    private boolean checkCounter(String counter)
    {
        boolean status = false;

        String counterDb = readCounter();

        int intCounter = Integer.parseInt(counter);
        int intCounterDb = Integer.parseInt(counterDb);

        System.out.println("intCounter: " + intCounter + ", intCounterDb: " + intCounterDb);

        if (intCounter > intCounterDb)
        {
            status = true;
        }

        System.out.println("checkCounter: " + status);

        return status;
    }

    private Date parseStringDate(String strDate)
    {
        Date date = null;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try
        {
            date = dateFormat.parse(strDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return date;
    }

    public void deleteFile(String path)
    {
        File file = new File(path);

        if (file.delete())
        {
            log.debug("deleteFile: {} SUCCESS", path);
        }
        else
        {
            log.debug("deleteFile: {} FAILED", path);
        }
    }

    public void checkCounterAndMarker()
    {
        if (readCounter().equalsIgnoreCase(appConfig.counterFileMax))
        {
            resetCounter();
            updateMarker();
        }
        else
        {
            updateCounter();
        }
    }

    //long getDiffDays(String strStartDate, String strEndDate)
    int getDiffDays(int markerStartYear, int markerStartMonth, int markerStartDate, int markerEndYear, int markerEndMonth, int markerEndDate)
    {
        /*String[] splitStartDate = strStartDate.split("-");
        int markerStartYear = Integer.parseInt(splitStartDate[0]);
        int markerStartMonth = Integer.parseInt(splitStartDate[1]);
        int markerStartDate = Integer.parseInt(splitStartDate[2]);

        String[] splitEndDate = strEndDate.split("-");
        int markerEndYear = Integer.parseInt(splitEndDate[0]);
        int markerEndMonth = Integer.parseInt(splitEndDate[1]);
        int markerEndDate = Integer.parseInt(splitEndDate[2]);*/

        /*Calendar start = Calendar.getInstance();
        start.set(markerStartYear, markerStartMonth, markerStartDate);

        Calendar end = Calendar.getInstance();
        end.set(markerEndYear, markerEndMonth, markerEndDate);

        Date startDate = start.getTime();
        Date endDate = end.getTime();

        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long diffTime = endTime - startTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);

        DateFormat dateFormat = DateFormat.getDateInstance();
        System.out.println("The difference between " + dateFormat.format(startDate) + " and " + dateFormat.format(endDate) + " is " + diffDays + " days.");

        return diffDays;*/


        DateTime startDate = new DateTime(markerStartYear, markerStartMonth, markerStartDate, 0, 0, 0, 0);
        DateTime endDate = new DateTime(markerEndYear, markerEndMonth, markerEndDate, 0, 0, 0, 0);

        Days d = Days.daysBetween(startDate, endDate);

        return d.getDays();
    }

    public String getMarkerNextDay(String fileName)
    {
        String strYear, strMonth, strDay;

        String[] output = fileName.split("-");

        int year = Integer.parseInt(output[0]);
        int month = Integer.parseInt(output[1]);
        int day = Integer.parseInt(output[2]);

        int maxDay = getMaxDayOfMonth(year, month);

        //log.debug("year: {}, month: {}, day: {}, maxDay: {}", year, month, day, maxDay);

        if (day == maxDay)
        {
            if (month == 12)
            {
                year = year + 1;
                month = 1;
                day = 1;
            }
            else
            {
                month = month + 1;
                day = 1;
            }
        }
        else
        {
            day = day + 1;
        }

        strYear = String.valueOf(year);

        if (String.valueOf(month).length() == 1)
        {
            strMonth = "0" + String.valueOf(month);
        }
        else
        {
            strMonth = String.valueOf(month);
        }

        if (String.valueOf(day).length() == 1)
        {
            strDay = "0" + String.valueOf(day);
        }
        else
        {
            strDay = String.valueOf(day);
        }

        return strYear + "-" + strMonth + "-" + strDay;
    }
}