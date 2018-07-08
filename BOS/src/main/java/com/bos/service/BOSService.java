/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.bos.service;

import com.bos.constant.CountryCode;
import com.bos.constant.MessagePreference;
import com.bos.constant.MessageType;
import com.bos.constant.ShippingType;
import com.bos.dao.BOSDAO;
import com.bos.model.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BOSService
{
    private final Logger log = LoggerFactory.getLogger(BOSService.class);

    private static final String JASA_TITIP_FORMAT = "JST";
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final double LIMIT_COMMA_NUMBER = 0.2;

    private double totalWeight;
    private int roundTotalWeight, totalPrice;
    private Integer totalShipping;
    private List<Item> itemList = new ArrayList<>();
    private String shippingType;

    @Autowired
    private BOSDAO dao;

    @Autowired
    private SCPService scp;

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

    public boolean checkOpenPO(java.util.Date dateNow, java.util.Date dateOpen)
    {
        boolean status = false;

        log.debug("Date now: {}", dateNow);
        log.debug("Date open: {}", dateOpen);

        if (dateNow.compareTo(dateOpen) >= 0)
        {
            log.debug("dateNow is before dateOpen");
            status = true;
        }

        return status;
    }

    public String checkMessage(User user, Request request)
    {
        String response;
        String phoneNumber = request.getPhone();
        String token = request.getToken();
        String message = request.getMessage();

        log.debug("token: {}, message: {}", token, message);

        /*categorize message*/
        try
        {
            /*reset to default*/
            totalWeight = 0;
            totalShipping = 0;
            totalPrice = 0;
            roundTotalWeight = 0;
            itemList.clear();

            String[] data = message.split("#");

            if (data[1] != null && data[1].trim().equalsIgnoreCase(MessageType.MESSAGE_TYPE_BUY))
            {
                response = validationBuyMessage(user.getUserId(), data, phoneNumber);
            }
            else if (data[1] != null && data[1].trim().equalsIgnoreCase(MessageType.MESSAGE_TYPE_CHECK))
            {
                response = validationCheckMessage(user.getUserId(), data);
            }
            else
            {
                response = MessagePreference.MESSAGE_UNKNOWN_KEYWORD;
            }
        }
        catch (Exception e)
        {
            log.error("Error when checkMessage: {}", e);

            response = generateMessage();
        }

        return response;
    }

    private String validationBuyMessage(Integer userId, String[] data, String phoneNumber)
    {
        String response;

        try
        {
            Client client = new Client();

            if (data[2] != null && data[2].trim().equalsIgnoreCase(CountryCode.COUNTRY_CODE_INDONESIA))
            {
                log.debug("Process order from: {}", data[2]);

                //set client country
                client.setClientCountry(CountryCode.COUNTRY_CODE_INDONESIA);

                try
                {
                    log.debug("Data length: {}", data.length);

                    if (data.length - 1 < 9 || data[3] == null || data[4] == null || data[5] == null || data[6] == null || data[7] == null
                            || data[8] == null || data[9] == null)
                    {
                        return generateMessage();
                    }
                    else
                    {
                        String name = data[3].trim();
                        String bankName = data[4].trim();
                        String bankAccountNumber = data[5].trim();
                        String address = data[6].trim();
                        String district = data[7].trim();
                        String city = data[8].trim();
                        String order = data[9].trim();

                        if (name.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_NAME;
                        }
                        else if (bankName.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_BANK_NAME;
                        }
                        else if (bankAccountNumber.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_BANK_ACCOUNT_NUMBER;
                        }
                        else if (bankAccountNumber.matches(".*[a-zA-Z].*"))
                        {
                            return MessagePreference.MESSAGE_ERROR_INVALID_BANK_ACCOUNT_NUMBER;
                        }
                        else if (address.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_ADDRESS;
                        }
                        else if (district.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_DISTRICT;
                        }
                        else if (city.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_CITY;
                        }
                        else if (!checkRegion(district, city))
                        {
                            return MessagePreference.MESSAGE_UNKNOWN_REGION;
                        }
                        else if (order.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_ORDER;
                        }
                        else
                        {
                            try
                            {
                                if (data[10] != null && containsCaseInsensitive(data[10].trim(), Arrays.asList(ShippingType.shippingArrays)))
                                {
                                    shippingType = data[10].trim();
                                }
                                else
                                {
                                    return MessagePreference.MESSAGE_INVALID_SHIPPING_TYPE;
                                }
                            }
                            catch (Exception e)
                            {
                                shippingType = ShippingType.SHIPPING_TYPE_REG;
                            }

                            try
                            {
                                //set another client field
                                if (StringUtils.containsIgnoreCase(name, JASA_TITIP_FORMAT))
                                {
                                    String[] nameWithJstFormat = name.split("-");

                                    if (nameWithJstFormat.length == 3 && nameWithJstFormat[0].equalsIgnoreCase(JASA_TITIP_FORMAT))
                                    {
                                        client.setClientName(nameWithJstFormat[1]);
                                        client.setClientHp(nameWithJstFormat[2]);
                                    }
                                    else
                                    {
                                        return generateMessage();
                                    }
                                }
                                else
                                {
                                    client.setClientName(name);
                                    client.setClientHp(phoneNumber);
                                }

                                client.setClientBankName(bankName);
                                client.setClientBankNumber(bankAccountNumber);
                                client.setClientAddress(address);
                                client.setClientDistricts(district);
                                client.setClientCity(city);
                            }
                            catch (Exception e)
                            {
                                return generateMessage();
                            }

                            return processOrder(userId, order, client);
                        }
                    }
                }
                catch (Exception e)
                {
                    log.error("Error when validationBuyMessage IN: {}", e);

                    return generateMessage();
                }
            }
            else if (data[2] != null && Arrays.asList(CountryCode.countryArrays).contains(data[2].trim()))
            {
                log.debug("Process order from: {}", data[2]);

                //set client country
                client.setClientCountry(data[2]);

                try
                {
                    log.debug("Data length: {}", data.length);

                    if (data.length - 1 != 7 || data[3] == null || data[4] == null || data[5] == null || data[6] == null || data[7] == null)
                    {
                        return generateMessage();
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
                            return MessagePreference.MESSAGE_ERROR_EMPTY_NAME;
                        }
                        else if (bankName.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_BANK_NAME;
                        }
                        else if (bankAccountNumber.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_BANK_ACCOUNT_NUMBER;
                        }
                        else if (bankAccountNumber.matches(".*[a-zA-Z].*"))
                        {
                            return MessagePreference.MESSAGE_ERROR_INVALID_BANK_ACCOUNT_NUMBER;
                        }
                        else if (address.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_ADDRESS;
                        }
                        else if (order.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_ORDER;
                        }
                        else
                        {
                            //set another client field
                            client.setClientName(name);
                            client.setClientHp(phoneNumber);
                            client.setClientBankName(bankName);
                            client.setClientBankNumber(bankAccountNumber);
                            client.setClientAddress(address);

                            return processOrder(userId, order, client);
                        }
                    }
                }
                catch (Exception e)
                {
                    log.error("Error when validationBuyMessage IN: {}", e);

                    return generateMessage();
                }
            }
            else
            {
                return MessagePreference.MESSAGE_UNKNOWN_COUNTRY;
            }
        }
        catch (Exception e)
        {
            log.error("Error when validationBuyMessage: {}", e);

            return generateMessage();
        }
    }

    private boolean containsCaseInsensitive(String shippingType, List<String> shippingList)
    {
        return shippingList.stream().anyMatch(x -> x.equalsIgnoreCase(shippingType));
    }

    private String processOrder(Integer userId, String order, Client client)
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

                        Item item = dao.getItem(userId, codeItem, sizeItem);

                        if (item != null)
                        {
                            if (item.getItemStock() >= totalItem && item.getItemStock() > 0 && totalItem > 0)
                            {
                                log.debug("Item: {}", item.toString());

                                int newStock = item.getItemStock() - totalItem;

                                Item newItem = new Item();
                                newItem.setUserId(userId);
                                newItem.setItemId(item.getItemId());
                                newItem.setItemCode(codeItem);
                                newItem.setItemSize(sizeItem);
                                newItem.setItemTotal(totalItem);
                                newItem.setItemTotalOld(newStock);
                                newItem.setItemPrice(item.getItemPrice());
                                newItem.setItemWeight(item.getItemWeight());
                                itemList.add(newItem);

                                dao.updateStock(userId, codeItem, sizeItem, newStock);
                            }
                            else
                            {
                                /*return the stock*/
                                returnStocks();
                                itemList.clear();

                                return "Item dengan kode: " + codeItem + " dan ukuran: " + sizeItem + ", tersisa " + item.getItemStock() + " buah";
                            }
                        }
                        else
                        {
                            itemList.clear();

                            return "Item dengan kode: " + codeItem + " tidak tersedia";
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

                    Item item = dao.getItem(userId, codeItem, sizeItem);

                    if (item != null)
                    {
                        if (item.getItemStock() >= totalItem)
                        {
                            log.debug("Item: {}", item.toString());

                            int newStock = item.getItemStock() - totalItem;

                            Item newItem = new Item();
                            newItem.setUserId(userId);
                            newItem.setItemId(item.getItemId());
                            newItem.setItemCode(codeItem);
                            newItem.setItemSize(sizeItem);
                            newItem.setItemTotal(totalItem);
                            newItem.setItemTotalOld(newStock);
                            newItem.setItemPrice(item.getItemPrice());
                            newItem.setItemWeight(item.getItemWeight());
                            itemList.add(newItem);

                            dao.updateStock(userId, codeItem, sizeItem, newStock);
                        }
                        else
                        {
                            itemList.clear();

                            return "Item dengan kode: " + arrOrder[0] + " dan ukuran: " + arrOrder[1] + ", tersisa " + item.getItemStock() + " buah";
                        }
                    }
                    else
                    {
                        itemList.clear();

                        return "Item dengan kode: " + arrOrder[0] + " tidak tersedia";
                    }
                }
            }

            /*
             * 1. Generate transaction code
             * 2. Count total weight, price & shipping price
             * 2. Save data to all database related
             * 3. Clear list
             * */

            String transactionNumber = generateTransactionNumber();
            log.debug("transactionNumber: {}", transactionNumber);

            countTotalWeightPrice(client.getClientCountry(), itemList);
            log.debug("totalWeight: {}", totalWeight);
            log.debug("roundTotalWeight: {}", roundTotalWeight);
            log.debug("totalPrice: {}", totalPrice);

            if (shippingType.equalsIgnoreCase(ShippingType.SHIPPING_TYPE_CARGO) && roundTotalWeight < 7)
            {
                shippingType = ShippingType.SHIPPING_TYPE_REG;
            }
            else if (shippingType.equalsIgnoreCase(ShippingType.SHIPPING_TYPE_BEST))
            {
                if (dao.isSupportBest(client.getClientDistricts(), client.getClientCity()))
                {
                    shippingType = ShippingType.SHIPPING_TYPE_BEST;
                }
                else
                {
                    shippingType = ShippingType.SHIPPING_TYPE_REG;
                }
            }

            log.debug("shippingType: {}", shippingType);

            if (client.getClientCountry().equalsIgnoreCase(CountryCode.COUNTRY_CODE_INDONESIA))
            {
                /*find with API first, if response null, find in database*/
                Response response = scp.getTarif(client.getClientDistricts(), client.getClientCity());

                log.debug("responseGetTarif:{}", response);

                if (response.getSicepat() != null && response.getSicepat().getStatus().getCode() == 200)
                {
                    log.debug("get total shipping from API");

                    //extracting response list
                    List<Results> results = response.getSicepat().getResults().stream()
                            .filter(r -> r.getService().equalsIgnoreCase(shippingType))
                            .collect(Collectors.toList());

                    totalShipping = results.get(0).getTariff();
                }
                else
                {
                    log.debug("get total shipping from database");
                    totalShipping = dao.countShippingIn(client.getClientDistricts(), client.getClientCity(), shippingType);
                }
            }
            else if (Arrays.asList(CountryCode.countryArrays).contains(client.getClientCountry()))
            {
                totalShipping = dao.countShippingOut(client.getClientCountry());
            }

            log.debug("totalShipping: {}", totalShipping);

            if (totalShipping <= 0 || totalShipping == null)
            {
                returnStocks();
                itemList.clear();

                return MessagePreference.MESSAGE_FAILED_COUNT;
            }

            //generate 3 unique number
            int unique = getRandomNumberInRange();
            totalPrice = totalPrice + unique;

            /*
            save everything to database
            1. Save into table client
            2. Save into table transaction
            3. Save into table order
            */
            Integer clientId = dao.saveClient(client);
            log.debug("clientId: {}", clientId);

            String message;

            if (clientId != null && clientId > 0)
            {
                Integer transactionId = dao.saveTransaction(userId, clientId, transactionNumber, shippingType,
                        roundTotalWeight > 0 ? totalShipping * roundTotalWeight : totalShipping, roundTotalWeight, unique);

                log.debug("transactionId: {}", transactionId);

                if (transactionId != null && transactionId > 0)
                {
                    List<Integer> orderIds = dao.saveOrder(transactionId, itemList);
                    log.debug("orderId: {}", orderIds);

                    message = generateMessage(client, transactionNumber);
                }
                else
                {
                    message =  MessagePreference.MESSAGE_FAILED_PROCESS_TRANSACTION;
                    returnStocks();
                }
            }
            else
            {
                message =  MessagePreference.MESSAGE_FAILED_PROCESS_CLIENT;
                returnStocks();
            }

            itemList.clear();

            return message;
        }
        catch (Exception e)
        {
            log.error("Error on processOrder: {}", e);

            returnStocks();
            itemList.clear();

            return MessagePreference.MESSAGE_ERROR_PROCESS_KEYWORD;
        }
    }

    private String generateMessage(Client client, String transactionNumber)
    {
        String separator = System.lineSeparator();

        Integer fixTotal = roundTotalWeight > 0 ? totalShipping * roundTotalWeight : totalShipping;

        StringBuilder builder = new StringBuilder();
        builder.append("Assalamu'alaikum, ").append(client.getClientName()).append(separator);
        builder.append("Transaksi anda untuk no. Order (").append(transactionNumber).append("): ").append(separator);

        int i = 1;

        for (Item item : itemList)
        {
            builder.append(i).append(". kode barang: ").append(item.getItemCode()).append(", ukuran: ").append(item.getItemSize()).append(", jumlah: ")
                    .append(item.getItemTotal());
            builder.append(separator);
            i++;
        }

        builder.append(separator);
        builder.append("Total belanja: Rp. ").append(NumberFormat.getNumberInstance(Locale.US).format(totalPrice)).append(separator);
        builder.append("Total biaya pengiriman: Rp. ").append(NumberFormat.getNumberInstance(Locale.US).format(fixTotal)).append(separator);
        builder.append("Total keseluruhan: Rp. ").append(NumberFormat.getNumberInstance(Locale.US).format(totalPrice + fixTotal))
                .append(separator).append(separator);
        builder.append("Mohon transfer ke rekening Bank Mandiri atas nama Ayuka Winda Kharisma 1560002743930, sesuai totalan berikut dengan kode unik yang diberikan. " +
                "Ini memudahkan kami dalam pengecekan transferan. " +
                "Dan mengenai nominal kode unik. Seluruh kode unik tersebut akan kami total dan kami sedekahkan setiap bulannya.\n" +
                "Dan untuk batas transfer hanya 4 jam dari dikirimkannya invoice ini. Selebihnya kami anggap batal. " +
                "Wajib untuk mengirimkan bukti transfer ke WA : 087808731559 (wajib sertakan no.order). Mohon maaf kurang lebihnya.");

        return builder.toString();
    }

    private String generateMessage()
    {
        String separator = System.lineSeparator();

        StringBuilder builder = new StringBuilder();
        builder.append("Mohon maaf format tidak terdeteksi. Untuk bantuan langsung wa ke 087808731559");

        return builder.toString();
    }

    private int getRandomNumberInRange()
    {
        boolean status = true;
        int min = 100;
        int max = 999;
        int unique = 0;

        while (status)
        {
            Random r = new Random();
            unique = r.ints(min, (max + 1)).findFirst().getAsInt();

            if (!String.valueOf(unique).contains("0"))
            {
                log.debug("unique number: {}", unique);
                status = false;
            }
        }

        return unique;
    }

    private void returnStocks()
    {
        if (itemList.size() > 0)
        {
            for (Item itemStocks : itemList)
            {
                log.debug("userId: {}, itemCode: {}, itemSize: {}, itemTotal: {}, itemTotalOld: {}", itemStocks.getUserId(), itemStocks.getItemCode(),
                        itemStocks.getItemSize(), itemStocks.getItemTotal(), itemStocks.getItemTotalOld());

                dao.updateStock(itemStocks.getUserId(), itemStocks.getItemCode(), itemStocks.getItemSize(),
                        itemStocks.getItemTotal() + itemStocks.getItemTotalOld());
            }
        }
    }

    private void countTotalWeightPrice(String countryCode, List<Item> itemList)
    {
        for (Item item : itemList)
        {
            totalWeight += item.getItemWeight() * item.getItemTotal();
            totalPrice += item.getItemPrice() * item.getItemTotal();
        }

        double valueAfterDot = (totalWeight - Math.floor(totalWeight));
        String strDouble = new Double(totalWeight).toString();
        String str = strDouble.substring(0, strDouble.indexOf('.'));

        if (valueAfterDot < LIMIT_COMMA_NUMBER)
        {
            roundTotalWeight = Integer.parseInt(str);
        }
        else
        {
            roundTotalWeight = Integer.parseInt(str) + 1;
        }
    }

    private String generateTransactionNumber()
    {
        /*Format transaction number
         * 1. Header
         * 2. Year: only last 2 digit number
         * 3. Month: 2 digit number
         * 4. Date: 2 digit number
         * 5. Hour: 2 digit number
         * 6. Minute: 2 digit number
         * 7. Unique: 3 digit alphanumeric
         * */

        String header = "INV";
        String separator = "//";

        Calendar now = Calendar.getInstance();
        String strYear = String.valueOf(now.get(Calendar.YEAR));
        String strMonth = String.valueOf(now.get(Calendar.MONTH));
        String strDay = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
        String strHour = String.valueOf(now.get(Calendar.HOUR_OF_DAY));
        String strMinute = String.valueOf(now.get(Calendar.MINUTE));

        return header + separator +
                strYear.substring(strYear.length() - 2) +
                (strMonth.length() == 2 ? strMonth : "0" + strMonth) +
                (strDay.length() == 2 ? strDay : "0" + strDay) +
                (strHour.length() == 2 ? strHour : "0" + strHour) +
                (strMinute.length() == 2 ? strMinute : "0" + strMinute) +
                separator + generateRandomString();
    }

    private String generateRandomString()
    {
        int count = 3;

        StringBuilder builder = new StringBuilder();

        while (count-- != 0)
        {
            int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }

        return builder.toString();
    }

    private boolean checkRegion(String district, String city)
    {
        return dao.checkRegion(district, city) > 0;
    }

    private String validationCheckMessage(Integer userId, String[] data)
    {
        String response = null;

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

                    response = "Item dengan kode: " + codeName + " dan ukuran: " + size + ", tersisa 1 buah";
                }
                else if (totalItem > 1)
                {
                    response = "Item dengan kode: " + codeName + " dan ukuran: " + size + ", tersisa  " + totalItem + " buah";
                }
                else
                {
                    response = "Tidak dapat menemukan item dengan kode: " + codeName + " dan ukuran: " + size;
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error when validationCheckMessage: {}", e);

            response =  generateMessage();
        }

        return response;
    }
}