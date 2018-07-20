package string;

import com.google.gson.Gson;

public class ReplacePlaceHolder
{
    public static void main(String[] args)
    {
        String cuid = "2557293";
        String url = "https://am.id00c1.id.infra/cabus-am/rest/account/getAccount?accountNumber=%s";

        String result = String.format(url, cuid);
        System.out.println("Result: " + result);

        /*float a = 5000000;
        float b = 3800000;

        System.out.println(b/a*100);

        String yourJson = "[{\"accountNumber\":3800005895,\"ledgerBalance\":{\"value\":-3230000,\"currency\":\"IDR\"},\"availableBalance\":{\"value\":2770000,\"currency\":\"IDR\"},\"holdBalance\":{\"value\":1000000,\"currency\":\"IDR\"},\"availableCashBalance\":{\"value\":2770000,\"currency\":\"IDR\"},\"overdueAmount\":{\"value\":0,\"currency\":\"IDR\"}}]";

        channelSearchEnum[] enums = new Gson.fromJson(yourJson, channelSearchEnum[].class);*/
    }
}