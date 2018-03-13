package json;

import com.google.gson.Gson;

/**
 * Created by Lingga on 02/05/17.
 */

public class TestJson
{
    public static void main(String[] args)
    {
        String jsonStr = "{\n" +
                "  \"param\": {\n" +
                "    \"network\": {\n" +
                "      \"tadigCode\": \"OMNGT\",\n" +
                "      \"name\": \"Omantel (Oman Mobile)\",\n" +
                "      \"country\": {\n" +
                "        \"iso3166\": \"OMN\",\n" +
                "        \"name\": \"Oman\",\n" +
                "        \"countryCode\": 968,\n" +
                "        \"mcc\": 422\n" +
                "      },\n" +
                "      \"mccmnc\": 42202,\n" +
                "      \"ccnc\": 96892\n" +
                "    },\n" +
                "    \"allowedIsmsc\": {\n" +
                "      \"OMT\": {\n" +
                "        \"_id\": \"OMT\",\n" +
                "        \"host\": \"OMT\",\n" +
                "        \"url\": \"http://ismsc1.ssd.ams/request.php\",\n" +
                "        \"methods\": [\n" +
                "          \"fst\"\n" +
                "        ],\n" +
                "        \"highAccuracyMethods\": []\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"requestUuid\": \"f8b4d4ff-cedd-485e-8c82-f3619c506104\",\n" +
                "  \"type\": \"locate\",\n" +
                "  \"version\": \"1.0\"\n" +
                "}";

        Gson gson = new Gson();
        Response response = gson.fromJson(jsonStr, Response.class);

        System.out.println(response.getParam().getNetwork());
    }
}