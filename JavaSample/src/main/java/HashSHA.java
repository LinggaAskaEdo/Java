import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.util.Formatter;

/**
 * Created by edo on 06/12/16.
 */

public class HashSHA
{
    private static final String HASH_ALGORITHM = "HmacSHA256";

    public static void main(String[] args) throws SignatureException
    {
        //System.out.println(withoutKey("This is a test"));
        System.out.println(withKey("This is a test", "abc"));
    }

    private static String withoutKey(String input)
    {
        String digestResultHex = null;

        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(input.getBytes("UTF-8"));
            byte[] digestResult = messageDigest.digest();

            digestResultHex = String.format("%064x", new BigInteger(1, digestResult));
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("Error algorithm: " + e.getMessage());
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println("Error unsupport encoding: " + e.getMessage());
        }

        return digestResultHex;
    }

    private static String withKey(String input, String key) throws SignatureException
    {
        try
        {
            Key sk = new SecretKeySpec(key.getBytes(), HASH_ALGORITHM);
            Mac mac = Mac.getInstance(sk.getAlgorithm());
            mac.init(sk);
            final byte[] hmac = mac.doFinal(input.getBytes());

            return toHexString(hmac);
        }
        catch (NoSuchAlgorithmException e1)
        {
            throw new SignatureException("error building signature, no such algorithm in device " + HASH_ALGORITHM);
        }
        catch (InvalidKeyException e)
        {
            throw new SignatureException("error building signature, invalid key " + HASH_ALGORITHM);
        }
    }

    private static String toHexString(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder(bytes.length * 2);

        Formatter formatter = new Formatter(sb);
        for (byte b : bytes)
        {
            formatter.format("%02x", b);
        }

        return sb.toString();
    }
}