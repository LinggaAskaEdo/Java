/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.service;

import org.springframework.stereotype.Service;

@Service
public class BOSService
{
    public boolean checkMessage(String message)
    {
        String[] data = message.split("#");

        System.out.println("Data length: " + data.length);

        for (int i = 0; i < data.length; i++)
        {
            System.out.println("Data[" + i + "]" + String.valueOf(data[i]));
        }

        return data.length - 1 == 8;
    }
}