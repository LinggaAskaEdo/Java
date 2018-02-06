package com.sql.generator.main;

import com.sql.generator.function.SQLGeneratorFunction;
import com.sql.generator.pojo.ConfigMapper;

import java.util.List;

public class SQLGenerator
{
    public static void main(String[] args)
    {
        SQLGeneratorFunction function = new SQLGeneratorFunction();
        List<ConfigMapper> configMappers = function.processInputFile(args[1], Integer.parseInt(args[2]));

        String clientName = args[0];
        System.out.println("clientName: " + clientName + "\n");

        function.clearFiles("files/resultScript");
        function.clearFiles("files/warningLog");

        for (ConfigMapper mapper : configMappers)
        {
            //System.out.println(mapper.toString());

            String content;
            String generateConfigValueText = null;

            String generateConfigKey = function.generateConfigKey(mapper.getConfigKeyId(), mapper.getModule(), mapper.getKey(), mapper.getDescription(), mapper.getDataType());
            System.out.println("generateConfigKey: " + generateConfigKey);

            if (mapper.getType().equalsIgnoreCase(ConfigMapper.MANUAL_TYPE))
            {
                content = mapper.getContent();
                function.writeToFiles("files/warningLog", ConfigMapper.MANUAL_TYPE + ": " + mapper.getConfigKeyId() + " - " + mapper.getModule() + " - " + mapper.getConfigPath() + " - " + mapper.getConfigKey() + "\n");
            }
            else
            {
                String pathOri = function.generatePathOri(clientName, mapper.getConfigPath());
                content = function.getKeyOri(pathOri, mapper.getConfigKey());
                System.out.println("path: " + pathOri);
                System.out.println("content: " + content);
            }

            if (content.equalsIgnoreCase(""))
            {
                function.writeToFiles("files/warningLog", "ERROR: " + mapper.getConfigKeyId() + " - " + mapper.getModule() + " - " + mapper.getConfigPath() + " - " + mapper.getConfigKey() + "\n");
            }

            generateConfigValueText = function.generateConfigValueText(mapper.getConfigKeyId(), content);
            function.writeToFiles("files/resultScript", generateConfigValueText + "\n");
            System.out.println("generateConfigValueText: " + generateConfigValueText + "\n");
        }
    }
}