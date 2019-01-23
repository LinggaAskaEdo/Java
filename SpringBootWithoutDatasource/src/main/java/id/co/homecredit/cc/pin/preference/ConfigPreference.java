package id.co.homecredit.cc.pin.preference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigPreference
{
    @Value("${ws.soap.card-info.url}")
    private String cardInfoUrl;

    @Value("${ws.soap.card-info.username}")
    private String cardInfoUsername;

    @Value("${ws.soap.card-info.password}")
    private String cardInfoPassword;

    @Value("${ws.soap.pin-change.url}")
    private String pinChangeUrl;

    @Value("${ws.soap.pin-change.username}")
    private String pinChangeUsername;

    @Value("${ws.soap.pin-change.password}")
    private String pinChangePassword;

    @Value("${ws.soap.party-service.url}")
    private String partyServiceUrl;

    @Value("${ws.soap.party-service.username}")
    private String partyServiceUsername;

    @Value("${ws.soap.party-service.password}")
    private String partyServicePassword;

    public String getCardInfoUrl()
    {
        return cardInfoUrl;
    }

    public String getCardInfoUsername()
    {
        return cardInfoUsername;
    }

    public String getCardInfoPassword()
    {
        return cardInfoPassword;
    }

    public String getPinChangeUrl()
    {
        return pinChangeUrl;
    }

    public String getPinChangeUsername()
    {
        return pinChangeUsername;
    }

    public String getPinChangePassword()
    {
        return pinChangePassword;
    }

    public String getPartyServiceUrl()
    {
        return partyServiceUrl;
    }

    public String getPartyServiceUsername()
    {
        return partyServiceUsername;
    }

    public String getPartyServicePassword()
    {
        return partyServicePassword;
    }
}