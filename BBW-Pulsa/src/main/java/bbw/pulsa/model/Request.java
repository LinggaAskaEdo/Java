package bbw.pulsa.model;

/**
 * Created by Lingga on 12/03/18.
 */

public class Request
{
    private String username;
    private String password;
    private String operator;
    private Integer userId;
    private Integer harga;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getHarga()
    {
        return harga;
    }

    public void setHarga(Integer harga)
    {
        this.harga = harga;
    }

    @Override
    public String toString()
    {
        return "Request{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", operator='" + operator + '\'' +
                ", userId=" + userId +
                ", harga=" + harga +
                '}';
    }
}