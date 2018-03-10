package com.sql.generator.pojo;

/**
 * Created by Lingga on 02/02/18.
 */

public class ConfigMapper
{
    public static final String JAVA_TYPE = "JAVA";
    public static final String PHP_TYPE = "PHP";
    public static final String MANUAL_TYPE = "MANUAL";
    public static final String DEFAULT_TYPE = "DEFAULT";

    public static String CLIENT_NAME;
    public static String CSV_FILE_PATH;
    public static int CSV_START_READ_LINE;
    public static String BASE_CONFIG_PATH;
    public static String GET_CONFIG_URL;
    public static String TARGET_WEB_PATH;
    public static String TARGET_VDIR_PATH;
    public static String SRC_WEB_PATH;
    public static String SRC_VDIR_PATH;
    public static String FILE_PATH_RESULT_SCRIPT;
    public static String FILE_PATH_WARNING_LOG;

    private String configKeyId;
    private String module;
    private String key;
    private String description;
    private String dataType;
    private String configPath;
    private String configKey;
    private String content;
    private String type;

    public String getConfigKeyId()
    {
        return configKeyId;
    }

    public void setConfigKeyId(String configKeyId)
    {
        this.configKeyId = configKeyId;
    }

    public String getModule()
    {
        return module;
    }

    public void setModule(String module)
    {
        this.module = module;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDataType()
    {
        return dataType;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    public String getConfigPath()
    {
        return configPath;
    }

    public void setConfigPath(String configPath)
    {
        this.configPath = configPath;
    }

    public String getConfigKey()
    {
        return configKey;
    }

    public void setConfigKey(String configKey)
    {
        this.configKey = configKey;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ConfigMapper{" +
                "configKeyId='" + configKeyId + '\'' +
                ", module='" + module + '\'' +
                ", key='" + key + '\'' +
                ", description='" + description + '\'' +
                ", dataType='" + dataType + '\'' +
                ", configPath='" + configPath + '\'' +
                ", configKey='" + configKey + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}