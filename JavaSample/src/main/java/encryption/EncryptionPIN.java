package encryption;

import com.fasterxml.uuid.Generators;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.DESedeKeyGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

//import org.apache.commons.codec.binary.Base64;

public class EncryptionPIN
{
    public static void main(String[] args)
    {
        //generate UUID version 1
        UUID uuid = Generators.timeBasedGenerator().generate();
        String timeuuid = uuid.toString();
        System.out.println("timeuuid: " + timeuuid);

        String encodedPubKeyBase64 = null;
        String encodedPvtKeyBase64;

        //demo to generate RSA key
        try
        {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();
            Key pub = kp.getPublic();
            Key pvt = kp.getPrivate();

            byte[] encodedPubKey = pub.getEncoded();
            //encodedPubKeyBase64 = Base64.encodeBase64String(encodedPubKey);
            encodedPubKeyBase64 = Base64.getEncoder().encodeToString(encodedPubKey);

            byte[] encodedPvtKey = pvt.getEncoded();
            //encodedPvtKeyBase64 = Base64.encodeBase64String(encodedPvtKey);
            encodedPvtKeyBase64 = Base64.getEncoder().encodeToString(encodedPvtKey);

            System.out.println("encode pub: " + encodedPubKeyBase64);
            System.out.println("encode pvt: " + encodedPvtKeyBase64);
        }
        catch (Exception e)
        {
            System.out.println("Error when generate RSA key: " + e);
        }

        if (null != encodedPubKeyBase64)
        {
            //test decode RSA key
//        try
//        {
//            byte publicKeyData[] = Base64.decodeBase64(encodedPubKeyBase64); //Base64.decode(publicKeyStr, Base64.DEFAULT);
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            RSAPublicKey publicKeyTest = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyData));
//            System.out.println("publicKeyTest: " + publicKeyTest);
//        }
//        catch (Exception e)
//        {
//            System.out.println("Error when test decode RSA key: " + e);
//        }

            //test decode RSA key 2

            try
            {
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(encodedPubKeyBase64.getBytes()));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey publicKey = keyFactory.generatePublic(keySpec);

//                System.out.println("publicKey: " + publicKey);
                System.out.println("publicKey HEX: " + new String(Hex.encodeHex(publicKey.getEncoded())));
            }
            catch (NoSuchAlgorithmException | InvalidKeySpecException e)
            {
                System.out.println("Error when test decode RSA key 2: " + e);
            }
        }

        //create ZPK
//        KeyGenerationParameters keyGenParams = new KeyGenerationParameters(new SecureRandom(), 128);
//        DESedeKeyGenerator keyGen = new DESedeKeyGenerator();
//        keyGen.init(keyGenParams);
//        byte[] zpk = keyGen.generateKey();
//        System.out.println("zpk: " + Arrays.toString(zpk));

        //create ZPK2
        SecretKey k1 = generateDESkey();
//        SecretKey k2 = generateDESkey();
//        SecretKey k3 = generateDESkey();

        if (null != k1)
        {
            System.out.println("k1 HEX: " + new String(Hex.encodeHex(k1.getEncoded())));

//            PKCS12Attribute attribute = new PKCS12Attribute(new String(Hex.encodeHex(k1.getEncoded())).getBytes());

//            System.out.println("ASN.1 DER encoding: " + new String(Hex.encodeHex(attribute.getEncoded())));

            //encryptPinBlockByZPK
            String pinBlock = "14999985676F6EE3";

            try
            {
//                SecretKeySpec keySpec = new SecretKeySpec(k1.getEncoded(), "AES");
//                SecretKey key = SecretKeyFactory.getInstance("AES").generateSecret(keySpec);

                Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
                cipher.init(Cipher.ENCRYPT_MODE, k1);

                // Encrypt
                byte[] enc = cipher.doFinal(pinBlock.getBytes());

                // Encode bytes to base64 to get a string
                System.out.println("Encrypt: " + new String(Hex.encodeHex(enc)));
            }
            catch (Exception e)
            {
                System.out.println("Error when encryptPinBlockByZPK: " + e);
            }
        }

//        if (null != k2)
//        {
//            System.out.println("k2 HEX: " + new String(Hex.encodeHex(k2.getEncoded())));
//        }

//        if (null != k3)
//        {
//            System.out.println("k3 HEX: " + new String(Hex.encodeHex(k3.getEncoded())));
//        }



//        Security.addProvider(new BouncyCastleProvider());
//
//        try
//        {
//            KeySpec keySpec = new DESedeKeySpec(zpk.getBytes());
//            SecretKey key = SecretKeyFactory.getInstance("DESede").generateSecret(keySpec);
//
//            Cipher encrypter = Cipher.getInstance("DESede/ECB/PKCS7Padding", "BC");
//            encrypter.init(Cipher.ENCRYPT_MODE, key);
//
//            //encrypt
//            System.out.println(new String(Hex.encodeHex(encrypter.doFinal(pinBlock.getBytes()))));
//        }
//        catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | InvalidKeySpecException | IllegalBlockSizeException e)
//        {
//            e.printStackTrace();
//        }
    }

    private static SecretKey generateDESkey()
    {
        KeyGenerator keyGen = null;

        try
        {
            keyGen = KeyGenerator.getInstance("AES");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        if (keyGen != null)
        {
            keyGen.init(128); // key length 112 for two keys, 168 for three keys
        }

        return keyGen != null ? keyGen.generateKey() : null;
    }
}