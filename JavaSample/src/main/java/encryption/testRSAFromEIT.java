package encryption;

import org.bouncycastle.asn1.pkcs.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

public class testRSAFromEIT
{
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException,
            IllegalBlockSizeException
    {
        testFromInternet();
        testFromInternet2();
    }

    private static void testFromInternet() throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String publicKeyB64 = "MIIBCgKCAQEAt1gf3YV/WMN2YewjXLHELgTcfgv8x12zbvrWi1ak6zeo7sDtIS/JLxX6f/o8q0oNzRR8Y4nxOOQw6T1GNaYZkc/DEy+XS3xs9Et3a7hZTHm7Oq7//x7A+YjYmKvaSt505ulbtw10BMKsExWkG5JSY3CLDSvoWGQQgkMTAWze7XpVHQQju7nAGt+ZIVgNqksZfjIkxyp1SW1XCanwUs6WvnqjbIbxY+DcxatipZTcnDnGLAympCm5IKnfPsMhEHvCfeMmcSZ/P7bMhoIPqiI4x1WvT76NK4q59qhqBKaNrn9nS3njurnHZQEPs6kEq5qjMj0Oc1ttwhAXKzUdK/S/BwIDAQAB";
        byte[] decoded = Base64.getDecoder().decode(publicKeyB64);

        org.bouncycastle.asn1.pkcs.RSAPublicKey pkcs1PublicKey = org.bouncycastle.asn1.pkcs.RSAPublicKey.getInstance(decoded);
        BigInteger modulus = pkcs1PublicKey.getModulus();
        BigInteger publicExponent = pkcs1PublicKey.getPublicExponent();

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, publicExponent);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey generatedPublic = kf.generatePublic(keySpec);
        System.out.printf("Modulus: %X%n", modulus);
        System.out.printf("Public exponent: %d ... 17? Why?%n", publicExponent); // 17? OK.
        System.out.printf("See, Java class result: %s, is RSAPublicKey: %b%n", generatedPublic.getClass().getName(), generatedPublic instanceof RSAPublicKey);
    }

    private static void testFromInternet2() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException
    {
        String asn1ZPK = "30240410DF1B0D8F14ED56AB9C80C0F4315DEE0A041000000000000000000000000000000000";

        String publicKeyB64 = "MIIBCgKCAQEAt1gf3YV/WMN2YewjXLHELgTcfgv8x12zbvrWi1ak6zeo7sDtIS/JLxX6f/o8q0oNzRR8Y4nxOOQw6T1GNaYZkc/DEy+XS3xs9Et3a7hZTHm7Oq7//x7A+YjYmKvaSt505ulbtw10BMKsExWkG5JSY3CLDSvoWGQQgkMTAWze7XpVHQQju7nAGt+ZIVgNqksZfjIkxyp1SW1XCanwUs6WvnqjbIbxY+DcxatipZTcnDnGLAympCm5IKnfPsMhEHvCfeMmcSZ/P7bMhoIPqiI4x1WvT76NK4q59qhqBKaNrn9nS3njurnHZQEPs6kEq5qjMj0Oc1ttwhAXKzUdK/S/BwIDAQAB";
        byte[] decoded = Base64.getDecoder().decode(publicKeyB64);

        org.bouncycastle.asn1.pkcs.RSAPublicKey pkcs1PublicKey = org.bouncycastle.asn1.pkcs.RSAPublicKey.getInstance(decoded);
        BigInteger modulus = pkcs1PublicKey.getModulus();
        BigInteger publicExponent = pkcs1PublicKey.getPublicExponent();

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, publicExponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey generatedPublic = keyFactory.generatePublic(keySpec);

        System.out.printf("Modulus: %X%n", modulus);
        System.out.printf("Public exponent: %d ... 17? Why?%n", publicExponent); // 17? OK.
        System.out.printf("See, Java class result: %s, is RSAPublicKey: %b%n", generatedPublic.getClass().getName(), generatedPublic instanceof RSAPublicKey);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, generatedPublic);
        byte[] encrypted = cipher.doFinal(DatatypeConverter.parseHexBinary(asn1ZPK));
        System.out.println("Result encryptZPK: " + DatatypeConverter.printHexBinary(encrypted));
        System.out.println("Result encryptZPK encoded: " + Base64.getEncoder().encodeToString(encrypted));
    }
}