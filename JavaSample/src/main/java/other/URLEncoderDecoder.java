package other;

import org.apache.http.client.utils.URIBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class URLEncoderDecoder
{
    public static void main(String[] args)
    {
        //String urlMentah = "http://10.32.6.20:8081/audittrax/auditservice.do?targetNumber=6281374041104&auditMessage=User testing_superadmin did a SLIR on 6281374041104 from IP%2010.32.6.10&module=OMA&auditLevel=1&tokenId=52b44afb-2";
        //String urlFixed = "http://10.32.6.20:8081/audittrax/auditservice.do?targetNumber=6281374041104&auditMessage=User%20testing_superadmin%20did%20a%20SLIR%20on%206281374041104%20from%20IP%2010.32.6.10&module=OMA&auditLevel=1&tokenId=52b44afb-2";

        String urlMentah1 = "http://10.32.6.20:8081/audittrax/auditservice.do";
        String urlMentah2 = "targetNumber=6281374041104&auditMessage=User testing_superadmin did a SLIR on 6281374041104 from IP%2010.32.6.10&module=OMA&auditLevel=1&tokenId=52b44afb-2";

        //System.out.println(encoder1(urlMentah2));

        Map<String, String> mapParameter = new HashMap<>();
        mapParameter.put("targetNumber", "6281374041104");
        mapParameter.put("auditMessage", "User testing_superadmin did a SLIR on 6281374041104 from IP 10.32.6.10");
        mapParameter.put("module", "OMA");
        mapParameter.put("auditLevel", "1");
        mapParameter.put("tokenId", "d2c13850-d");

        System.out.println(encoder2(urlMentah1 + "?", mapParameter));
    }

    private static String encoder1(String value)
    {
        try
        {
            value =  URLEncoder.encode(value, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return value;
    }

    private static String encoder2(String url, Map<String, String> parameters)
    {
        URIBuilder ub = null;

        try
        {
            ub = new URIBuilder(url);

            for (Map.Entry<String, String> entry : parameters.entrySet())
            {
                System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
                ub.addParameter(entry.getKey(), entry.getValue());
            }
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }

        return ub != null ? ub.toString() : null;
    }
}