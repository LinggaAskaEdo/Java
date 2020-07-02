package org.otis.log.parser.model;

public class LogMapper implements Comparable<LogMapper>
{
    private String date;
    private String time;
    private String endTime;
    private String threadId;
    private String processId;
    private String logLevel;
    private String classHandler;
    private String event;
    private String method;
    private String requestPath;
    private String requestBody;
    private String response;
    private long diff;
    private String fileName;

    public LogMapper()
    {}

    public LogMapper(String date, String time, String threadId, String processId, String event)
    {
        this.date = date;
        this.time = time;
        this.threadId = threadId;
        this.processId = processId;
        this.event = event;
    }

    public LogMapper(String date, String time, String threadId, String processId, String event, String fileName)
    {
        this.date = date;
        this.time = time;
        this.threadId = threadId;
        this.processId = processId;
        this.event = event;
        this.fileName = fileName;
    }

    public LogMapper(String date, String time, String endTime, String threadId, String processId, long diff)
    {
        this.date = date;
        this.time = time;
        this.endTime = endTime;
        this.threadId = threadId;
        this.processId = processId;
        this.diff = diff;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public String getThreadId()
    {
        return threadId;
    }

    public void setThreadId(String threadId)
    {
        this.threadId = threadId;
    }

    public String getProcessId()
    {
        return processId;
    }

    public void setProcessId(String processId)
    {
        this.processId = processId;
    }

    public String getLogLevel()
    {
        return logLevel;
    }

    public void setLogLevel(String logLevel)
    {
        this.logLevel = logLevel;
    }

    public String getClassHandler()
    {
        return classHandler;
    }

    public void setClassHandler(String classHandler)
    {
        this.classHandler = classHandler;
    }

    public String getEvent()
    {
        return event;
    }

    public void setEvent(String event)
    {
        this.event = event;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getRequestPath()
    {
        return requestPath;
    }

    public void setRequestPath(String requestPath)
    {
        this.requestPath = requestPath;
    }

    public String getRequestBody()
    {
        return requestBody;
    }

    public void setRequestBody(String requestBody)
    {
        this.requestBody = requestBody;
    }

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public long getDiff()
    {
        return diff;
    }

    public void setDiff(long diff)
    {
        this.diff = diff;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    @Override
    public String toString()
    {
        return "LogMapper{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", endTime='" + endTime + '\'' +
                ", threadId='" + threadId + '\'' +
                ", processId='" + processId + '\'' +
                ", logLevel='" + logLevel + '\'' +
                ", classHandler='" + classHandler + '\'' +
                ", event='" + event + '\'' +
                ", method='" + method + '\'' +
                ", requestPath='" + requestPath + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", response='" + response + '\'' +
                ", diff=" + diff +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    @Override
    public int compareTo(LogMapper logMapper)
    {
        return this.processId.compareTo(logMapper.processId);
    }
}
