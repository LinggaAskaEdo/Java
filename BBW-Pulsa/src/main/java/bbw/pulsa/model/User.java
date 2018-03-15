package bbw.pulsa.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lingga on 12/03/18.
 */

public class User
{
    private Integer userId;

    @SerializedName("nama")
    private String userName;

    @SerializedName("password")
    private String userPassword;

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}