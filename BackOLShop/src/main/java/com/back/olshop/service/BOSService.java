/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.service;

import com.back.olshop.constant.ApplicationStatus;
import com.back.olshop.constant.MessagePreference;
import com.back.olshop.dao.BOSDao;
import com.back.olshop.model.Response;
import com.back.olshop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Calendar;

@Service
public class BOSService
{
    private final Logger log = LoggerFactory.getLogger(BOSService.class);

    @Autowired
    private BOSDao dao;

    public User loadUserByToken(String token)
    {
        return dao.loadUserByToken(token);
    }

    public boolean checkStoreOpen(Time userOpenTime, Time userCloseTime)
    {
        Calendar calendar = Calendar.getInstance();

        int hoursNow = calendar.get(Calendar.HOUR_OF_DAY);

        calendar.setTime(userOpenTime);
        int hourUserOpenTime = calendar.get(Calendar.HOUR_OF_DAY);

        calendar.setTime(userCloseTime);
        int hourUserCloseTime = calendar.get(Calendar.HOUR_OF_DAY);

        log.debug("Open Time: {}", hourUserOpenTime);
        log.debug("Now Time: {}", hoursNow);
        log.debug("Close Time: {}", hourUserCloseTime);

        return hoursNow >= hourUserOpenTime && hoursNow < hourUserCloseTime;
    }

    public Response checkMessage(String message)
    {
        try
        {
            String[] data = message.split("#");

            log.debug("Data length: {}", data.length);

            for (int i = 0; i < data.length; i++)
            {
                log.debug("Data[{}]: {}", i, String.valueOf(data[i]));
            }

            if (data.length - 1 != 8 && data[1] == null && data[2] == null && data[3] == null && data[4] == null && data[5] == null && data[6] == null && data[7] == null
                    && data[8] == null && data[9] == null)
            {
                return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_REQUEST);
            }
            else
            {
                //TODO
                /*
                * IF country ID,
                *
                * */

                String keyword = data[1];
                String name = data[2];
                String bankName = data[3];
                String bankAccountNumber = data[4];
                String address = data[5];
                String district = data[6];
                String province = data[7];
                String country = data[8];
                String order = data[9];

                if (keyword != null && !keyword.equalsIgnoreCase("BELI"))
                {
                    return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_KEYWORD);
                }
                else if (name != null && name.equalsIgnoreCase(""))
                {
                    return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_NAME);
                }
                else if (bankName != null && bankName.equalsIgnoreCase(""))
                {
                    return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_BANK_NAME);
                }
                else if (bankAccountNumber != null && bankAccountNumber.equalsIgnoreCase(""))
                {
                    return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_BANK_ACCOUNT_NUMBER);
                }
                else if (address != null && address.equalsIgnoreCase(""))
                {
                    return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_ADDRESS);
                }
                else if (district != null && district.equalsIgnoreCase(""))
                {
                    return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_DISTRICT);
                }
                else if (province != null && province.equalsIgnoreCase(""))
                {
                    return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_PROVINCE);
                }
                else if (country != null && country.equalsIgnoreCase(""))
                {
                    return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_COUNTRY);
                }
                else if (order != null && order.equalsIgnoreCase(""))
                {
                    return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_ORDER);
                }
                else
                {
                    String orderMessage = generateOrderMessage(name, bankName, bankAccountNumber);

                    return new Response(ApplicationStatus.SUCCESS.toString(), orderMessage);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error when checkMessage: {}", e.getMessage());

            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_REQUEST);
        }
    }

    private String generateOrderMessage(String name, String bankName, String bankAccountNumber)
    {
        return "Pesanan anda akan diproses";
    }
}