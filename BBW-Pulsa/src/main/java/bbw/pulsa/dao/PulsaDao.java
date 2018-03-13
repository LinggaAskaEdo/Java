package bbw.pulsa.dao;

import bbw.pulsa.model.User;

/**
 * Created by Lingga on 12/03/18.
 */

public interface PulsaDao
{
    User login(String username, String password);
}