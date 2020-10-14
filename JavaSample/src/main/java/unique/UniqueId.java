package unique;

import org.apache.commons.codec.digest.DigestUtils;

import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

import static java.lang.System.out;

public class UniqueId
{
    private static final String LENGTH = ", length: ";
    private static final char[] DIGITS66 = {
            '0','1','2','3','4','5','6','7','8','9',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '-','.','_','~'
    };

    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        String method1 = method1();
        String method2 = method2();
        String method3 = method3();
        String method4 = method4();
        String method5 = method5();
        String method6 = method6();
        
        out.println("method1: " + method1 + LENGTH + method1.length());
        out.println("method2: " + method2 + LENGTH + method2.length());
        out.println("method3: " + method3 + LENGTH + method3.length());
        out.println("method4: " + method4 + LENGTH + method4.length());
        out.println("method5: " + method5 + LENGTH + method5.length());
        out.println("method6: " + method6 + LENGTH + method6.length());
    }

    private static String method1()
    {
        UUID u = UUID.randomUUID();

        return toIDString(u.getMostSignificantBits()) + toIDString(u.getLeastSignificantBits());
    }

    private static String toIDString(long bits)
    {
        char[] buf = new char[32];
        long z = 64;
        int cp = 32;
        long b = z - 1;

        do
        {
            buf[--cp] = DIGITS66[(int)(bits & b)];
            bits >>>= 6;
        }
        while (bits != 0);

        return new String(buf, cp, (32 - cp));
    }

    private static String method2() throws NoSuchAlgorithmException
    {
        //initialization of the application
        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

        //generate a random number
        String randomNum = Integer.toString(prng.nextInt());

        //get its digest
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        byte[] result = sha.digest(randomNum.getBytes());

        return Base64.getEncoder().encodeToString(result);
    }

    private static String method3()
    {
        UID userId = new UID();

        return userId.toString();
    }

    private static String method4()
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMdd");

        String contractNumber = "4000011070";
        String milis = String.valueOf(System.currentTimeMillis());
        out.println("milis: " + milis);

        long id = contractNumber.hashCode() + milis.hashCode();
//        int id = (contractNumber + milis).hashCode();
//        long id = (contractNumber + System.currentTimeMillis()).hashCode();

        out.println(contractNumber.hashCode());
        out.println(milis.hashCode());
        out.println("id: " + id);

        double length = String.valueOf(id).length();
        out.println("length: " + length);
        double maxLength = 666;

        if (String.valueOf(id).length() > maxLength)
        {
            id = (long) (id / Math.pow(13.0, length - maxLength));
        }

        return dateTimeFormatter.format(LocalDateTime.now()) + id;
    }

    private static String method5()
    {
        String result = null;

        try 
        {
            // cryptographically strong random number generator. Options: NativePRNG or SHA1PRNG
            SecureRandom crunchifyPRNG = SecureRandom.getInstance("NativePRNG");

            // generate a random number
            String crunchifyRandomNumber = Integer.toString(crunchifyPRNG.nextInt());

            // Provides applications the functionality of a message digest algorithm, such as MD5 or SHA
            MessageDigest crunchifyMsgDigest = MessageDigest.getInstance("SHA-256");

            // Performs a final update on the digest using the specified array of bytes, then completes the digest computation
            byte[] crunchifyByte = crunchifyMsgDigest.digest(crunchifyRandomNumber.getBytes());

//            out.println("- Generated Random number: " + crunchifyRandomNumber);
//            out.println("- Generated Random byte: " + Arrays.toString(crunchifyByte));
            result = crunchifyEncodeUsingHEX(crunchifyByte).toString();
        }
        catch (Exception e) 
        {
            out.println("Error during creating MessageDigest");
        }

        return result;
    }

    private static StringBuilder crunchifyEncodeUsingHEX(byte[] crunchifyByte)
    {
        StringBuilder crunchifyResult = new StringBuilder();
        char[] crunchifyKeys = { 'A', 'B', 'C', 'D', 'E', 'X', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

//        out.println(crunchifyByte.length);

        for (byte myByte : crunchifyByte)
        {
            // Appends the string representation of the char argument to this sequence
//            out.println("hex1: " + myByte);
//            out.println("hex2: " + (myByte & 0xf0));
//            out.println("hex3: " + ((myByte & 0xf0) >> 5));
//            out.println("hex4: " + (myByte & 0x0f));
            crunchifyResult.append(crunchifyKeys[(myByte & 0xf0) >> 5]);
            crunchifyResult.append(crunchifyKeys[myByte & 0x0f]);
        }

        return crunchifyResult;
    }

    private static String method6()
    {
        String ts = String.valueOf(System.currentTimeMillis());
//        out.println("ts: " + ts);
        String rand = UUID.randomUUID().toString();
//        out.println("rand: " + rand);

        return DigestUtils.md5Hex(rand + ts);
    }
}