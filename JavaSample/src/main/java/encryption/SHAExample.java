package encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAExample
{
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        String phoneNumber = "85715025257";
        System.out.println(phoneNumber.substring(Math.max(phoneNumber.length() - 6, 0)));

        String passwordToHash = "1425";
        byte[] salt = getSalt();

        String securePassword = (String) get_SHA_512_SecurePassword(passwordToHash, salt);
        System.out.println(securePassword);
    }

    private static Object get_SHA_512_SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;

        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte aByte : bytes)
            {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return generatedPassword;
    }

    //Add salt
    private static byte[] getSalt()
    {
        String phoneSalt = "123456";

        return phoneSalt.getBytes();
    }
}