package encryption;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;

public class Generate3DESKeys
{
//    private static final String KCV_DEFAULT_DATA = "0000000000000000";
    private static final String ASN1_DER_HEADER = "3024";
    private static final String ASN1_DER_CONTENT = "0410";

    public static void main(String[] args)
    {
        UUID uuid = UUID.randomUUID();
        System.out.println("uuid: " + uuid.toString());

        List<byte[]> keyList = new ArrayList<>();

        for (int i = 1; i < 4; i++)
        {
            byte[] key = generateKey();
            String result = DatatypeConverter.printHexBinary(key);
            System.out.println("ZPK component #" + i + ": " + result);
//            System.out.println("KCV: " + countKCV(result));
            keyList.add(key);
        }

        byte[] masterKey = buildKey(keyList.get(0), keyList.get(1), keyList.get(2));
        String strMasterKey = DatatypeConverter.printHexBinary(masterKey);
        System.out.println("ZPK final: " + strMasterKey);
//        System.out.println("KCV: " + countKCV(strMasterKey));

        String asn1ZPK = ASN1_DER_HEADER + ASN1_DER_CONTENT + strMasterKey + ASN1_DER_CONTENT + StringUtils.repeat("0", strMasterKey.length());
        System.out.println("ASN1-ZPK: " + asn1ZPK);

        String pinBlock = generatePinBlock();
        System.out.println("PIN Block: " + pinBlock);

        String encryptPinBlock = encryptPinBlock(pinBlock, strMasterKey);
        System.out.println("Encrypt PIN Block: " + encryptPinBlock);

        String publicKeyRSA = "MIIBCgKCAQEAtkloQHvDBjiNLI/xkykmIJxLr020JPLH9TxAh4Bq0VycGeeeYrL1WXPo4+7Wq1bmAMnTyOOpuyNb/ErLe/xxj/xeq+hMHhBG1clUSLcvwrOCwwfFZbYMBT4DLiKOdyW622r24VwZQPWwP6nbYMp6JmQpfXTOaRovFuRRb9Dxzmp3AEqisWVp8dOLYRFYIYET7gy6mqnY8dw/pz3BfcXXFgI9eRm5cOb8yryNJ0/rYvBps8JW1XTvm29/84iw1XUVOYMDzd3zdzW//Z1bkvo+xoTsjDsBstYJ49YGWyyAnolPfEvWADRUMnRJaPtowlUBJY8W9ix7Po4kUHb19thPIwIDAQAB";
        encryptZPK(asn1ZPK, publicKeyRSA);
    }

    private static String encryptPinBlock(String pinBlock, String zpk)
    {
        String result = null;

        try
        {
            //new method
            byte[] tmp = hexToByte(zpk);
            byte[] key = new byte[24];
            System.arraycopy(tmp, 0, key, 0, 16);
            System.arraycopy(tmp, 0, key, 16, 8);
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DESede"));
            byte[] plaintext = cipher.doFinal(hexToByte(pinBlock));

            result = byteToHex(plaintext);
        }
        catch (Exception e)
        {
            System.out.println("Error when encryptPinBlockByZPK: " + e);
        }

        return result;
    }

    private static byte[] hexToByte(String hex)
    {
        if ((hex.length() & 0x01) == 0x01)
            throw new IllegalArgumentException();

        byte[] bytes = new byte[hex.length() / 2];

        for (int idx = 0; idx < bytes.length; ++idx)
        {
            int hi = Character.digit((int) hex.charAt(idx * 2), 16);
            int lo = Character.digit((int) hex.charAt(idx * 2 + 1), 16);

            if ((hi < 0) || (lo < 0))
                throw new IllegalArgumentException();

            bytes[idx] = (byte) ((hi << 4) | lo);
        }

        return bytes;
    }

    private static String byteToHex(byte[] bytes)
    {
        char[] hex = new char[bytes.length * 2];

        for (int idx = 0; idx < bytes.length; ++idx)
        {
            int hi = (bytes[idx] & 0xF0) >>> 4;
            int lo = (bytes[idx] & 0x0F);
            hex[idx * 2] = (char) (hi < 10 ? '0' + hi : 'A' - 10 + hi);
            hex[idx * 2 + 1] = (char) (lo < 10 ? '0' + lo : 'A' - 10 + lo);
        }

        return new String(hex);
    }

    private static String generatePinBlock()
    {
        // Using numeric values
        String numbers = "0123456789";
        int pinLenght = 6;
        int numberLength = 8;

        // Using random method
        Random random = new Random();

        char[] chars = new char[pinLenght];

        for (int i = 0; i < pinLenght; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            chars[i] = numbers.charAt(random.nextInt(numbers.length()));
        }

        String pin =  String.valueOf(chars);

        //generate random number
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        while (sb.length() < numberLength)
        {
            sb.append(Integer.toHexString(r.nextInt()));
        }

        System.out.println("PIN: " + pin);

        String randomNumber =  sb.toString().substring(0, numberLength).toUpperCase();

        return "1" + pin.length() + pin + randomNumber;
    }

//    private static byte[] readFileBytes(String filename) throws IOException
//    {
//        Path path = Paths.get(filename);
//        return Files.readAllBytes(path);
//    }

    private static void encryptZPK(String asn1ZPK, String publicKeyRSA)
    {
        try
        {
            byte[] decodeKeyRSA = Base64.getDecoder().decode(publicKeyRSA);

            System.out.println("decodeKeyRSA: " + DatatypeConverter.printHexBinary(decodeKeyRSA));

//            System.out.println("Encode ASN1-ZPK: " + Arrays.toString(Base64.getEncoder().encode(DatatypeConverter.parseHexBinary(asn1ZPK))));

            //generate public key
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodeKeyRSA);

            //generate private key
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodeKeyRSA);

//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(keySpec);
//            RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

//            RSAPublicKeySpec keySpecRsa = new RSAPublicKeySpec(key.getModulus(), key.getPublicExponent());
//            KeyFactory fact = KeyFactory.getInstance("RSA");
//            PublicKey generatedPublic = fact.generatePublic(keySpecRsa);

//            RSAPrivateKeySpec keySpecRsa = new RSAPrivateKeySpec(key.getModulus(), key.getPrivateExponent());
//            KeyFactory fact = KeyFactory.getInstance("RSA");
//            PrivateKey pKey = fact.generatePrivate(keySpecRsa);

            //bouncy
            RSAPublicKey pkcs1PublicKey = RSAPublicKey.getInstance(decodeKeyRSA);
            BigInteger modulus = pkcs1PublicKey.getModulus();
            BigInteger publicExponent = pkcs1PublicKey.getPublicExponent();
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, publicExponent);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey generatedPublic = kf.generatePublic(keySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, generatedPublic);
            byte[] encrypted = cipher.doFinal(DatatypeConverter.parseHexBinary(asn1ZPK));

//            System.out.println("Result encryptZPK: " + DatatypeConverter.printHexBinary(encrypted));
            System.out.println("Result encryptZPK encoded: " + Base64.getEncoder().encodeToString(encrypted));
        }
        catch (Exception e)
        {
            System.out.println("Error when encryptZPK: " + e);
        }
    }

    private static byte[] buildKey(byte[] cc1, byte[] cc2, byte[] cc3)
    {
        byte[] result = new byte[cc1.length];
        int i = 0;

        for (byte b1: cc1)
        {
            byte b2 = cc2[i];
            byte b3 = cc3[i];
            result[i] = (byte) (b1 ^ b2 ^ b3);
            i++;
        }

        return result;
    }

    private static byte[] generateKey()
    {
        SecureRandom random = new SecureRandom();
        byte[] tripleDesKey = new byte[16];
        random.nextBytes(tripleDesKey);

        for (int i = 0; i < tripleDesKey.length; i++)
        {
            int keyByte = (tripleDesKey[i] & 0xFE);
            int parity = 0;

            for (int b = keyByte; b != 0; b >>= 1)
            {
                parity ^= b & 1;
            }

            tripleDesKey[i] = (byte)(keyByte | (parity == 0 ? 1 : 0));
        }

        return tripleDesKey;

    }

//    private static String countKCV(String keyString)
//    {
//        byte[] result = new byte[0];
//
//        try
//        {
//            byte[] keyValue = Hex.decodeHex(keyString.toCharArray());
//
//            //create key from string
////            byte[] keyValue = new byte[24]; // a Triple DES key is a byte[24] array
////
////            for (int i = 0; i < keyString.length() && i < keyValue.length; i++)
////            {
////                keyValue[i] = (byte) keyString.charAt(i);
////            }
//
//            // Make the Key
//            SecretKey key = new SecretKeySpec(keyValue, "DESede");
//            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//
//            byte[] kcvDefaultDataBytes = KCV_DEFAULT_DATA.getBytes();
//
//            result = cipher.doFinal(kcvDefaultDataBytes);
//        }
//        catch (Exception e)
//        {
//            System.out.println("Error when countKCV: " + e);
//        }
//
//        String resultHex = new String(Hex.encodeHex(result)).toUpperCase();
//
//        return resultHex.substring(0, Math.min(resultHex.length(), 6));
//    }
}