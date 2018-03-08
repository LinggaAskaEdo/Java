package com.sql.generator.main;

import com.sql.generator.function.SQLGeneratorFunction;
import com.sql.generator.pojo.ConfigMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Created by Lingga on 02/02/18.
 */

public class SQLGenerator
{
    private static void prepareWebConfigs(String PathOri)
    {
        //Define Directory File Target
        String targetWeb = ConfigMapper.TARGET_WEB_PATH;
        File targetWebDir = new File(targetWeb);
        String targetVdir = ConfigMapper.TARGET_VDIR_PATH;
        File targetVdirDir = new File(targetVdir);

        //Define Source File Target
        String sourceWeb = PathOri + ConfigMapper.SRC_WEB_PATH;
        File srcWebDir = new File(sourceWeb);

        String sourceVdir = PathOri + ConfigMapper.SRC_VDIR_PATH;
        File srcVdirDir = new File(sourceVdir);

        //Delete web directory target
        try
        {
            FileUtils.deleteDirectory(targetWebDir);
            System.out.println("Directory " + targetWeb + " success deleted !");
        }
        catch (Exception e)
        {
            System.out.println("Problem occurs when deleting the directory : " + targetWeb + ", because: " + e.getMessage());
        }

        //Delete vdir directory target
        try
        {
            FileUtils.deleteDirectory(targetVdirDir);
            System.out.println("Directory " + targetVdir + " success deleted !");
        }
        catch (Exception e)
        {
            System.out.println("Problem occurs when deleting the directory : " + targetVdir + ", because: " + e.getMessage());
        }

        //Copy web directory
        try
        {
            FileUtils.copyDirectory(srcWebDir, targetWebDir);
            System.out.println("Directory " + targetWeb + " success copied !");
        }
        catch (IOException e)
        {
            System.out.println("Problem occurs when copying the directory : " + targetWeb + ", because: " + e.getMessage());
        }

        //Copy vdir directory
        try
        {
            FileUtils.copyDirectory(srcVdirDir, targetVdirDir);
            System.out.println("Directory " + targetVdir + " success copied !");
        }
        catch (IOException e)
        {
            System.out.println("Problem occurs when copying the directory : " + targetVdir + ", because: " + e.getMessage());
        }

        System.out.println("");
    }

    public static void main(String[] args) throws Exception
    {
        SQLGeneratorFunction function = new SQLGeneratorFunction();

        //load properties
        Properties properties = function.getPropertiesValues();

        List<ConfigMapper> configMappers = function.processInputFile(ConfigMapper.CSV_FILE_PATH, ConfigMapper.CSV_START_READ_LINE);

        String clientName = ConfigMapper.CLIENT_NAME;
        System.out.println("clientName: " + clientName + "\n");

        //checking files
        File resultScript = new File(ConfigMapper.FILE_PATH_RESULT_SCRIPT);
        File warningLog = new File(ConfigMapper.FILE_PATH_WARNING_LOG);

        if (resultScript.exists())
        {
            function.clearFiles(ConfigMapper.FILE_PATH_RESULT_SCRIPT);
        }
        else
        {
            if (resultScript.createNewFile())
            {
                System.out.println(ConfigMapper.FILE_PATH_RESULT_SCRIPT + " is created!");
            }
            else
            {
                System.out.println(ConfigMapper.FILE_PATH_RESULT_SCRIPT + " already exists.");
            }
        }

        if (warningLog.exists())
        {
            function.clearFiles(ConfigMapper.FILE_PATH_WARNING_LOG);
        }
        else
        {
            if (warningLog.createNewFile())
            {
                System.out.println(ConfigMapper.FILE_PATH_WARNING_LOG + " is created!");
            }
            else
            {
                System.out.println(ConfigMapper.FILE_PATH_WARNING_LOG + " already exists.");
            }
        }

        int idx = 0;
        Map<String, Map<String, String>> clientMapper = new HashMap<>();

        while (properties.getProperty("client." + ++idx) != null)
        {
            String clientMap = properties.getProperty("client." + idx);
            String[] split = clientMap.split(Pattern.quote("^"));

            Map<String, String> client = new HashMap<>();
            clientMapper.put(split[0], client);

            String[] servers = split[1].split(Pattern.quote("|"));

            for (String server : servers)
            {
                String[] serverArr = server.split(Pattern.quote(":"));
                client.put(serverArr[0], serverArr[1]);
            }
        }

        prepareWebConfigs(function.generatePathOri(clientMapper, clientName, "/web/"));

        for (ConfigMapper mapper : configMappers)
        {
            String content;
            String generateConfigValueText;

            String generateConfigKey = function.generateConfigKey(mapper.getConfigKeyId(), mapper.getModule(), mapper.getKey(), mapper.getDescription(), mapper.getDataType());
            System.out.println("generateConfigKey: " + generateConfigKey);

            if (mapper.getType().equalsIgnoreCase(ConfigMapper.MANUAL_TYPE))
            {
                content = mapper.getContent();
                function.writeToFiles(ConfigMapper.FILE_PATH_WARNING_LOG, ConfigMapper.MANUAL_TYPE + ": " + mapper.getConfigKeyId() + " - " + mapper.getModule() + " - " + mapper.getConfigPath() + " - " + mapper.getConfigKey() + "\n");
            }
            else if (mapper.getType().equalsIgnoreCase(ConfigMapper.JAVA_TYPE))
            {
                String pathOri = function.generatePathOri(clientMapper, clientName, mapper.getConfigPath());
                content = function.getKeyOri(pathOri, mapper.getConfigKey());
                System.out.println("path: " + pathOri);
                System.out.println("content: " + content);
            }
            else if (mapper.getType().equalsIgnoreCase(ConfigMapper.PHP_TYPE))
            {
                String pathOri = function.generatePathOri(clientMapper, clientName, mapper.getConfigPath());
                String urlPHP = function.generateUrlPHP(mapper.getConfigKey());
                content = function.sendGet(urlPHP);
                System.out.println("path: " + pathOri);
                System.out.println("content: " + content);
            }
            else if (mapper.getType().equalsIgnoreCase(ConfigMapper.DEFAULT_TYPE))
            {
                content = mapper.getContent();
            }
            else
            {
                content = mapper.getContent();
                function.writeToFiles(ConfigMapper.FILE_PATH_WARNING_LOG, "ERROR: " + mapper.getConfigKeyId() + " - " + mapper.getModule() + " - " + mapper.getConfigPath() + " - " + mapper.getConfigKey() + "\n");
            }

            if (content == null)
            {
                function.writeToFiles(ConfigMapper.FILE_PATH_WARNING_LOG, "ERROR: " + mapper.getConfigKeyId() + " - " + mapper.getModule() + " - " + mapper.getConfigPath() + " - " + mapper.getConfigKey() + "\n");
                content = mapper.getContent();
            }

            generateConfigValueText = function.generateConfigValueText(mapper.getConfigKeyId(), content);
            function.writeToFiles(ConfigMapper.FILE_PATH_RESULT_SCRIPT, generateConfigValueText + "\n");
            System.out.println("generateConfigValueText: " + generateConfigValueText + "\n");
        }
    }
}