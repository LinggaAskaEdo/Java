/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.service;

import com.back.olshop.constant.ApplicationStatus;
import com.back.olshop.constant.CountryCode;
import com.back.olshop.constant.MessagePreference;
import com.back.olshop.constant.MessageType;
import com.back.olshop.dao.BOSDAO;
import com.back.olshop.model.Item;
import com.back.olshop.model.Request;
import com.back.olshop.model.Response;
import com.back.olshop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service
public class BOSService
{
    private final Logger log = LoggerFactory.getLogger(BOSService.class);

    private List<Item> itemList = new ArrayList<>();

    @Autowired
    private BOSDAO dao;

    public User loadUserByToken(String token)
    {
        return dao.loadUserByToken(token);
    }

    public boolean checkTokenExpired(Date userTokenExpired)
    {
        return userTokenExpired.compareTo(new Date(Calendar.getInstance().getTimeInMillis())) < 0;
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

    public Response checkMessage(User user, Request request)
    {
        String token = request.getToken();
        String message = request.getMessage();

        log.debug("token: {}, message: {}", token, message);

        /*categorize message*/
        try
        {
            Response response = new Response();

            String[] data = message.split("#");

            /*for (int i = 0; i < data.length; i++)
            {
                log.debug("Data[{}]: {}", i, String.valueOf(data[i]));
            }*/

            if (data[1] != null && data[1].trim().equalsIgnoreCase(MessageType.MESSAGE_TYPE_BUY))
            {
                response = validationBuyMessage(user.getUserId(), data);
            }
            else if (data[1] != null && data[1].trim().equalsIgnoreCase(MessageType.MESSAGE_TYPE_CHECK))
            {
                response = validationCheckMessage(user.getUserId(), data);
            }
            else
            {
                response.setStatus(ApplicationStatus.FAILED.toString());
                response.setMessage(MessagePreference.MESSAGE_UNKNOWN_KEYWORD);
            }

            return response;
        }
        catch (Exception e)
        {
            log.error("Error when checkMessage: {}", e.getMessage());

            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_REQUEST);
        }
    }

    private Response validationBuyMessage(Integer userId, String[] data)
    {
        try
        {
            if (data[2] != null && data[2].trim().equalsIgnoreCase(CountryCode.COUNTRY_CODE_INDONESIA))
            {
                log.debug("Process order from: {}", data[2]);

                try
                {
                    log.debug("Data length: {}", data.length);

                    /*for (int i = 0; i < data.length; i++)
                    {
                        log.debug("Data[{}]: {}", i, String.valueOf(data[i]));
                    }*/

                    if (data.length - 1 != 9 || data[3] == null || data[4] == null || data[5] == null || data[6] == null || data[7] == null
                            || data[8] == null || data[9] == null)
                    {
                        return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_REQUEST);
                    }
                    else
                    {
                        String name = data[3].trim();
                        String bankName = data[4].trim();
                        String bankAccountNumber = data[5].trim();
                        String address = data[6].trim();
                        String district = data[7].trim();
                        String province = data[8].trim();
                        String order = data[9].trim();

                        if (name.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_NAME);
                        }
                        else if (bankName.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_BANK_NAME);
                        }
                        else if (bankAccountNumber.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_BANK_ACCOUNT_NUMBER);
                        }
                        else if (bankAccountNumber.matches(".*[a-zA-Z].*"))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_INVALID_BANK_ACCOUNT_NUMBER);
                        }
                        else if (address.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_ADDRESS);
                        }
                        else if (district.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_DISTRICT);
                        }
                        else if (province.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_PROVINCE);
                        }
                        else if (checkRegion(district, province))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_UNKNOWN_REGION);
                        }
                        else if (order.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_ORDER);
                        }
                        /*else if (checkFormatOrder(order))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_INVALID_ORDER);
                        }*/
                        else
                        {
                            /*check existing item*/
                            /*generate number*/

                            //String orderMessage = generateOrderMessage(name, bankName, bankAccountNumber);

                            //return new Response(ApplicationStatus.SUCCESS.toString(), orderMessage);
                            return processOrder(userId, order);
                        }
                    }
                }
                catch (Exception e)
                {
                    log.error("Error when validationBuyMessage IN: {}", e.getMessage());

                    return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_REQUEST);
                }
            }
            else if (data[2] != null && Arrays.asList(CountryCode.countryArrays).contains(data[2].trim()))
            {
                log.debug("Process order from: {}", data[2]);

                try
                {
                    log.debug("Data length: {}", data.length);

                    for (int i = 0; i < data.length; i++)
                    {
                        log.debug("Data[{}]: {}", i, String.valueOf(data[i]));
                    }

                    if (data.length - 1 != 7 || data[3] == null || data[4] == null || data[5] == null || data[6] == null || data[7] == null)
                    {
                        return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_REQUEST);
                    }
                    else
                    {
                        String name = data[3].trim();
                        String bankName = data[4].trim();
                        String bankAccountNumber = data[5].trim();
                        String address = data[6].trim();
                        String order = data[7].trim();

                        if (name.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_NAME);
                        }
                        else if (bankName.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_BANK_NAME);
                        }
                        else if (bankAccountNumber.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_BANK_ACCOUNT_NUMBER);
                        }
                        else if (bankAccountNumber.matches(".*[a-zA-Z].*"))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_INVALID_BANK_ACCOUNT_NUMBER);
                        }
                        else if (address.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_ADDRESS);
                        }
                        else if (order.equalsIgnoreCase(""))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_EMPTY_ORDER);
                        }
                        else if (checkFormatOrder(order))
                        {
                            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_ERROR_INVALID_ORDER);
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
                    log.error("Error when validationBuyMessage IN: {}", e.getMessage());

                    return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_REQUEST);
                }
            }
            else
            {
                return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_UNKNOWN_COUNTRY);
            }
        }
        catch (Exception e)
        {
            log.error("Error when validationBuyMessage: {}", e.getMessage());

            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_REQUEST);
        }
    }

    private Response processOrder(Integer userId, String order)
    {
        try
        {
            int separator = order.indexOf(',');

            if (separator >= 0)
            {
                String[] arrOrders = order.split(",");

                for (String arrOrder : arrOrders)
                {
                    log.debug("arrOrders: {}", arrOrder);

                    String[] orders = arrOrder.split("-");

                    log.debug("orders[0]: {}, orders[1]: {}, orders[2]: {}", orders[0].trim(), orders[1].trim(), orders[2].trim());

                    if (orders[0] != null && orders[1] != null && orders[2] != null)
                    {
                        String codeItem = orders[0].trim();
                        String sizeItem = orders[1].trim();
                        int totalItem = Integer.parseInt(orders[2].trim());

                        boolean status = dao.checkItem(userId, codeItem, sizeItem, totalItem);

                        /*TODO
                        * 1. After checking stock, automatically update stock */

                        if (status)
                        {
                            Item item = new Item();
                            item.setItemCode(orders[0]);
                            item.setItemSize(orders[1]);
                            item.setItemTotal(Integer.parseInt(orders[2]));
                            itemList.add(item);
                        }
                        else
                        {
                            itemList.clear();

                            String message = "Item with code: " + codeItem + " and size: " + sizeItem + ", not available";

                            return new Response(ApplicationStatus.FAILED.toString(), message);
                        }
                    }
                }
            }
            else
            {
                String[] arrOrder = order.split("-");

                log.debug("arrOrder[0]: {}, arrOrder[1]: {}, arrOrder[2]: {}", arrOrder[0].trim(), arrOrder[1].trim(), arrOrder[2].trim());

                if (arrOrder[0] != null && arrOrder[1] != null && arrOrder[2] != null)
                {
                    String codeItem = arrOrder[0].trim();
                    String sizeItem = arrOrder[1].trim();
                    int totalItem = Integer.parseInt(arrOrder[2].trim());

                    boolean status = dao.checkItem(userId, codeItem, sizeItem, totalItem);

                    if (status)
                    {
                        Item item = new Item();
                        item.setItemCode(arrOrder[0]);
                        item.setItemSize(arrOrder[1]);
                        item.setItemTotal(Integer.parseInt(arrOrder[2]));
                        itemList.add(item);
                    }
                    else
                    {
                        itemList.clear();

                        String message = "Item with code: " + arrOrder[0] + " and size: " + arrOrder[1] + ", not available";

                        return new Response(ApplicationStatus.FAILED.toString(), message);
                    }
                }
            }

            /*TODO
            * 1. */

            return new Response(ApplicationStatus.SUCCESS.toString(), MessagePreference.MESSAGE_PROCESS_ORDER);
        }
        catch (Exception e)
        {
            log.error("Error on processOrder: {}", e.getMessage());

            return new Response(ApplicationStatus.FAILED.toString(), MessagePreference.MESSAGE_INVALID_REQUEST);
        }
    }

    private boolean checkRegion(String district, String province)
    {
        return dao.checkRegion(district, province) > 0;
    }

    private boolean checkFormatOrder(String order)
    {
        boolean status = true;

        try
        {
            int separator = order.indexOf(',');

            if (separator >= 0)
            {
                String[] arrOrders = order.split(",");

                for (String arrOrder : arrOrders)
                {
                    log.debug("arrOrders: {}", arrOrder);

                    String[] orders = arrOrder.split("-");

                    log.debug("orders[0]: {}, orders[1]: {}, orders[2]: {}", orders[0].trim(), orders[1].trim(), orders[2].trim());

                    if (orders[0] != null && orders[1] != null && orders[2] != null)
                    {
                        status = true;
                    }
                }
            }
            else
            {
                String[] arrOrder = order.split("-");

                log.debug("arrOrder[0]: {}, arrOrder[1]: {}, arrOrder[2]: {}", arrOrder[0].trim(), arrOrder[1].trim(), arrOrder[2].trim());

                if (arrOrder[0] != null && arrOrder[1] != null && arrOrder[2] != null)
                {
                    status = false;
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error on checkFormatOrder: {}", e.getMessage());
        }

        return status;
    }

    private Response validationCheckMessage(Integer userId, String[] data)
    {
        Response response = new Response();

        try
        {
            if (data.length - 1 == 3 || data[1] != null || data[2] != null || data[3] != null)
            {
                /*check data from database*/
                String keyword = data[1].trim();
                String codeName = data[2].trim();
                String size = data[3].trim();

                log.debug("keyword: {}, codeName: {}, size: {}", keyword, codeName, size);

                Integer totalItem = dao.checkItem(userId, codeName, size);

                if (totalItem == 1)
                {
                    String message = "Code item: " + codeName + ", size: " + size + ", there is 1 item";

                    response.setStatus(ApplicationStatus.SUCCESS.toString());
                    response.setMessage(message);
                }
                else if (totalItem > 1)
                {
                    String message = "Code item: " + codeName + ", size: " + size + ", there are " + totalItem + " items";

                    response.setStatus(ApplicationStatus.SUCCESS.toString());
                    response.setMessage(message);
                }
                else
                {
                    response.setStatus(ApplicationStatus.SUCCESS.toString());
                    response.setMessage(MessagePreference.MESSAGE_ERROR_EMPTY_ITEM);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error when validationCheckMessage: {}", e.getMessage());

            response.setStatus(ApplicationStatus.FAILED.toString());
            response.setMessage(MessagePreference.MESSAGE_INVALID_REQUEST);
        }

        return response;
    }

    private String generateOrderMessage(String name, String bankName, String bankAccountNumber)
    {
        return "Pesanan anda akan diproses";
    }
}