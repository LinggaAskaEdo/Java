package id.co.homecredit.simulator.model;

public class Config
{
    private String phoneNumber;
    private String cardNumber;

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString()
    {
        return "Config{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}