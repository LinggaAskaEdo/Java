package com.sql.generator.function;

import com.opencsv.CSVReader;
import com.sql.generator.pojo.ConfigMapper;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Created by Lingga on 02/02/18.
 */

public class SQLGeneratorFunction
{
    public Properties getPropertiesValues()
    {
        Properties prop = new Properties();

        try
        {
            //load property
            String propFileName = "./application.properties";
            FileInputStream inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);
            inputStream.close();

            //set the property value
            ConfigMapper.CLIENT_NAME = prop.getProperty("client.name");
            ConfigMapper.CSV_FILE_PATH = prop.getProperty("csv.file.path");
            ConfigMapper.CSV_START_READ_LINE = Integer.parseInt(prop.getProperty("csv.start.read.line"));
            ConfigMapper.BASE_CONFIG_PATH = prop.getProperty("base.config.path");
            ConfigMapper.GET_CONFIG_URL = prop.getProperty("get.config.url");
            ConfigMapper.TARGET_WEB_PATH = prop.getProperty("target.web.path");
            ConfigMapper.TARGET_VDIR_PATH = prop.getProperty("target.vdir.path");
            ConfigMapper.SRC_WEB_PATH = prop.getProperty("src.web.path");
            ConfigMapper.SRC_VDIR_PATH = prop.getProperty("src.vdir.path");
            ConfigMapper.FILE_PATH_RESULT_SCRIPT = prop.getProperty("file.path.result.script");
            ConfigMapper.FILE_PATH_WARNING_LOG = prop.getProperty("file.path.warning.log");
        }
        catch (Exception e)
        {
            System.out.println("ERROR getPropertiesValues: " + e.getMessage());
        }

        return prop;
    }

    @SuppressWarnings("deprecation")
    public List<ConfigMapper> processInputFile(String inputFilePath, int skipNumber)
    {
        List<ConfigMapper> inputList = new ArrayList<>();

        try
        {
            // Start with openCSV
            CSVReader reader = new CSVReader(new FileReader(inputFilePath), ',', '\"', '`', skipNumber);
            String[] line;

            while ((line = reader.readNext()) != null)
            {
                inputList.add(setConfigMapperList(line));
            }
        }
        catch (IOException e)
        {
            System.out.println("ERROR processInputFile: " + e.getMessage());
        }

        return inputList;
    }

    private ConfigMapper setConfigMapperList(String[] line)
    {
        ConfigMapper configMapper = new ConfigMapper();
        configMapper.setConfigKeyId(line[0] != null && line[0].trim().length() > 0 ? line[0] : "");
        configMapper.setModule(line[1] != null && line[1].trim().length() > 0 ? line[1] : "");
        configMapper.setKey(line[2] != null && line[2].trim().length() > 0 ? line[2] : "");
        configMapper.setDescription(line[3] != null && line[3].trim().length() > 0 ? line[3] : "");
        configMapper.setDataType(line[4] != null && line[4].trim().length() > 0 ? line[4] : "");
        configMapper.setConfigPath(line[5] != null && line[5].trim().length() > 0 ? line[5] : "");
        configMapper.setConfigKey(line[6] != null && line[6].trim().length() > 0 ? line[6] : "");
        configMapper.setType(line[7] != null && line[7].trim().length() > 0 ? line[7] : "");
        configMapper.setContent(line[8] != null && line[8].trim().length() > 0 ? line[8] : "");

        return configMapper;
    }

    public String generatePathOri(Map<String, Map<String, String>> clientMapper, String clientName, String baseConfigUri)
    {
        String replaceString = null;
        String[] parts = baseConfigUri.split("/");

        Map<String, String> client = clientMapper.get(clientName);

        if (!baseConfigUri.equalsIgnoreCase(""))
        {
            if (client != null && client.get(parts[1]) != null)
            {
                String rootDirectory = client.get(parts[1]);
                replaceString = baseConfigUri.replaceFirst("/" + parts[1] + "/", rootDirectory + "/");
            }
        }

        return ConfigMapper.BASE_CONFIG_PATH + clientName + "/" + replaceString;
    }

    public String getKeyOri(String pathOri, String key)
    {
        String result = null;
        Properties prop = new Properties();
        InputStream input = null;

        try
        {
            input = new FileInputStream(pathOri);

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            result = prop.getProperty(key);

            if (result != null)
                result = prop.getProperty(key);
        }
        catch (IOException ex)
        {
            System.out.println("ERROR getKeyOri: " + ex.getMessage());
        }
        finally
        {
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public String generateConfigKey(String configKeyId, String module, String key, String description, String dataType)
    {
        return "(" + configKeyId + "," + module + "," + key + "," + description + "," + dataType + "),";
    }

    public String generateConfigValueText(String configKeyId, String content)
    {
        final String textDelimiter = "\"";

        if (content == null)
            content = "";

        content = content.replaceAll(Pattern.quote(textDelimiter), "\\\\" + textDelimiter);

        return "(" + configKeyId + "," + configKeyId + "," + textDelimiter + content + textDelimiter + ",0,NOW()),";
    }

    public void clearFiles(String filePath)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,false));
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("ERROR clearFiles: " + e.getMessage());
        }
    }

    public void writeToFiles(String filePath, String contentToAppend)
    {
        File file = new File(filePath);

        try
        {
            //Set the third parameter to true to specify you want to append to file.
            FileUtils.write(file, contentToAppend, "UTF-8", true);
        }
        catch (IOException e)
        {
            System.out.println("ERROR writeToFiles: " + e.getMessage());
        }
    }

    public String sendGet(String pathPHP) throws Exception
    {
        URL obj = new URL(pathPHP);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //optional default is GET
        con.setRequestMethod("GET");

        //add request header
        String USER_AGENT = "Mozilla/5.0";
        con.setRequestProperty("User-Agent", USER_AGENT);

        //check response code
        int responseCode = con.getResponseCode();

        //if response code 500 return null
        if (responseCode == 500)
        {
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }
        in.close();

        //result
        String result = response.toString();

        if ("[[CONFIG_NOT_FOUND/IS_ARRAY]]".equals(result))
        {
            result = null;
        }

        return result;
    }

    @SuppressWarnings("deprecation")
    public String generateUrlPHP(String configKey)
    {
        configKey = URLEncoder.encode(configKey);

        return ConfigMapper.GET_CONFIG_URL + configKey;
    }
}