package bbw.pulsa.dao;

import bbw.pulsa.model.User;

public interface PulsaDao
{
    User login(String username, String password);
}