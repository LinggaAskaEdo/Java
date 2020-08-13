package com.edelwish.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class EdelwishUtil
{
    public Date generateTimestamp(String eventDateTime)
    {
        Date parsedDate = null;

        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            parsedDate = dateFormat.parse(eventDateTime);
        }
        catch(ParseException pe)
        {
            //Do nothing
        }

        return parsedDate;
    }

    public String generateRandomNumber()
    {
        return UUID.randomUUID().toString();
    }
}