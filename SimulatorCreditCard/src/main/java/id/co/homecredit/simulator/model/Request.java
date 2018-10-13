package id.co.homecredit.simulator.model;

public class Request
{
    private String userId;
    private String connectionId;
    private String encryptedPinBlock;
    private String sessionZpkKey;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getConnectionId()
    {
        return connectionId;
    }

    public void setConnectionId(String connectionId)
    {
        this.connectionId = connectionId;
    }

    public String getEncryptedPinBlock()
    {
        return encryptedPinBlock;
    }

    public void setEncryptedPinBlock(String encryptedPinBlock)
    {
        this.encryptedPinBlock = encryptedPinBlock;
    }

    public String getSessionZpkKey()
    {
        return sessionZpkKey;
    }

    public void setSessionZpkKey(String sessionZpkKey)
    {
        this.sessionZpkKey = sessionZpkKey;
    }

    @Override
    public String toString()
    {
        return "Request{" +
                "userId='" + userId + '\'' +
                ", connectionId='" + connectionId + '\'' +
                ", encryptedPinBlock='" + encryptedPinBlock + '\'' +
                ", sessionZpkKey='" + sessionZpkKey + '\'' +
                '}';
    }
}