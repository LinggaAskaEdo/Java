package csv;

import java.util.List;

/**
 * Created by edo on 10/01/17.
 */

public class ReportResultCsv
{
    private List<String> data;
    private String message;

    List<String> getData()
    {
        return data;
    }

    void setData(List<String> data)
    {
        this.data = data;
    }

    String getMessage()
    {
        return message;
    }

    void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ReportResultCsv{" + "data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}