package springboot.jdbc.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springboot.jdbc.dao.UserDao;
import springboot.jdbc.model.User;
import springboot.jdbc.preference.ConfigPreference;

@Service
public class UserService
{
    private UserDao userDao;
    private ConfigPreference preference;

    @Autowired
    public UserService(UserDao userDao, ConfigPreference preference)
    {
        this.userDao = userDao;
        this.preference = preference;
    }

    public ResponseEntity<String> getUser(String userId)
    {
        User user = userDao.getTypeByUserId(userId);

        String otp = preference.otp;

        if (!otp.equalsIgnoreCase(""))
            user.setOtp("AAA");
        else
            user.setOtp("BBB");

        return new ResponseEntity<>(new Gson().toJson(user), HttpStatus.OK);
    }

    public ResponseEntity<String> getUserLiveness(String userId)
    {
        User user = userDao.getLivenessByUserId(userId);

        return new ResponseEntity<>(new Gson().toJson(user), HttpStatus.OK);
    }
}