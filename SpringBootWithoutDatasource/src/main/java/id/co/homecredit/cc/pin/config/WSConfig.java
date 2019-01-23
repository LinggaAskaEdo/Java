package id.co.homecredit.cc.pin.config;

import id.co.homecredit.cc.pin.preference.ConfigPreference;
import id.co.homecredit.portal.ws.CardInfo;
import id.co.homecredit.portal.ws.PartyV1Service;
import id.co.homecredit.portal.ws.PinChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WSConfig
{
    private ConfigPreference preference;

    @Autowired
    public WSConfig(ConfigPreference preference)
    {
        this.preference = preference;
    }

    @Bean
    public CardInfo cardInfo()
    {
        return new CardInfo(preference.getCardInfoUrl(), preference.getCardInfoUsername(), preference.getCardInfoPassword());
    }

    @Bean
    public PinChange pinChange()
    {
        return new PinChange(preference.getPinChangeUrl(), preference.getPinChangeUsername(), preference.getPinChangePassword());
    }

    @Bean
    public PartyV1Service partyV1Service()
    {
        return new PartyV1Service(preference.getPartyServiceUrl(), preference.getPartyServiceUsername(), preference.getPartyServicePassword());
    }
}