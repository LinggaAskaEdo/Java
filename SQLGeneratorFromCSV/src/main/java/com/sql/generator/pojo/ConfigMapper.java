package com.sql.generator.pojo;

public class ConfigMapper
{
    public static final String BASE_CONFIG_PATH = "/home/lingga/git/altamides/altamides-config/src/data/";
    public static final String MANUAL_TYPE = "MANUAL";

    private String configKeyId;
    private String module;
    private String key;
    private String description;
    private String dataType;
    private String configPath;
    private String configKey;
    private String configValueId;
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

    public String getConfigValueId()
    {
        return configValueId;
    }

    public void setConfigValueId(String configValueId)
    {
        this.configValueId = configValueId;
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
                ", configValueId='" + configValueId + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}