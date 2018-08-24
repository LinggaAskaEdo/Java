/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.bos.dao;

import com.bos.constant.ShippingType;
import com.bos.model.Client;
import com.bos.model.Item;
import com.bos.model.Results;
import com.bos.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BOSDAO
{
    private final Logger log = LoggerFactory.getLogger(BOSDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User loadUserByToken(String token)
    {
        User user = null;

        String query = "SELECT USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL, USER_HP, USER_ADDRESS, USER_TOKEN, USER_TOKEN_EXPIRED, USER_SECURITY_TOKEN, " +
                "USER_OPEN_TIME, USER_CLOSE_TIME, USER_ADMIN FROM USER WHERE USER_TOKEN = ? LIMIT 1";

        log.debug("Query loadUserByToken: {}", query);

        try
        {
            user = jdbcTemplate.queryForObject(query, new Object[] { token }, new BeanPropertyRowMapper<>(User.class));
        }
        catch (Exception e)
        {
            log.error("ERROR when loadUserByToken: ", e);
        }

        return user;
    }

    public Integer checkRegion(String district, String city)
    {
        Integer result = 0;
        
        String query = "SELECT COUNT(*) FROM EXPEDITION_IN_NEW WHERE EXPEDITION_IN_DISTRICT = ? AND EXPEDITION_IN_CITY LIKE ?";
        
        log.debug("Query checkRegion: {}", query);
        
        try
        {
            result = jdbcTemplate.queryForObject(query, new String[] { district, "%" + city + "%" }, Integer.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when checkRegion: {}", e);
        }
        
        return result;
    }

    public Integer checkItem(Integer userId, String codeName, String size)
    {
        Integer result = 0;

        String query = "SELECT ITEM_STOCK FROM ITEM WHERE USER_ID = ? AND ITEM_CODE = ? AND ITEM_SIZE = ?";

        log.debug("Query checkItem: {}", query);

        try
        {
            result = jdbcTemplate.queryForObject(query, new Object[] { userId, codeName, size }, Integer.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when checkItem: {}", e);
        }

        return result;
    }

    public Item getItem(Integer userId, String codeItem, String sizeItem)
    {
        Item item = null;

        String query = "SELECT ITEM_ID, USER_ID, ITEM_CODE, ITEM_NAME, ITEM_PICTURE, ITEM_SIZE, ITEM_DESC, ITEM_STOCK, ITEM_PRICE, ITEM_WEIGHT " +
                "FROM ITEM WHERE USER_ID = ? AND ITEM_CODE = ? AND ITEM_SIZE = ?";

        log.debug("Query getItem: {}", query);

        try
        {
            item = jdbcTemplate.queryForObject(query, new Object[] { userId, codeItem, sizeItem }, new BeanPropertyRowMapper<>(Item.class));
        }
        catch (Exception e)
        {
            log.error("ERROR when getItem: ", e);
        }

        return item;
    }

    public void updateStock(Integer userId, String codeItem, String sizeItem, int newStock)
    {
        String query = "UPDATE ITEM SET ITEM_STOCK = ? WHERE USER_ID = ? AND ITEM_CODE = ? AND ITEM_SIZE = ?";

        log.debug("Query updateStock: {}", query);

        try
        {
            jdbcTemplate.update(query, newStock, userId, codeItem, sizeItem);
        }
        catch (Exception e)
        {
            log.error("ERROR when updateStock: ", e);
        }
    }

    public Integer countShippingIn(String district, String city, String shippingType)
    {
        Integer total = 0;

        String colName;

        switch (shippingType)
        {
            case ShippingType.SHIPPING_TYPE_REG :
                colName = "EXPEDITION_IN_PRICE_REG";
                break;
            case ShippingType.SHIPPING_TYPE_BEST :
                colName = "EXPEDITION_IN_PRICE_BEST";
                break;
            case ShippingType.SHIPPING_TYPE_CARGO :
                colName = "EXPEDITION_IN_PRICE_CARGO";
                break;
            default :
                colName = "EXPEDITION_IN_PRICE_REG";
        }

        String query = "SELECT " + colName + " FROM EXPEDITION_IN_NEW WHERE EXPEDITION_IN_DISTRICT = ? AND EXPEDITION_IN_CITY LIKE ?";

        log.debug("Query countShippingIn: {}", query);

        try
        {
            total = jdbcTemplate.queryForObject(query, new String[] { district, "%"  + city + "%" }, Integer.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when countShippingIn: ", e);
        }

        return total;
    }

    public Integer countShippingOut(String countryCode)
    {
        Integer total = 0;

        String query = "SELECT EXPEDITION_OUT_PRICE FROM EXPEDITION_OUT WHERE EXPEDITION_OUT_COUNTRY_CODE = ?";

        log.debug("Query countShippingOut: {}", query);

        try
        {
            total = jdbcTemplate.queryForObject(query, new String[] { countryCode }, Integer.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when countShippingOut: ", e);
        }

        return total;
    }

    public boolean isSupportBest(String district, String city)
    {
        boolean result = false;

        String query = "SELECT (EXPEDITION_IN_PRICE_BEST > 0) FROM EXPEDITION_IN_NEW WHERE EXPEDITION_IN_DISTRICT = ? AND EXPEDITION_IN_CITY LIKE ?";

        log.debug("Query isSupportBest: {}", query);

        try
        {
            result = jdbcTemplate.queryForObject(query, new String[] { district, "%"  + city + "%" }, Boolean.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when isSupportBest: {}", e);
        }

        return result;
    }

    public Integer saveClient(Client client)
    {
        Integer resultId = 0;

        try
        {
            SimpleJdbcInsert clientJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            clientJdbcInsert.withTableName("CLIENT").usingGeneratedKeyColumns("CLIENT_ID");

            Map<String, Object> clientParameters = new HashMap<>();
            clientParameters.put("CLIENT_COUNTRY", client.getClientCountry());
            clientParameters.put("CLIENT_NAME", client.getClientName());
            clientParameters.put("CLIENT_HP", client.getClientHp());
            clientParameters.put("CLIENT_BANK_NAME", client.getClientBankName());
            clientParameters.put("CLIENT_BANK_NUMBER", client.getClientBankNumber());
            clientParameters.put("CLIENT_ADDRESS", client.getClientAddress());
            clientParameters.put("CLIENT_DISTRICTS", client.getClientDistricts());
            clientParameters.put("CLIENT_PROVINCE", client.getClientCity());

            log.debug("saveClient: {}", clientJdbcInsert.getInsertString());

            Number key = clientJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(clientParameters));

            resultId = key.intValue();
        }
        catch (Exception e)
        {
            log.error("ERROR when saveClient: {}", e);
        }

        return resultId;
    }

    public Integer saveTransaction(Integer userId, int clientId, String transactionNumber, String shippingType, Integer totalShipping, Integer totalWeightShipping, int unique)
    {
        Integer resultId = 0;

        try
        {
            SimpleJdbcInsert transactionJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            transactionJdbcInsert.withTableName("TRANSACTION").usingGeneratedKeyColumns("TRANSACTION_ID");

            Map<String, Object> transactionParameters = new HashMap<>();
            transactionParameters.put("USER_ID", userId);
            transactionParameters.put("CLIENT_ID", clientId);
            transactionParameters.put("TRANSACTION_NUMBER", transactionNumber);
            transactionParameters.put("TRANSACTION_DATE", new Date());
            transactionParameters.put("IS_TRANSFERED", false);
            transactionParameters.put("IS_CANCELED", false);
            transactionParameters.put("IS_DELIVERED", false);
            transactionParameters.put("INVOICE_NUMBER", "");
            transactionParameters.put("SHIPPING_TYPE", shippingType);
            transactionParameters.put("SHIPPING_TOTAL", totalShipping);
            transactionParameters.put("SHIPPING_TOTAL_WEIGHT", totalWeightShipping);
            transactionParameters.put("UNIQUE_NUMBER", unique);

            log.debug("saveTransaction: {}", transactionJdbcInsert.getInsertString());

            Number key = transactionJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(transactionParameters));

            resultId = key.intValue();
        }
        catch (Exception e)
        {
            log.error("ERROR when saveTransaction: {}", e);
        }

        return resultId;
    }

    public List<Integer> saveOrder(int transactionId, List<Item> itemList)
    {
        List<Integer> resultIds = new ArrayList<>();

        for (Item item : itemList)
        {
            try
            {
                SimpleJdbcInsert ordersJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
                ordersJdbcInsert.withTableName("ORDERS").usingGeneratedKeyColumns("ORDERS_ID");

                Map<String, Object> ordersParameters = new HashMap<>();
                ordersParameters.put("ITEM_ID", item.getItemId());
                ordersParameters.put("TRANSACTION_ID", transactionId);
                ordersParameters.put("TOTAL_ITEM", item.getItemTotal());
                ordersParameters.put("TOTAL_PRICE", item.getItemTotal() * item.getItemPrice());

                log.debug("saveOrder: {}", ordersJdbcInsert.getInsertString());

                Number key = ordersJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(ordersParameters));

                Integer resultId = key.intValue();
                resultIds.add(resultId);
            }
            catch (Exception e)
            {
                log.error("ERROR when saveOrder: {}", e);
            }
        }

        return resultIds;
    }

    public boolean updateOriginData(List<Results> results)
    {
        boolean status = false;

        String query = "INSERT INTO ORIGIN (ORIGIN_NAME, ORIGIN_CODE) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE ORIGIN_NAME = ?, ORIGIN_CODE = ?";

        log.debug("Query updateOriginData: {}", query);

        try
        {
            for (Results r : results)
            {
                jdbcTemplate.update(query, r.getOrigin_name(), r.getOrigin_code(), r.getOrigin_name(), r.getOrigin_code());
            }

            status = true;
        }
        catch (Exception e)
        {
            log.error("ERROR when updateOrigin: {}", e);
        }

        return status;
    }

    public boolean updateDestinationData(List<Results> results)
    {
        boolean status = false;

        /*String query = "INSERT INTO EXPEDITION_IN_NEW (EXPEDITION_IN_CODE, EXPEDITION_IN_CITY, EXPEDITION_IN_DISTRICT, EXPEDITION_IN_PROVINCE) VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE EXPEDITION_IN_CODE = ?, EXPEDITION_IN_CITY = ?, EXPEDITION_IN_DISTRICT = ?";

        log.debug("Query updateOriginData: {}", query);*/

        log.debug("Query updateOriginData: INSERT_UPDATE_EXPEDITION_IN(?, ?, ?, ?)");

        try
        {
            for (Results r : results)
            {
                if (!r.getCity().equalsIgnoreCase("Pending") || !r.getSubdistrict().equalsIgnoreCase("Pending") ||
                        !r.getProvince().equalsIgnoreCase("Pending"))
                {
                    /*jdbcTemplate.update(query, r.getDestination_code(), r.getCity(), r.getSubdistrict(), r.getProvince(), r.getDestination_code(), r.getCity(),
                            r.getSubdistrict());*/

                    jdbcTemplate.update("CALL INSERT_UPDATE_EXPEDITION_IN(?, ?, ?, ?)", r.getDestination_code(), r.getCity(), r.getSubdistrict(), r.getProvince());
                }
            }

            status = true;
        }
        catch (Exception e)
        {
            log.error("ERROR when updateOrigin: {}", e);
        }

        return status;
    }

    public String getOriginCode(String city)
    {
        String result = null;

        String query = "SELECT ORIGIN_CODE FROM ORIGIN WHERE ORIGIN_NAME = ?";

        log.debug("Query getOriginCode: {}", query);

        try
        {
            result = jdbcTemplate.queryForObject(query, new String[] { city }, String.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when getOriginCode: {}", e);
        }

        return result;
    }

    public String getDestinationCode(String district, String city)
    {
        String result = null;

        String query = "SELECT EXPEDITION_IN_CODE FROM EXPEDITION_IN_NEW WHERE EXPEDITION_IN_DISTRICT = ? AND EXPEDITION_IN_CITY LIKE ?";

        log.debug("Query getDestinationCode: {}", query);

        try
        {
            result = jdbcTemplate.queryForObject(query, new String[] { district, "%"  + city + "%" }, String.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when getDestinationCode: {}", e);
        }

        return result;
    }

    public boolean updateTarif(List<Results> results, String destinationCode)
    {
        boolean status = false;

        String colPrice = "";
        String colEtd = "";

        try
        {
            for (Results r : results)
            {
                if (Arrays.stream(ShippingType.shippingArrays).anyMatch(x -> x.equalsIgnoreCase(r.getService())))
                {
                    if (r.getService().equalsIgnoreCase(ShippingType.SHIPPING_TYPE_REG))
                    {
                        colPrice = "EXPEDITION_IN_PRICE_REG";
                        colEtd = "EXPEDITION_IN_PRICE_REG_ETD";
                    }
                    else if (r.getService().equalsIgnoreCase(ShippingType.SHIPPING_TYPE_BEST))
                    {
                        colPrice = "EXPEDITION_IN_PRICE_BEST";
                        colEtd = "EXPEDITION_IN_PRICE_BEST_ETD";
                    }
                    else if (r.getService().equalsIgnoreCase(ShippingType.SHIPPING_TYPE_CARGO))
                    {
                        colPrice = "EXPEDITION_IN_PRICE_CARGO";
                        colEtd = "EXPEDITION_IN_PRICE_CARGO_ETD";
                    }

                    String query = "UPDATE EXPEDITION_IN_NEW SET " + colPrice + " = ?, " + colEtd + " = ? WHERE EXPEDITION_IN_CODE = ?";

                    log.debug("Query updateTarif: {}", query);

                    jdbcTemplate.update(query, r.getTariff(), r.getEtd(), destinationCode);
                }
            }

            status = true;
        }
        catch (Exception e)
        {
            log.error("ERROR when updateTarif: ", e);
        }

        return status;
    }
}