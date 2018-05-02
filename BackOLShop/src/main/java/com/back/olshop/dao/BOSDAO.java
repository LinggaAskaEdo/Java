/*
 * Copyright (c) 2018 by Lingga "Aska" Edo
 */

package com.back.olshop.dao;

import com.back.olshop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
public class BOSDAO
{
    private final Logger log = LoggerFactory.getLogger(BOSDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public User loadUserByToken(String token)
    {
        User user = null;

        String query = "SELECT USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL, USER_TOKEN, USER_TOKEN_EXPIRED, USER_HP, USER_OPEN_TIME, USER_CLOSE_TIME, USER_ADMIN FROM USER " +
                "WHERE USER_TOKEN = ? LIMIT 1";

        log.debug("Query loadUserByToken: {}", query);

        try
        {
            user = jdbcTemplate.queryForObject(query, new Object[] { token }, new BeanPropertyRowMapper<>(User.class));
        }
        catch (Exception e)
        {
            log.error("ERROR when loadUserByToken: ", e.getMessage());
        }

        return user;
    }

    public Integer checkRegion(String district, String province)
    {
        Integer result = 0;
        
        String query = "SELECT COUNT(*) FROM EXPEDITION_IN WHERE EXPEDITION_IN_DISTRICT LIKE ? AND EXPEDITION_IN_PROVINCE LIKE ?";
        
        log.debug("Query checkRegion: {}", query);
        
        try
        {
            result = jdbcTemplate.queryForObject(query, new String[] { "'%" + district + "%'", "'%" + province + "%'" }, Integer.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when checkRegion: {}", e.getMessage());
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
            log.error("ERROR when checkItem: {}", e.getMessage());
        }

        return result;
    }

    public boolean checkItem(Integer userId, String codeName, String size, int total)
    {
        boolean result = false;

        String query = "SELECT ((SELECT ITEM_STOCK FROM ITEM WHERE USER_ID = ? AND ITEM_CODE = ? AND ITEM_SIZE = ?) >= ?)";

        log.debug("Query checkItem: {}", query);

        try
        {
            result = jdbcTemplate.queryForObject(query, new Object[] { userId, codeName, size, total }, Boolean.class);
        }
        catch (Exception e)
        {
            log.error("ERROR when checkItem: {}", e.getMessage());
        }

        return result;
    }
}