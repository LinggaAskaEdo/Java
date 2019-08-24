package encryption;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class SampleAES256Ex2
{
    public static void main(String[] args) throws Exception {
        String key = "KbPeShVmYq3t6v9y$B&E)H@McQfTjWnZ";
        String clean = "{\n" +
                "  \"fromaccount\": \"08161198857\",\n" +
                "  \"transid\": \"5d172115fd50cabde0805fa0\",\n" +
                "  \"accountname\": \"AN\",\n" +
                "  \"businessname\": \"BN\",\n" +
                "  \"firstname\": \"FN\",\n" +
                "  \"middlename\": \"MN\",\n" +
                "  \"lastname\": \"LN\",\n" +
                "  \"regioncode\": \"RC\",\n" +
                "  \"citycode\": \"CC\",\n" +
                "  \"postalcode\": \"PC\",\n" +
                "  \"coordinates\": \"0,0\",\n" +
                "  \"specificaddress\": \"SA\",\n" +
                "  \"authorizedmobile\": \"0899999992\",\n" +
                "  \"validid\": \"SAM\",\n" +
                "  \"valididdesc\": \"EMAIL\"\n" +
                "}";

        byte[] encrypted = encrypt(clean, key);
        System.out.println(Hex.encodeHexString(encrypted));

        String decrypted = decrypt(encrypted, key);
        System.out.println(decrypted);
    }

    private static byte[] encrypt(String plainText, String key) throws Exception {
        byte[] clean = plainText.getBytes();

        // Generating IV.
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Hashing key.
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(key.getBytes(StandardCharsets.UTF_8));

        byte[] keyBytes = new byte[16];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Encrypt.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(clean);

        // Combine IV and encrypted part.
        byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
        System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
        System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);

        return encryptedIVAndText;
    }

    private static String decrypt(byte[] encryptedIvTextBytes, String key) throws Exception {
        int ivSize = 16;
        int keySize = 16;

        // Extract IV.
        byte[] iv = new byte[ivSize];
        System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Extract encrypted part.
        int encryptedSize = encryptedIvTextBytes.length - ivSize;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);

        // Hash key.
        byte[] keyBytes = new byte[keySize];
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(key.getBytes());
        System.arraycopy(md.digest(), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Decrypt.
        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

        return new String(decrypted);
    }
}