package encryption;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PinblockTool
{
    private static final String PIN_PAD = "FFFFFFFFFFFFFF";

    public static void main(String[] args)
    {
        try
        {
            String pinISO1 = padPin("123456");
            System.out.println("FORMATTED ISO 1   : " + pinISO1);

            byte[] generatedKey = Encryption.generateKeyDua(pinISO1);
            byte[] newKeyBytes = ByteBuffer.wrap(new byte[24])
                    .put(Arrays.copyOfRange(generatedKey, 0, 16))
                    .put(Arrays.copyOfRange(generatedKey, 0, 8))
                    .array();

            byte[] encrypted = Encryption.encrypt(pinISO1, newKeyBytes);
            System.out.println("AFTER  " + Arrays.toString(newKeyBytes));
            System.out.println("ENCODED   " + Arrays.toString(encrypted));
            System.out.println("decrypted  " + Encryption.decrypt(encrypted, newKeyBytes));
//            System.out.println("HEX ARRAY " + bytesToHex(encrypted, SDKBase64.encode(encrypted).toCharArray()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes, char[] hexArray)
    {
        char[] hexChars = new char[bytes.length];

        for (int j = 0; j < bytes.length; j++)
        {
            int v = bytes[j] & 0xFF;
            hexChars[j] = hexArray[v >>> 4];
            hexChars[j] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static String padPin(String pin)
    {
        String pinBlockString = "1" + pin.length() + pin + PIN_PAD;
        pinBlockString = pinBlockString.substring(0, 16);
        return pinBlockString;

    }

    static class Encryption
    {
        static byte[] encrypt(String message, byte[] key) throws Exception
        {
            SecretKeyFactory mySecretKeyFactory = SecretKeyFactory.getInstance("DESede");
            KeySpec myKeySpec = new DESedeKeySpec(key);

            SecretKey encryptionKey = mySecretKeyFactory.generateSecret(myKeySpec);
            final Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);

            final byte[] plainTextBytes = message.getBytes("utf-8");
            final byte[] cipherText = cipher.doFinal(plainTextBytes);

            return cipherText;
        }


        static String decrypt(byte[] message, byte[] key) throws Exception
        {
            SecretKeyFactory mySecretKeyFactory = SecretKeyFactory.getInstance("DESede");
            KeySpec myKeySpec = new DESedeKeySpec(key);

            SecretKey encryptionKey = mySecretKeyFactory.generateSecret(myKeySpec);
            final Cipher decipher = Cipher.getInstance("DESede/ECB/NoPadding");
            decipher.init(Cipher.DECRYPT_MODE, encryptionKey);
            final byte[] plainText = decipher.doFinal(message);

            return new String(plainText, "UTF-8");
        }

        private static byte[] padKeyToLength(byte[] key, int len)
        {
            byte[] newKey = new byte[len];
            System.arraycopy(key, 0, newKey, 0, Math.min(key.length, len));
            return newKey;

        }

        static byte[] generateKeyDua(String pinNumber)
        {
            byte[] key;

            try
            {
                key = pinNumber.getBytes("UTF8");
            }
            catch (UnsupportedEncodingException e)
            {
                throw new IllegalArgumentException(e);
            }

            key = padKeyToLength(key, 24);
            System.out.println("KEY  : " + key);
            return key;
        }
    }
}