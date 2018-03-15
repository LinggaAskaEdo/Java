package encryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;

public class AmsEncryption
{
    private static final String password = "1rstWAP";

    public static void main(String[] args)
    {
        SimplePBEConfig simplePBEConfig = new SimplePBEConfig();
        simplePBEConfig.setAlgorithm("PBEWithMD5AndDES");
        simplePBEConfig.setPassword(password);

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setConfig(simplePBEConfig);
        encryptor.initialize();

        System.out.println(encryptor.decrypt("uMU6tY0ODpyUTtsgUsYBb7uY2rkNl/L8"));
    }
}