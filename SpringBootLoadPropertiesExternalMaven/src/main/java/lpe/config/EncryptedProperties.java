package lpe.config;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.util.StringValueResolver;

class EncryptedProperties
{
    private static final String PRIVATE_KEY = "askacool";
    private static final String ALGORITHM = "PBEWithMD5AndDES";

    static EnvironmentStringPBEConfig createPBEConfig()
    {
        final EnvironmentStringPBEConfig encryptor = new EnvironmentStringPBEConfig();
        encryptor.setAlgorithm(ALGORITHM);
        encryptor.setPassword(PRIVATE_KEY);

        return encryptor;
    }

    public static class PlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer
    {
        private final StandardPBEStringEncryptor encryptor;

        PlaceholderConfigurer(final StandardPBEStringEncryptor encryptor)
        {
            this.encryptor = encryptor;
        }

        @Override
        protected void doProcessProperties(final ConfigurableListableBeanFactory beanFactoryToProcess, final StringValueResolver valueResolver)
        {
            super.doProcessProperties(beanFactoryToProcess, new EncryptedValueResolver(valueResolver, encryptor));
        }
    }

    private static class EncryptedValueResolver implements StringValueResolver
    {
        private final StringValueResolver valueResolver;
        private final PBEStringEncryptor stringEncryptor;

        private EncryptedValueResolver(final StringValueResolver stringValueResolver, final StandardPBEStringEncryptor stringEncryptor)
        {
            this.valueResolver = stringValueResolver;
            this.stringEncryptor = stringEncryptor;
        }

        @Override
        public String resolveStringValue(final String strVal)
        {
            final String value = valueResolver.resolveStringValue(strVal);

            return PropertyValueEncryptionUtils.isEncryptedValue(value) ? PropertyValueEncryptionUtils.decrypt(value, this.stringEncryptor) : value;
        }
    }
}