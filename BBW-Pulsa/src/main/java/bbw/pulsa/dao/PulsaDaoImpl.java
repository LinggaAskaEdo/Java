package bbw.pulsa.dao;

import bbw.pulsa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Lingga on 12/03/18.
 */

@Repository
@Qualifier("personDao")
public class PulsaDaoImpl implements PulsaDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User login(String username, String password)
    {
        return (User) jdbcTemplate.queryForObject("SELECT USER_ID, USER_NAME, USER_PASSWORD FROM BBW_PULSA.USER WHERE USER_NAME = ? AND USER_PASSWORD = ? LIMIT 1", new Object[] { username, password }, new BeanPropertyRowMapper(User.class));
    }
}