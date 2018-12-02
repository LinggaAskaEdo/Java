package springboot.lpe.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil
{
    public String decrypt(String text)
    {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("askacool");
        encryptor.setAlgorithm("PBEWithMD5AndDES");

        return encryptor.decrypt(text);
    }
}