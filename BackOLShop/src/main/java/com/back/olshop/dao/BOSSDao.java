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
public class BOSSDao
{
    private final Logger log = LoggerFactory.getLogger(BOSSDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User loadUserByToken(String token)
    {
        User user = null;

        String query = "SELECT * FROM USER WHERE USER_TOKEN = ? LIMIT 1";

        log.debug("Query loadUserByToken: {}", query);

        try
        {
            user = jdbcTemplate.queryForObject(query, new Object[] { token }, new BeanPropertyRowMapper<>(User.class));
        }
        catch (Exception e)
        {
            log.error("ERROR when loadUserByToken: " + e.getMessage());
        }

        return user;
    }
}