package id.co.qris.generator.preference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigPreference
{
    @Value("${app.key}")
    public String appKey;

    @Value("${base.path.template}")
    public String basePathTemplate;

    @Value("${base.path.background}")
    public String basePathBackground;

    @Value("${base.name.result}")
    public String baseNameResult;
}