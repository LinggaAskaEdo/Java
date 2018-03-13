package json;

import com.google.gson.Gson;

public class TestIsmscNode
{
    public static void main(String[] args)
    {
        String jsonString = "{\n" +
                "        \"_id\": \"ET1\",\n" +
                "        \"host\": \"ET1\",\n" +
                "        \"url\": \"http://127.0.0.1:8090/\",\n" +
                "        \"methods\": [\n" +
                "          \"fst\",\n" +
                "          \"ati\",\n" +
                "          \"psi\",\n" +
                "          \"enh\"\n" +
                "        ]\n" +
                "      }";

        Gson gson = new Gson();
        IsmscNode ismscNode = gson.fromJson(jsonString, IsmscNode.class);

        System.out.println(ismscNode);
    }
}