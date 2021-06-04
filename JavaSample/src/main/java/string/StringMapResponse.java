package string;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StringMapResponse
{
    public static void main(String[] args)
    {
        Response response = new Response();

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("acm.enum.eventTypes.ANNIVERSARY_ACCOUNT", "Account anniversary");
        stringMap.put("acm.enum.eventTypes.BTDIR", "Direct internal payment order");

        response.setContent(stringMap);

        System.out.println(new Gson().toJson(response));
        System.out.println(stringMap.containsKey("ANNIVERSARY_ACCOUNT"));

//        <Optional> String keyValue = stringMap.keySet().stream().filter(key -> key.contains("ANNIVERSARY_ACCOUNT")).map(stringMap::get)
//                .findFirst()
//                .collect(Collectors.toList())
                ;
//        System.out.println(keyValue);
    }
}

class Response
{
    private Map<String, String> content;

    public Map<String, String> getContent()
    {
        return content;
    }

    public void setContent(Map<String, String> content)
    {
        this.content = content;
    }
}