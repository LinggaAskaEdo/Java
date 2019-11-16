package encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.security.SecureRandom;
import java.security.MessageDigest;

public class Crypto
{
    private static final String method = "AES/CBC/PKCS5PADDING";
    private static final String key = "KbPeShVmYq3t6v9y$B&E)H@McQfTjWnZ";

    private static String encrypt(String data)
    {
        try
        {
            // Get chiper method.
            Cipher cipher = Cipher.getInstance(method);

            // Random Iv bytes using length of defined chiper.
            SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] iv = new byte[cipher.getBlockSize()];
            randomSecureRandom.nextBytes(iv);

            // Hashing secret key.
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashKey = hash(md, key.getBytes(StandardCharsets.UTF_8));

            // Prepare Chiper Spec.
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec secretKeySpec = new SecretKeySpec(hashKey, "AES");

            // Init Chiper.
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);

            // Do Encryption.
            byte[] encryptedData = cipher.doFinal(data.getBytes());

            // Convert iv to hex.
            String hex2BinIv = DatatypeConverter.printHexBinary(iv);

            // Return encrypted data (converted iv as checksum).
            return hex2BinIv + Base64.getEncoder().encodeToString(encryptedData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    private static byte[] hash(MessageDigest md, byte[] key)
    {
        return md.digest(key);
    }

    private static String decrypt(String data)
    {
        try
        {
            // Get chiper method.
            Cipher cipher = Cipher.getInstance(method);

            // Getting checkum length.
            SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] ivB = new byte[cipher.getBlockSize()];
            randomSecureRandom.nextBytes(ivB);
            String hex2BinIv = DatatypeConverter.printHexBinary(ivB);
            int checksumLength = hex2BinIv.length();

            // Getting checksum (iv hex).
            String checksum = data.substring(0, checksumLength);

            // Convert checksum to bin.
            byte[] iv = DatatypeConverter.parseHexBinary(checksum);

            // Getting realdata beside checksum.
            String realData = data.substring(checksumLength, data.length());

            // Hashing secret key.
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashKey = hash(md, key.getBytes(StandardCharsets.UTF_8));

            // Prepare Chiper Spec.
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec secretKeySpec = new SecretKeySpec(hashKey, "AES");

            // Init Chiper.
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

            // Do Decrypt.
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(realData));

            return new String(decrypted);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args)
    {
        String data = "jancuks";
        String encrypted = encrypt(data);
        System.out.println(encrypted);

//        String decrypted = decrypt(encrypted);
        String decrypted = decrypt("17bbf067370680dbef601a484233a307MdSic5xspNGCy/F+MOIYu3qG4BWjcdA9EBQv9XeVegm1WUVjKbocj6YLq7XHkM4zpFYW/IButy4YkQE6QHvGmEYhMceEq4dCDTnrDPN5wDZdjP1FSrgEoViaYn4SMkjsCz6FhW4u+IlWOXKWaCAwX3XXiHfsgVgAGUHVMYNWi6l7Rgc94VExbdwNwq664y7fwCy6/LTZb1lUEtemnDe3+HBhmfQzwTOqNi6xfIXvxcTz1kchMG6621o/RHJdDRig5NN+AflLVG+FmPAyAxf3x3MeiSGnoWFOH3gYdcsd1Zdhbwya3crdyeivC1KmM8c1XMCwfaoBtajWuVlKPIPnGiQX6IUENlW+KcWFZmXZsuuiqj3SRP8yKIWcjjl8z1Tljfi2qxv7ia7YdCfJkJuB3jmPFjlrdIAaGkL8rFfkK1r0pNZAYNqhpAKJARyt9oDF");
        System.out.println(decrypted);
    }
}