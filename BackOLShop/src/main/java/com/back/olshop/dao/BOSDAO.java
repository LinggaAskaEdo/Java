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

@Repository
public class BOSDAO
{
    private final Logger log = LoggerFactory.getLogger(BOSDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
}