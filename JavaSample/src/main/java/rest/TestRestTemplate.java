package rest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;

public class TestRestTemplate
{
    public static void main(String[] args)
    {
//        try
//        {
//            RestTemplate restTemplate = new RestTemplate();
//
//            String url = "http://dev.kaspro.id/api/081318978125/custom/validate";
//            String token = "WLu28cXFYvrdtQ7KFNxDUI3hpufmj+EbNknAEL9i7pfdjx69s/lnu3YSScaxUv+7Iere9Or5f1AvNC3rO8l+U3gkcU87vUrlHu6llGJeZiolpM2mD1ZePTlPyjVrArkmlK5Ui8vnGmu55anh2jq2Y4KD9HIj2FI8ENzfFqPX3/vmVH2e8ImkxsDuK1Ot+oH6BVxUKThhqcVPFfv3Qe52AA==";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("token", token);
//
//            HttpEntity<String> entity = new HttpEntity<>(headers);
//
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//
//            if (null != response && null != response.getBody() && !response.getBody().getData().isEmpty())
//            {
//                System.out.println(response.get);
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println("Error: " + e);
//        }
    }
}