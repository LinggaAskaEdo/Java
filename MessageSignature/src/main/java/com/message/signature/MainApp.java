package com.message.signature;

import com.google.gson.Gson;
import com.message.signature.model.Message;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class MainApp
{
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException
    {
        /*TimeStamp*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        String timestamp = simpleDateFormat.format(new Date());

        System.out.println("timestamp: " + timestamp);

        /*API Key*/
        String apikey = "cf5456ed-65a1-480b-ac6e-8aad99ce5a30";

        /*Http Verb*/
        String httpVerb = "Get".toUpperCase();

        System.out.println("httpVerb: " + httpVerb);

        /*Body MD5*/
        Message message = new Message();
        message.setApplicationCode("ALSA");
        message.setFintechCode("35");
        message.setReferenceNo("70");

        String jsonStr = new Gson().toJson(message);

        System.out.println("Json: " + jsonStr);

        byte[] hashInBytes = MessageDigest.getInstance("MD5").digest(jsonStr.getBytes(StandardCharsets.UTF_8));

        String bodymd5 = Base64.getEncoder().encodeToString(hashInBytes);

        System.out.println("Body MD5: " + bodymd5);

        /*Hmac as Base64*/
        String secret = "API Secret";
        String hmacsignature  = timestamp + ":" + apikey + ":" + httpVerb + ":" + bodymd5;

        Mac sha256HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256HMAC.init(secretKey);

        String hash = Base64.getEncoder().encodeToString(sha256HMAC.doFinal(hmacsignature.getBytes()));
        System.out.println("hmacAsBase64: " + hash);

        /*Signature*/
        String authString = apikey + hash;
        String signature = Base64.getEncoder().encodeToString(authString.getBytes(StandardCharsets.UTF_8));
        System.out.println("Signature: " + signature);
    }
}