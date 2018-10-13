package id.co.homecredit.simulator.util;

import com.google.gson.Gson;
import id.co.homecredit.simulator.model.Request;
import id.co.homecredit.simulator.model.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimulatorUtil
{
    private static final String ASN1_DER_HEADER = "3024";
    private static final String ASN1_DER_CONTENT = "0410";

    public boolean checkNumber(String number)
    {
        boolean status = false;

        try
        {
            if (Long.valueOf(number) > 0)
            {
                status = true;
            }
        }
        catch (Exception ignored)
        {}

        return status;
    }

    public Response sendHttpPost(String url, Request request)
    {
        Response response = new Response();
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try
        {
            HttpPost httpPost = new HttpPost(url);

            StringEntity params = new StringEntity(new Gson().toJson(request));

            httpPost.addHeader("content-type", "application/json");
            httpPost.setEntity(params);

            HttpResponse httpResponse = httpClient.execute(httpPost);

            if (null != httpResponse)
            {
                InputStream inputStream = httpResponse.getEntity().getContent(); //Get the data in the entity
                Reader reader = new InputStreamReader(inputStream);
                response = new Gson().fromJson(reader, Response.class);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error when sendHttpPost: " + e);
        }

        return response;
    }

    public String generatePin()
    {
        boolean status = false;
        String numbers = "0123456789";
        int pinLenght = 6;
        String pin = null;

        while (!status)
        {
            Random random = new Random();
            char[] chars = new char[pinLenght];

            for (int i = 0; i < pinLenght; i++)
            {
                chars[i] = numbers.charAt(random.nextInt(numbers.length()));
            }

            pin =  String.valueOf(chars);
            status = isPinValid(pin);
        }

        return pin;
    }

    private boolean isPinValid(String pin)
    {
        return !isBlankOrNot6Digit(pin) && isPinWeak(pin);
    }

    private boolean isBlankOrNot6Digit(CharSequence string)
    {
        return string == null || string.toString().trim().length() != 6;
    }

    private boolean isPinWeak(String pin)
    {
        boolean isPinValid = false;

        String regex = "^(000000|111111|222222|333333|444444|" +
                "555555|666666|777777|888888|999999|012345|" +
                "123456|234567|345678|456789|567890|" +
                "543210|654321|765432|876543|987654|098765)$";

        Pattern mPattern = Pattern.compile(regex);

        Matcher matcher = mPattern.matcher(pin);

        if (!matcher.find())
        {
            isPinValid = true;
        }

        return isPinValid;
    }

    public Request generateRequestSetPin(String userId, String uuid, String pin, String publicKey)
    {
        Request request = new Request();
        request.setUserId(userId);
        request.setConnectionId(uuid);

        List<byte[]> keyList = new ArrayList<>();

        for (int i = 1; i < 4; i++)
        {
            byte[] key = generateKey();
            String result = DatatypeConverter.printHexBinary(key);
            keyList.add(key);
        }

        byte[] masterKey = buildKey(keyList.get(0), keyList.get(1), keyList.get(2));
        String strMasterKey = DatatypeConverter.printHexBinary(masterKey);

        String asn1ZPK = ASN1_DER_HEADER + ASN1_DER_CONTENT + strMasterKey + ASN1_DER_CONTENT + StringUtils.repeat("0", strMasterKey.length());

        String pinBlock = generatePinBlock(pin);

        String encryptPinBlock = encryptPinBlock(pinBlock, strMasterKey);

        String encryptZPK = encryptZPK(asn1ZPK, publicKey);

        request.setEncryptedPinBlock(encryptPinBlock);
        request.setSessionZpkKey(encryptZPK);

        return request;
    }

    private byte[] generateKey()
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

    private byte[] buildKey(byte[] cc1, byte[] cc2, byte[] cc3)
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

    private String generatePinBlock(String pin)
    {
        int numberLength = 8;

        //generate random number
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        while (sb.length() < numberLength)
        {
            sb.append(Integer.toHexString(r.nextInt()));
        }

        String randomNumber =  sb.toString().substring(0, numberLength).toUpperCase();

        return "1" + pin.length() + pin + randomNumber;
    }

    private String encryptPinBlock(String pinBlock, String zpk)
    {
        String result = null;

        try
        {
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
            System.out.println("Error when encryptPinBlock: " + e);
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

    private String encryptZPK(String asn1ZPK, String publicKeyRSA)
    {
        String result = null;

        try
        {
            byte[] decodeKeyRSA = Base64.getDecoder().decode(publicKeyRSA);

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

            result = Base64.getEncoder().encodeToString(encrypted);
        }
        catch (Exception e)
        {
            System.out.println("Error when encryptZPK: " + e);
        }

        return result;
    }
}