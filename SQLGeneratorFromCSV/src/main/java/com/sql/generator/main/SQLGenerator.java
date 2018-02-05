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

        for (ConfigMapper mapper : configMappers)
        {
            System.out.println(mapper.toString());

            //TODO
            /* check value of content
             *  {if} placeholder format = skip
             *  {else if} type = MANUAL, skip it then log it (keep generate to SQL, use default value)
             *  {else if} not placeholder find based on configPath & configKey
             */
        }
    }
}