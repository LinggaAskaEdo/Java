package id.co.homecredit.mobile.common.secure.security;

import org.springframework.stereotype.Component;

@Component
public class SecurityContextHolder
{
    private String cuid;
    private String userName;
    private String mobId;

    public String getCuid()
    {
        return cuid;
    }

    public void setCuid(String cuid)
    {
        this.cuid = cuid;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getMobId()
    {
        return mobId;
    }

    public void setMobId(String mobId)
    {
        this.mobId = mobId;
    }

    @Override
    public String toString()
    {
        return "ResourceHeaders{" +
                "cuid='" + cuid + '\'' +
                ", userName='" + userName + '\'' +
                ", mobId='" + mobId + '\'' +
                '}';
    }
}
