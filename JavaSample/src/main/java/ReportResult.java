import java.util.List;

/**
 * Created by Lingga on 10/01/17.
 */

public class ReportResult
{
    private List<String> data;
    private String message;

    public List<String> getData()
    {
        return data;
    }

    public void setData(List<String> data)
    {
        this.data = data;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ReportResult{" + "data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}