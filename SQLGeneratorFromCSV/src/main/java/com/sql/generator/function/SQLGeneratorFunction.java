package com.sql.generator.function;

import com.sql.generator.pojo.ConfigMapper;
import org.apache.commons.io.FileUtils;
import sun.nio.cs.UTF_32;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SQLGeneratorFunction
{
    private static final String CSV_SEPARATOR = ",";

    public List<ConfigMapper> processInputFile(String inputFilePath, int skipNumber)
    {
        List<ConfigMapper> inputList = new ArrayList<>();

        try
        {
            File file = new File(inputFilePath);
            InputStream inputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            inputList = bufferedReader.lines().skip(skipNumber).map(mapToItem).collect(Collectors.toList());
            bufferedReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return inputList;
    }

    private Function<String, ConfigMapper> mapToItem = (line) ->
    {
        String[] strings = line.split(CSV_SEPARATOR);
        ConfigMapper mapper = new ConfigMapper();

        //System.out.println("length of p: " + strings.length);
        //System.out.println("p: " + Arrays.toString(strings));

        mapper.setConfigKeyId(strings[0] != null && strings[0].trim().length() > 0 ? strings[0] : "");
        mapper.setModule(strings[1] != null && strings[1].trim().length() > 0 ? strings[1] : "");
        mapper.setKey(strings[2] != null && strings[2].trim().length() > 0 ? strings[2] : "");
        mapper.setDescription(strings[3] != null && strings[3].trim().length() > 0 ? strings[3] : "");
        mapper.setDataType(strings[4] != null && strings[4].trim().length() > 0 ? strings[4] : "");
        mapper.setConfigPath(strings[5] != null && strings[5].trim().length() > 0 ? strings[5] : "");
        mapper.setConfigKey(strings[6] != null && strings[6].trim().length() > 0 ? strings[6] : "");
        mapper.setConfigValueId(strings[7] != null && strings[7].trim().length() > 0 ? strings[7] : "");
        mapper.setContent(strings[8] != null && strings[8].trim().length() > 0 ? strings[8] : "");
        mapper.setType(strings[9] != null && strings[9].trim().length() > 0 ? strings[9] : "");

        return mapper;
    };

    public String generatePathOri(String clientName, String baseConfigUri)
    {
        String replaceString = null;
        String[] parts = baseConfigUri.split("/");

        //System.out.println(baseConfigUri + " - " + parts[1]);

        if (parts[1].equalsIgnoreCase("app"))
        {
            if (clientName.equalsIgnoreCase("KSA-SAU"))
            {
                replaceString = baseConfigUri.replace("/app/", "app.ksa.ams/");
            }
        }
        else if (parts[1].equalsIgnoreCase("web"))
        {
            if (clientName.equalsIgnoreCase("KSA-SAU"))
            {
                replaceString = baseConfigUri.replace("/web/", "web.ksa.ams/");
            }
        }

        return ConfigMapper.BASE_CONFIG_PATH + clientName + "/" + replaceString;
    }

    public String getKeyOri(String pathOri, String key)
    {
        String result = null;
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(pathOri);

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            result = prop.getProperty(key);

            if (result != null)
                result = "'" + prop.getProperty(key) + "'";
            else
                result = "";
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
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
        if (content == null)
            content = "";

        return "(" + configKeyId + "," + configKeyId + "," + content + ",0,NOW()),";
    }

    public void clearFiles(String filePath)
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath,false));
        }
        catch (IOException e)
        {
            e.printStackTrace();
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
            System.out.println("Problem occurs when writeToFiles: " + filePath);
            e.printStackTrace();
        }
    }
}