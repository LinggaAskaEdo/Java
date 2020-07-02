package org.otis.log.parser;

import org.joda.time.LocalTime;
import org.joda.time.Seconds;
import org.otis.log.parser.model.LogMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test2
{
    public static void main(String[] args) throws ParseException
    {
        List<Integer> removeKeys = new ArrayList<>();

        Map<Integer, LogMapper> xxx = new HashMap<>();
        xxx.put(0, new LogMapper("15/03/20", "14:41:24.145", "13", "15842532143660", "END"));
        xxx.put(1, new LogMapper("15/03/20", "14:42:24.128", "129", "1584258143876", "END"));
        xxx.put(2, new LogMapper("15/03/20", "14:42:24.110", "129", "1584258143876", "START"));
        xxx.put(3, new LogMapper("15/03/20", "14:45:27.110", "200", "1584258143666", "START"));
        xxx.put(4, new LogMapper("15/03/20", "14:45:28.145", "200", "1584258143666", "END"));

        boolean status = false;

        //clear single data map
        for (Map.Entry<Integer, LogMapper> entry : xxx.entrySet())
        {
            System.out.println("XXX: " + entry.getKey());

            int key = entry.getKey();
            String threadProcess = entry.getValue().getThreadId() + entry.getValue().getProcessId();
            String event = entry.getValue().getEvent();

            for (Map.Entry<Integer, LogMapper> entry2 : xxx.entrySet())
            {
                int tempKey = entry2.getKey();
                String tempThreadProcess = entry2.getValue().getThreadId() + entry2.getValue().getProcessId();
                String tempEvent = entry2.getValue().getEvent();

                if (tempThreadProcess.equalsIgnoreCase(threadProcess) && key != tempKey && !event.equalsIgnoreCase(tempEvent))
                {
//                    xxx.put(key, new LogMapper(entry2.getValue().getDate(), entry2.getValue().getTime(), entry2.getValue().getThreadId(), entry2.getValue().getProcessId(), entry2.getValue().getEvent(), "1"));
                    status = true;

                    break;
                }
            }

            if (!status)
            {
                removeKeys.add(key);
            }
        }

        for (Integer val : removeKeys)
        {
            System.out.println("Val: " + val);
            xxx.remove(val);
        }

        System.out.println("\n" + xxx + "\n");

        //sort data maps with processId
        List<LogMapper> logMapperList = new ArrayList<>();
        List<LogMapper> logMappers = new ArrayList<>(xxx.values());
        Collections.sort(logMappers);

        System.out.println(logMappers + "\n");

//        LocalTime startTime = new LocalTime();
//        LocalTime endTime = new LocalTime();
        Date startTime = new Date();
        Date endTime = new Date();
        String strStartTime = "";
        String strEndTime = "";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS");

        //count diff
        for (int i = 0; i < logMappers.size(); i++)
        {
            if (logMappers.get(i).getEvent().equalsIgnoreCase("START"))
            {
                strStartTime = logMappers.get(i).getTime();
//                startTime = new LocalTime(strStartTime);
                startTime = format.parse(strStartTime);
                System.out.println("Start: " + startTime);
            }
            else if (logMappers.get(i).getEvent().equalsIgnoreCase("END"))
            {
                strEndTime = logMappers.get(i).getTime();
//                endTime = new LocalTime(strEndTime);
                endTime = format.parse(strEndTime);
                System.out.println("End: " + endTime);
            }

            if ((i + 1) % 2 == 0)
            {
//                int diffTime = Seconds.secondsBetween(startTime, endTime).getSeconds() % 60;
                long diffTime = endTime.getTime() - startTime.getTime();
                System.out.println("Diff : " + diffTime + " ms\n");

                String date = logMappers.get(i).getDate();
                String threadId = logMappers.get(i).getThreadId();
                String processId = logMappers.get(i).getProcessId();

                logMapperList.addAll(Collections.singleton(new LogMapper(date, strStartTime, strEndTime, threadId, processId, diffTime)));
            }
        }

        System.out.println(logMapperList);
    }
}
