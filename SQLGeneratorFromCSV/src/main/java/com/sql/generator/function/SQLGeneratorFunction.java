package com.sql.generator.function;

import com.sql.generator.pojo.ConfigMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
}