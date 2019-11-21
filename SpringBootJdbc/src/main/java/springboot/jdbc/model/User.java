package springboot.jdbc.model;

public class User
{
    private Float id;
    private String type;
    private String userId;
    private String livenessUuid;

    public Float getId()
    {
        return id;
    }

    public void setId(Float id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getLivenessUuid()
    {
        return livenessUuid;
    }

    public void setLivenessUuid(String livenessUuid)
    {
        this.livenessUuid = livenessUuid;
    }
}