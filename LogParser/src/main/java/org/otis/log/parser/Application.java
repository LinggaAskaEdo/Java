package org.otis.log.parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.otis.log.parser.model.LogMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application
{
    private static String[] headerLogs = { "Date", "Time", "Thread Id", "Process Id", "Log Level", "Class Handler", "Event", "Method", "Request Path", "Request Body", "Output/Response" };
    private static String[] headerProcess = { "Date", "Time", "Thread Id", "Process Id", "Event" , "Process Time", "File"};
    private static String[] headerProcessDiff = { "Date", "Start Time", "End Time", "Thread Id", "Process Id", "Diff" };

    private static Map<Integer, LogMapper> bodyLogsMap = new HashMap<>();
    private static Map<Integer, LogMapper> bodyProcessMap = new HashMap<>();
    private static Map<Integer, LogMapper> bodyProcessDiffMap = new HashMap<>();

    static String line;
    private static int indexDelimiter = 8;
    private static int loopLogsIdentifier = 0;
    private static int indexError = 0;
    private static boolean statusError = false;

    private static String dateError = "";
    private static String timeError = "";
    private static String threadIdError = "";
    private static String processIdError = "";
    private static String logLevelError = "";
    private static String classHandlerError = "";
    private static String messageError = "";

    public static void main(String[] args) throws IOException, ParseException
    {
        if (args.length > 0)
        {
            String folderLogs = args[1];
            System.out.println("Path: " + folderLogs + "\n");

            if (args[0].equalsIgnoreCase("1"))
                listingFiles(folderLogs);
            else
                listingFilesClassic(folderLogs);
        }
        else
        {
            System.out.println("Missing Parameter !!!");
        }
    }

    private static void listingFiles(String folderLogs)
    {
        File file = new File(folderLogs);

        if (file.isDirectory())
        {
            try (Stream<Path> walk = Files.walk(Paths.get(folderLogs)))
            {
                List<String> result = walk.map(Path::toString).filter(f -> f.endsWith(".log")).collect(Collectors.toList());

                result.forEach(System.out::println);
            }
            catch (IOException e)
            {
                System.out.println("Error when listing files: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("Not valid folder !!!");
        }
    }

    private static void listingFilesClassic(String folderLogs) throws IOException, ParseException
    {
        File folder = new File(folderLogs);

        List<String> result = new ArrayList<>();

        search(".*\\.log", folder, result);

        for (String s : result)
        {
            System.out.println("Reading file: " + s);

            readUsingScanner(s);
        }

        writeToCsv(folderLogs);
    }

    private static void search(String pattern, File folder, List<String> result)
    {
        for (File f : Objects.requireNonNull(folder.listFiles()))
        {
            if (f.isDirectory())
            {
                search(pattern, f, result);
            }
            else if (f.isFile())
            {
                if (f.getName().matches(pattern))
                {
                    result.add(f.getAbsolutePath());
                }
            }
            else
            {
                System.out.println("Not valid folder !!!");
            }
        }
    }

    private static void readUsingScanner(String fileName)
    {
        Path path = Paths.get(fileName);
        int fileLine = 1;

        try (Scanner scanner = new Scanner(path))
        {
            while (scanner.hasNextLine())
            {
                line = scanner.nextLine();

                if (!line.contains("monitor") && (line.contains("DEBUG") || line.contains("INFO") || statusError))
                {
                    generateLogReport(line);
                    generateProcessTimeReport(line, fileName);

                    loopLogsIdentifier++;
                }

                fileLine++;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error when read using Scanner, line: " + fileLine + ", text: "+ line + ", error: " + e.getMessage());
        }
    }

    private static void generateLogReport(String line)
    {
        if (!line.contains("Error when") && !statusError)
        {
            String[] resultSplitBySpace = splitText(line, indexDelimiter);
            String date = resultSplitBySpace[0];
            String time = resultSplitBySpace[1];
            String threadId = removeFirstLastChar(resultSplitBySpace[2]);
            String processId = removeFirstLastChar(resultSplitBySpace[3]);
            String logLevel = resultSplitBySpace[4];
            String classHandler = resultSplitBySpace[5];

            String tempResultSplitByComma = resultSplitBySpace[indexDelimiter - 1];
            String[] resultSplitByComma = splitText(tempResultSplitByComma);

            if (!resultSplitByComma[0].contains("event"))
            {
                LogMapper mapper = new LogMapper();
                mapper.setDate(date);
                mapper.setTime(time);
                mapper.setThreadId(threadId);
                mapper.setProcessId(processId);
                mapper.setLogLevel(logLevel);
                mapper.setClassHandler(classHandler);
                mapper.setEvent("-");
                mapper.setMethod("-");
                mapper.setRequestPath("-");
                mapper.setRequestBody("-");
                mapper.setResponse(resultSplitByComma[0]);

                bodyLogsMap.put(loopLogsIdentifier, mapper);
            }
            else
            {
                String request = "-";
                String response = "-";
                StringBuilder fullRequest = new StringBuilder();
                StringBuilder fullResponse = new StringBuilder();
                String event = removeStringBy(resultSplitByComma[0], "event: ");
                String method = removeStringBy(resultSplitByComma[1], "httpMethod: ");
                String requestPath = removeStringBy(resultSplitByComma[2], "requestPath: ");

                if (event.contains("START") && method.contains("POST"))
                {
                    for (int i = 6; i < resultSplitByComma.length; i++)
                    {
                        fullRequest.append(resultSplitByComma[i]);
                    }

                    request = removeStringBy(fullRequest.toString(), "requestBody: ");
                }
                else if (event.contains("END"))
                {
                    for (int i = 3; i < resultSplitByComma.length; i++)
                    {
                        fullResponse.append(resultSplitByComma[i]);
                    }

                    response = removeStringBy(fullResponse.toString(), "responseBody: ");
                }

                LogMapper mapper = new LogMapper();
                mapper.setDate(date);
                mapper.setTime(time);
                mapper.setThreadId(threadId);
                mapper.setProcessId(processId);
                mapper.setLogLevel(logLevel);
                mapper.setClassHandler(classHandler);
                mapper.setEvent(event);
                mapper.setMethod(method);
                mapper.setRequestPath(requestPath);
                mapper.setRequestBody(request);
                mapper.setResponse(response);

                bodyLogsMap.put(loopLogsIdentifier, mapper);
            }
        }
        else
        {
            statusError = true;

            if (indexError == 0)
            {
                String[] resultSplitBySpace = splitText(line, indexDelimiter);

                dateError = resultSplitBySpace[0];
                timeError = resultSplitBySpace[1];
                threadIdError = removeFirstLastChar(resultSplitBySpace[2]);
                processIdError = removeFirstLastChar(resultSplitBySpace[3]);
                logLevelError = resultSplitBySpace[4];
                classHandlerError = resultSplitBySpace[5];

                String tempResultSplitByComma = resultSplitBySpace[indexDelimiter - 1];
                String[] resultSplitByComma = splitText(tempResultSplitByComma);

                messageError = resultSplitByComma[0];
            }
            else if (indexError == 1)
            {
                LogMapper mapper = new LogMapper();
                mapper.setDate(dateError);
                mapper.setTime(timeError);
                mapper.setThreadId(threadIdError);
                mapper.setProcessId(processIdError);
                mapper.setLogLevel(logLevelError);
                mapper.setClassHandler(classHandlerError);
                mapper.setEvent("-");
                mapper.setMethod("-");
                mapper.setRequestPath("-");
                mapper.setRequestBody("-");
                mapper.setResponse(messageError + " " + line);

                bodyLogsMap.put(loopLogsIdentifier, mapper);
            }
            else if (indexError > 1 && line.contains("more"))
            {
                indexError = 0;
                statusError = false;
            }

            indexError++;
        }
    }

    private static void generateProcessTimeReport(String line, String fileName)
    {
        if (line.contains("event"))
        {
            String[] resultSplitBySpace = splitText(line, indexDelimiter);
            String date = resultSplitBySpace[0];
            String time = resultSplitBySpace[1];
            String threadId = removeFirstLastChar(resultSplitBySpace[2]);
            String processId = removeFirstLastChar(resultSplitBySpace[3]);

            String tempResultSplitByComma = resultSplitBySpace[indexDelimiter - 1];
            String[] resultSplitByComma = splitText(tempResultSplitByComma);
            String event = removeStringBy(resultSplitByComma[0], "event: ");

            bodyProcessMap.put(loopLogsIdentifier, new LogMapper(date, time, threadId, processId, event, fileName));

            if (!processId.equalsIgnoreCase(""))
                bodyProcessDiffMap.put(loopLogsIdentifier, new LogMapper(date, time, threadId, processId, event));
        }
    }

    private static String[] splitText(String text)
    {
        return text.split(",");
    }

    private static String[] splitText(String text, int index)
    {
        return text.split(" ", index);
    }

    private static String removeFirstLastChar(String full)
    {
        int length = full.length();

        return full.substring(1, length - 1);
    }

    private static String removeStringBy(String full, String replacement)
    {
        return full.replace(replacement, "");
    }

    private static void writeToCsv(String resultPath) throws IOException, ParseException
    {
        System.out.println("\n");

        long startTime;
        long elapsedTime;

        startTime = System.nanoTime();
        createCsv(resultPath, "result-log.csv", headerLogs, bodyLogsMap, 1);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("File " + resultPath + "result-log.csv created, tooks " + (double) elapsedTime / 1_000_000_000 + " secs" );

        startTime = System.nanoTime();
        createCsv(resultPath, "result-process-time.csv", headerProcess, bodyProcessMap, 2);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("File " + resultPath + "result-process-time.csv created, tooks " + (double) elapsedTime / 1_000_000_000 + " secs" );

//        startTime = System.nanoTime();
//        createCsv(resultPath, "result-process-diff-time.csv", headerProcessDiff, normalizeMap(bodyProcessDiffMap), 3);
//        elapsedTime = System.nanoTime() - startTime;
//        System.out.println("File " + resultPath + "result-process-diff-time.csv created, tooks " + (double) elapsedTime / 1_000_000_000 + " secs" );
    }

    private static Map<Integer, LogMapper> normalizeMap(Map<Integer, LogMapper> map) throws ParseException
    {
        List<Integer> removeKeys = new ArrayList<>();
        boolean status = false;

        //find single data in map
        for (Map.Entry<Integer, LogMapper> entry : map.entrySet())
        {
            int key = entry.getKey();
            String threadProcess = entry.getValue().getThreadId() + entry.getValue().getProcessId();
            String event = entry.getValue().getEvent();

            for (Map.Entry<Integer, LogMapper> entry2 : map.entrySet())
            {
                int tempKey = entry2.getKey();
                String tempThreadProcess = entry2.getValue().getThreadId() + entry2.getValue().getProcessId();
                String tempEvent = entry2.getValue().getEvent();

                if (key != tempKey && threadProcess.equalsIgnoreCase(tempThreadProcess) && !event.equalsIgnoreCase(tempEvent))
                {
                    status = true;
                    break;
                }
            }

            if (!status)
            {
                removeKeys.add(key);
            }
        }

        //remove single data in map
        for (Integer val : removeKeys)
        {
            map.remove(val);
        }

        //sort data maps with processId
        List<LogMapper> logMapperList = new ArrayList<>();
        List<LogMapper> logMappers = new ArrayList<>(map.values());
        Collections.sort(logMappers);

        String strStartTime = "";
        String strEndTime = "";
        Date startTime = new Date();
        Date endTime = new Date();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS");

        //count diff
        for (int i = 0; i < logMappers.size(); i++)
        {
            if (logMappers.get(i).getEvent().equalsIgnoreCase("START"))
            {
                strStartTime = logMappers.get(i).getTime();
                startTime = format.parse(strStartTime);
            }
            else if (logMappers.get(i).getEvent().equalsIgnoreCase("END"))
            {
                strEndTime = logMappers.get(i).getTime();
                endTime = format.parse(strEndTime);
            }

            if ((i + 1) % 2 == 0)
            {
                long diffTime = endTime.getTime() - startTime.getTime();

                String date = logMappers.get(i).getDate();
                String threadId = logMappers.get(i).getThreadId();
                String processId = logMappers.get(i).getProcessId();

                logMapperList.addAll(Collections.singleton(new LogMapper(date, strStartTime, strEndTime, threadId, processId, diffTime)));
            }
        }

        //generate new map
        int i = 0;
        Map<Integer, LogMapper> mapperHashMap = new HashMap<>();

        for (LogMapper mapper : logMapperList)
        {
            mapperHashMap.put(i, mapper);
            i++;
        }

        return mapperHashMap;
    }

    private static void createCsv(String path, String fileName, String[] headerProcess, Map<Integer, LogMapper> mapperMap, int code) throws IOException
    {
        FileWriter fileWriter = new FileWriter(path + fileName);

        try (CSVPrinter printer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(headerProcess)))
        {
            mapperMap.forEach((author, mapper) -> {
                try
                {
                    if (code == 1)
                    {
                        printer.printRecord(mapper.getDate(), mapper.getTime(), mapper.getThreadId(), mapper.getProcessId(), mapper.getLogLevel(),
                                mapper.getClassHandler(), mapper.getEvent(), mapper.getMethod(), mapper.getRequestPath(), mapper.getRequestBody(), mapper.getResponse());
                    }
                    else if (code == 2)
                    {
                        printer.printRecord(mapper.getDate(), mapper.getTime(), mapper.getThreadId(), mapper.getProcessId(), mapper.getEvent(), "", mapper.getFileName());
                    }
                    else if (code == 3)
                    {
                        printer.printRecord(mapper.getDate(), mapper.getTime(), mapper.getThreadId(), mapper.getProcessId(), mapper.getEvent(), mapper.getDiff());
                    }
                }
                catch (IOException e)
                {
                    System.out.println("Error when writing CSV: " + e.getMessage());
                }
            });
        }
    }
}