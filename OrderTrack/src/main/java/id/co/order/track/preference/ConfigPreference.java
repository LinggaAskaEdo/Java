package id.co.order.track.preference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigPreference
{
    @Value("${rest.timeout.connection}")
    public int wsTimeout;

    @Value("${rest.marketplace.url}")
    public String restMarketplaceUrl;
}