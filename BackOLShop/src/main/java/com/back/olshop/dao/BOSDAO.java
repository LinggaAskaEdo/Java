/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.dao;

import com.back.olshop.constant.ShippingType;
import com.back.olshop.model.Client;
import com.back.olshop.model.Item;
import com.back.olshop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@Transactional
public class BOSDAO
{
    private final Logger log = LoggerFactory.getLogger(BOSDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*@Autowired
    private TransactionTemplate transactionTemplate;*/

    public User loadUserByToken(String token)
    {
        User user = null;

        String query = "SELECT USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL, USER_TOKEN, USER_TOKEN_EXPIRED, USER_HP, USER_OPEN_TIME, USER_CLOSE_TIME, USER_ADMIN " +
                "FROM USER WHERE USER_TOKEN = ? LIMIT 1";

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

    public Integer checkRegion(String district, String province)
    {
        Integer result = 0;
        
        String query = "SELECT COUNT(*) FROM EXPEDITION_IN WHERE EXPEDITION_IN_DISTRICT = ? AND EXPEDITION_IN_PROVINCE = ?";
        
        log.debug("Query checkRegion: {}", query);
        
        try
        {
            result = jdbcTemplate.queryForObject(query, new String[] { district, province }, Integer.class);
            //result = jdbcTemplate.queryForObject(query, new String[] { "'" + district + "'", "'" + province + "'" }, Integer.class);
            //result = jdbcTemplate.queryForObject(query, new String[] { "'%" + district + "%'", "'%" + province + "%'" }, Integer.class);
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

    public Integer countShippingIn(String district, String province, String shippingType)
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

        String query = "SELECT " + colName + " FROM EXPEDITION_IN WHERE EXPEDITION_IN_DISTRICT = ? AND EXPEDITION_IN_PROVINCE = ? LIMIT 1";

        log.debug("Query countShippingIn: {}", query);

        try
        {
            total = jdbcTemplate.queryForObject(query, new String[] { district, province }, Integer.class);
            //total = jdbcTemplate.queryForObject(query, new String[] { "'" + district + "'", "'" + province + "'" }, Integer.class);
            //total = jdbcTemplate.queryForObject(query, new String[]{ "'%" + district + "%'", "'%" + province + "%'" }, new BeanPropertyRowMapper<>(Integer.class));
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

    public boolean isSupportBest(String district, String province)
    {
        boolean result = false;

        String query = "SELECT (EXPEDITION_IN_PRICE_BEST > 0) FROM EXPEDITION_IN WHERE EXPEDITION_IN_DISTRICT = ? AND EXPEDITION_IN_PROVINCE = ? LIMIT 1";

        log.debug("Query isSupportBest: {}", query);

        try
        {
            result = jdbcTemplate.queryForObject(query, new String[] { district, province }, Boolean.class);
            //result = jdbcTemplate.queryForObject(query, new String[] { "'" + district + "'", "'" + province + "'" }, Boolean.class);
            //result = jdbcTemplate.queryForObject(query, new String[] { "'%" + district + "%'", "'%" + province + "%'" }, Boolean.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when isSupportBest: {}", e);
        }

        return result;
    }

    public int saveClient(Client client)
    {
        int resultId = 0;

        try
        {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("CLIENT").usingGeneratedKeyColumns("CLIENT_ID");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("CLIENT_COUNTRY", client.getClientCountry());
            parameters.put("CLIENT_NAME", client.getClientName());
            parameters.put("CLIENT_HP", client.getClientHp());
            parameters.put("CLIENT_BANK_NAME", client.getClientBankName());
            parameters.put("CLIENT_BANK_NUMBER", client.getClientBankNumber());
            parameters.put("CLIENT_ADDRESS", client.getClientAddress());
            parameters.put("CLIENT_DISTRICTS", client.getClientDistricts());
            parameters.put("CLIENT_PROVINCE", client.getClientProvince());

            log.debug("saveClient: {}", jdbcInsert.getInsertString());

            // execute insert
            Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

            resultId = key.intValue();
        }
        catch (Exception e)
        {
            log.error("ERROR when saveClient: {}", e);
        }

        return resultId;
    }

    public int saveTransaction(Integer userId, int clientId, String transactionNumber, String shippingType, Integer totalShipping, int unique)
    {
        int resultId = 0;

        try
        {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("TRANSACTION").usingGeneratedKeyColumns("TRANSACTION_ID");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("USER_ID", userId);
            parameters.put("CLIENT_ID", clientId);
            parameters.put("TRANSACTION_NUMBER", transactionNumber);
            parameters.put("TRANSACTION_DATE", new Date());
            parameters.put("IS_TRANSFERED", false);
            parameters.put("IS_CANCELED", false);
            parameters.put("IS_DELIVERED", false);
            parameters.put("INVOICE_NUMBER", "");
            parameters.put("SHIPPING_TYPE", shippingType);
            parameters.put("SHIPPING_TOTAL", totalShipping);
            parameters.put("UNIQUE_NUMBER", unique);

            log.debug("saveTransaction: {}", jdbcInsert.getInsertString());

            // execute insert
            Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

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
                SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
                jdbcInsert.withTableName("ORDER").usingGeneratedKeyColumns("ORDER_ID");

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("ITEM_ID", item.getItemId());
                parameters.put("TRANSACTION_ID", transactionId);
                parameters.put("TOTAL_ITEM", item.getItemTotal());
                parameters.put("TOTAL_PRICE", item.getItemTotal() * item.getItemPrice());

                log.debug("saveOrder: {}", jdbcInsert.getInsertString());

                // execute insert
                Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

                resultIds.add(key.intValue());
            }
            catch (Exception e)
            {
                log.error("ERROR when saveOrder: {}", e);
            }
        }

        return resultIds;
    }
}