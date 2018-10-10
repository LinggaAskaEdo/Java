package encryption;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Base64;
import java.util.Random;

public class EncryptionPIN2
{
    public static void main(String[] args)
    {
        String publicKey = "MIIBCgKCAQEAu8N/CVgLw6FfvChKj1GAMw6Rsr/iXHZuCAMhBc35HvSFP/xyLQByoXhYnKUEmx6TdYpag1RpbREmHfAePn1YLZ5EV/slMsv0Gl33zWdjKvDdHFF0fT7AYBWGgDflhJW5vaDSq9vn4n1H61OAlIJThsTufLvDzL9WbZVm2gAQmnNesnf7rAeJIkYpuTpnkMYsfBv7kxVmaiT7bMlFd8J8kCB4uS6SsrHZ58tpAe+11HN5D1HEXpzVNsYCy1RvEMWsu+kjzL/GzOHjg6QvJdulN3xhZOe9MVGFjjNCvLMnr1w5Etz7tC9WaAS82YueY1D3AZJ0Wki1W5quffJpNWIpeQIDAQAB";

        //decode
        byte[] decodePublicKey = Base64.getDecoder().decode(publicKey);

        System.out.println("decodePublicKey: " + new String(Hex.encodeHex(decodePublicKey)));

        System.out.println("Random string: " + RandomStringUtils.randomAlphanumeric(10));

        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        while (sb.length() < 10)
        {
            sb.append(Integer.toHexString(r.nextInt()));
        }

        System.out.println(sb.toString().substring(0, 10).toUpperCase());
    }
}