package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;

import static java.lang.System.out;

public class FixJsonString
{
    public static void main(String[] args)
    {
//        String data = "{\"paymentType\":\"HCI_LOAN_FINANCING\",\"transactionId\":\"d4c745b3423d41f091304168e18419ab\",\"orderId\":\"test_retry001\",\"invoiceId\":null,\"createdAt\":\"2020-11-27 11:17:01\",\"informationCommodity\":\"ponsel-tablet\",\"airWillBillNumber\":null,\"expeditionName\":null,\"receiverName\":\"Dodi\",\"receiverRelation\":\"Saudara\",\"statusType\":\"ORDER\",\"status\":\"DELIVERED\",\"paymentDetail\":[]}";
        String data = "{\"deliveryAddress\":\"{\\\"address\\\":\\\"Direksikeet proyek terminal kijing , PT.wijaya karya (Persero) Tbk.\\r\\nJl.raya sungai kunyit , RT 10 RW 05 dusun mufakat desa sungai kunyit laut , kec.sungai kunyit ,Kab.mempawah , Kal_bar \\r\\ nKode pos- 78371\\\",\\\"province\\\":\\\"kalimantan barat\\\",\\\"city\\\":\\\"mempawah\\\",\\\"district\\\":null,\\\"ward\\\":null,\\\"postcode\\\":\\\"78371\\\"}\"}";
//        String data = "{\"deliveryAddress\": \"{\\\"address\\\":\\\"Direksikeet proyek terminal kijing , PT.wijaya karya (Persero) Tbk.\\r\\nJl.raya sungai kunyit , RT 10 RW 05 dusun mufakat desa sungai kunyit laut , kec.sungai kunyit ,Kab.mempawah , Kal_bar \\r\\nKode pos78371\\\",\\\"province\\\":\\\"kalimantan barat\\\",\\\"city\\\":\\\"mempawah\\\",\\\"district\\\":null,\\\"ward\\\":null,\\\"postcode\\\":\\\"78371\\\"}\"}";

        try
        {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            OrderTrackStatusRequest trackStatusRequest = gson.fromJson(data, OrderTrackStatusRequest.class);

           out.println(trackStatusRequest.toString());
        }
        catch (Exception e)
        {
            out.println("Error: " + e.getMessage());
            String columnError = StringUtils.substringBetween(e.getMessage(), "column", "path").trim();
            out.println("Column error: " + columnError);
            out.println(data.charAt(Integer.parseInt(columnError) - 1));
            out.println(data.charAt(Integer.parseInt(columnError) - 2));
            out.println(data.charAt(Integer.parseInt(columnError) - 3));
        }
    }
}
