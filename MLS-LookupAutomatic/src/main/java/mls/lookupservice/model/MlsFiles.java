package mls.lookupservice.model;

import java.util.Comparator;

/**
 * Created by Lingga on 20/04/17.
 */

public class MlsFiles
{
    public static final String TYPE_A = "A";
    public static final String TYPE_B = "B";

    private String path;
    private String type;

    public MlsFiles(String path, String type)
    {
        this.path = path;
        this.type = type;
    }

    public String getPath()
    {
        return path;
    }

    public static Comparator<MlsFiles> COMPARE_BY_PATH = Comparator.comparing(one -> one.path);

    public static Comparator<MlsFiles> COMPARE_BY_TYPE = Comparator.comparing(one -> one.type);
}