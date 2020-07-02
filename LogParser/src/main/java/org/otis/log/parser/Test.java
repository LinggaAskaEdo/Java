package org.otis.log.parser;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Test
{
    private static final String[] oriLogs = new String[] { "DEBUG", "INFO" };

    public static void main(String[] args)
    {
        String test = "\u00113#\u0011\u007F,����@�Z���P�\".�D����~-#��<���\u007F�F�l\"%�\u0010\u001F#\f\u007F,�+�����7��M�\u0000\u001D#";
        System.out.println(test + " isPureAscii() : " + isPureAscii(test));

        String test2 = "2020-03-08 16:11:28.120 [421] [1583658688120] INFO id.co.homecredit.creditcard.filter.HttpLoggingFilter - event: START, httpMethod: GET, requestPath: /v1/config/check-eligible, remoteHost: wls100-pdcid2.id.prod, remotePort: 16234, requestParam: , requestBody:";
        if (/*!test2.contains("monitor") && test2.contains("DEBUG") || test2.contains("INFO") &&*/ test2.contains(Arrays.asList(oriLogs).toString()))
            System.out.println("XXX");
        else
            System.out.println("YYY");

        String line = " <filter>";
        if (!line.contains("monitor"))
            System.out.println("AAA1");
        else
            System.out.println("BBB1");

        if (!isPureAscii(line))
            System.out.println("AAA2");
        else
            System.out.println("BBB2");

        if (!Arrays.asList(oriLogs).contains(line))
            System.out.println("AAA3");
        else
            System.out.println("BBB3");

        if (!line.isEmpty())
            System.out.println("AAA4");
        else
            System.out.println("BBB4");

        if (!line.contains("\n"))
            System.out.println("AAA5");
        else
            System.out.println("BBB5");
    }

    private static boolean isPureAscii(String v)
    {
        byte[] bytearray = v.getBytes();
        CharsetDecoder decoder = StandardCharsets.US_ASCII.newDecoder();

        try
        {
            decoder.decode(ByteBuffer.wrap(bytearray));
        }
        catch(CharacterCodingException e)
        {
            return false;
        }

        return true;
    }
}
