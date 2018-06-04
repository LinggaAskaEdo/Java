/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.service;

import com.back.olshop.constant.*;
import com.back.olshop.dao.BOSDAO;
import com.back.olshop.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.NumberFormat;
import java.util.*;

@Service
public class BOSService
{
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final double LIMIT_COMMA_NUMBER = 0.2;
    private final Logger log = LoggerFactory.getLogger(BOSServiceRest.class);
    private double totalWeight;
    private int roundingTotalWeight, totalPrice;
    private Integer totalShipping;
    private List<Item> itemList = new ArrayList<>();
    private String shippingType;

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
            roundingTotalWeight = 0;

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

            //response = MessagePreference.MESSAGE_INVALID_REQUEST;
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
                        //return MessagePreference.MESSAGE_INVALID_REQUEST;
                        return generateMessage();
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
                        else if (province.equalsIgnoreCase(""))
                        {
                            return MessagePreference.MESSAGE_ERROR_EMPTY_PROVINCE;
                        }
                        else if (!checkRegion(district, province))
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
                                //if (data[10] != null && Arrays.asList(ShippingType.shippingArrays).contains(data[10].trim()))
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

                            //set another client field
                            client.setClientName(name);
                            client.setClientHp(phoneNumber);
                            client.setClientBankName(bankName);
                            client.setClientBankNumber(bankAccountNumber);
                            client.setClientAddress(address);
                            client.setClientDistricts(district);
                            client.setClientProvince(province);

                            return processOrder(userId, order, client);
                        }
                    }
                }
                catch (Exception e)
                {
                    log.error("Error when validationBuyMessage IN: {}", e);

                    //return MessagePreference.MESSAGE_INVALID_REQUEST;
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
                        //return MessagePreference.MESSAGE_INVALID_REQUEST;
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

                    //return MessagePreference.MESSAGE_INVALID_REQUEST;
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

            //return MessagePreference.MESSAGE_INVALID_REQUEST;
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
                        log.debug("Item: {}", item.toString());

                        if (item != null && item.getItemStock() >= totalItem)
                        {
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

                            return "Item dengan kode: " + codeItem +
                                    " dan ukuran: " + sizeItem +
                                    ", tersisa " + item.getItemStock() +
                                    " buah";
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
                    log.debug("Item: {}", item.toString());

                    if (item != null && item.getItemStock() >= totalItem)
                    {
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

                        return "Item dengan kode: " + arrOrder[0] +
                                " dan ukuran: " + arrOrder[1] +
                                ", tersisa " + item.getItemStock() +
                                " buah";
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
            log.debug("roundingTotalWeight: {}", roundingTotalWeight);
            log.debug("totalPrice: {}", totalPrice);

            if (shippingType.equalsIgnoreCase(ShippingType.SHIPPING_TYPE_CARGO) && roundingTotalWeight < 7)
            {
                shippingType = ShippingType.SHIPPING_TYPE_REG;
            }
            else if (shippingType.equalsIgnoreCase(ShippingType.SHIPPING_TYPE_BEST))
            {
                if (dao.isSupportBest(client.getClientDistricts(), client.getClientProvince()))
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
                totalShipping = dao.countShippingIn(client.getClientDistricts(), client.getClientProvince(), shippingType);
            }
            else if (Arrays.asList(CountryCode.countryArrays).contains(client.getClientCountry()))
            {
                totalShipping = dao.countShippingOut(client.getClientCountry());
            }

            log.debug("totalShipping: {}", totalShipping);
            log.debug("totalShippingWeight: {}", totalShipping * roundingTotalWeight);

            if (totalShipping <= 0 || totalShipping == null)
            {
                returnStocks();

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

            Integer transactionId = dao.saveTransaction(userId, clientId, transactionNumber, shippingType, totalShipping, roundingTotalWeight, unique);
            log.debug("transactionId: {}", transactionId);

            List<Integer> orderIds = dao.saveOrder(transactionId, itemList);
            log.debug("orderId: {}", orderIds);

            String message = generateMessage(client, transactionNumber, totalPrice, totalShipping, roundingTotalWeight, itemList);

            itemList.clear();

            //return new Response(ApplicationStatus.SUCCESS.toString(), MessagePreference.MESSAGE_PROCESS_ORDER);
            return message;
        }
        catch (Exception e)
        {
            log.error("Error on processOrder: {}", e);

            returnStocks();
            itemList.clear();

            return MessagePreference.MESSAGE_ERROR_PROCESS;
        }
    }

    private String generateMessage(Client client, String transactionNumber, Integer totalPrice, Integer totalShipping, Integer roundingTotalWeight, List<Item> itemList)
    {
        String separator = System.lineSeparator();

        StringBuilder builder = new StringBuilder();
        builder.append("Assalamu'alaikum, ").append(client.getClientName()).append(separator);
        builder.append("Transaksi anda untuk pembelian (").append(transactionNumber).append("): ").append(separator);

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
        builder.append("Total biaya pengiriman: Rp. ").append(NumberFormat.getNumberInstance(Locale.US).format(totalShipping * roundingTotalWeight)).append(separator);
        builder.append("Total keseluruhan: Rp. ").append(NumberFormat.getNumberInstance(Locale.US).format(totalPrice + (totalShipping * roundingTotalWeight)))
                .append(separator).append(separator);
        builder.append("Mohon transfer ke rekening Bank Mandiri atas nama Ayuka Winda Kharisma 1560002743930, sesuai totalan berikut dengan kode unik yang diberikan. " +
                "Ini memudahkan kami dalam pengecekan transferan. Dan mengenai nominal kode unik. " +
                "Seluruh kode unik tersebut akan kami total dan kami sedekahkan setiap bulannya. Mohon maaf kurang lebihnya. " +
                "Dan untuk batas transfer hanya 4 jam dari dikirimkannya invoice ini. Dimohon untuk mengirimkan bukti transfer. " +
                "Selebihnya kami anggap batal. Mohon maaf kurang lebihnya.");

        return builder.toString();
    }

    private String generateMessage()
    {
        String separator = System.lineSeparator();

        StringBuilder builder = new StringBuilder();
        builder.append("Mohon maaf format tidak terdeteksi. Untuk bantuan langsung wa ke 087808731559");

        /*builder.append(MessagePreference.MESSAGE_INVALID_REQUEST).append(separator).append(separator);
        builder.append("Untuk selengkapnya silahkan membaca di tautan ini: http://185.201.8.192/web/ atau menghubungi Admin kami, Ayuka (087808731559)");*/

        /*builder.append("Untuk pengiriman ke dalam negeri, bisa menggunakan format seperti dibawah ini:").append(separator);
        builder.append("1. Pembelian satu jenis barang").append(separator);
        builder.append("%23BELI%23KODE NEGARA#NAMA PEMBELI%23NAMA BANK%23REKENING BANK%23ALAMAT LENGKAP%23KECAMATAN%23PROPINSI%23KODE-UKURAN-JUMLAH BARANG%23");
        builder.append(separator);
        builder.append("2. Pembelian lebih dari satu jenis barang").append(separator);
        builder.append("#BELI#KODE NEGARA#NAMA PEMBELI#NAMA BANK#REKENING BANK#ALAMAT LENGKAP#KECAMATAN#PROPINSI#KODE-UKURAN-JUMLAH BARANG, KODE-UKURAN-JUMLAH BARANG#");
        builder.append(separator);
        builder.append(separator);
        builder.append("Untuk pengiriman ke luar negeri, bisa menggunakan format seperti dibawah ini:").append(separator);
        builder.append("1. Pembelian satu jenis barang").append(separator);
        builder.append("#BELI#KODE NEGARA#NAMA PEMBELI#NAMA BANK#REKENING BANK#ALAMAT LENGKAP#KODE-UKURAN-JUMLAH BARANG#");
        builder.append(separator);
        builder.append("2. Pembelian lebih dari satu jenis barang").append(separator);
        builder.append("#BELI#KODE NEGARA#NAMA PEMBELI#NAMA BANK#REKENING BANK#ALAMAT LENGKAP#KODE-UKURAN-JUMLAH BARANG, KODE-UKURAN-JUMLAH BARANG");
        builder.append(separator);
        builder.append(separator);
        builder.append("Berikut kode negara yang kita dukung:").append(separator);
        builder.append("1. SGP = Singapore").append(separator);
        builder.append("2. MYS1 = Malaysia").append(separator);
        builder.append("3. MYS2 = Malaysia (Sabah, Serawak, Khucing)").append(separator);
        builder.append("4. TWN = Taiwan").append(separator);
        builder.append("5. HGK = Hongkong").append(separator);
        builder.append("6. BRN = Brunei Darussalam").append(separator);
        builder.append("7. THA = Thailand").append(separator);
        builder.append("8. VNM = Vietnam").append(separator);*/

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
            roundingTotalWeight = Integer.parseInt(str);
        }
        else
        {
            roundingTotalWeight = Integer.parseInt(str) + 1;
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

    private boolean checkRegion(String district, String province)
    {
        return dao.checkRegion(district, province) > 0;
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
                    response = MessagePreference.MESSAGE_ERROR_EMPTY_ITEM;
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error when validationCheckMessage: {}", e);

            //response = MessagePreference.MESSAGE_INVALID_REQUEST;
            response =  generateMessage();
        }

        return response;
    }
}