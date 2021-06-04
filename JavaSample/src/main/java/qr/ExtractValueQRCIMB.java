package qr;

import org.apache.commons.lang3.StringUtils;

import static java.lang.System.out;

public class ExtractValueQRCIMB
{
    private static final String FORMAT_LENGTH = "%02d";

    public static void main(String[] args)
    {
        String data = "0111ID.CO.CIMBNIAGA.WWW             9360002200003380998000008158655616UME5391990003820626   5812IDRESTO MINI               DEPOK          16411     ID.OR.QRNPG.WWW                 ID102005001955000";

        String sdpfi = StringUtils.substring(data, 0, 2).trim();
        String sdpoi = StringUtils.substring(data, 2, 4).trim();
        String sdbdom = StringUtils.substring(data, 4, 36).trim();
        String sdmcpn = StringUtils.substring(data, 36, 55).trim();
        String sdmid = StringUtils.substring(data, 55, 70).trim();
        String sdmcia = StringUtils.substring(data, 70, 73).trim();
        String sdmcp = StringUtils.substring(data, 73, 92).trim();
        String sdmcc = StringUtils.substring(data, 92, 96).trim();
        String sdmccc = StringUtils.substring(data, 96, 98).trim();
        String sdmcnm = StringUtils.substring(data, 98, 123).trim();
        String sdmccy = StringUtils.substring(data, 123, 138).trim();
        String sdmcpc = StringUtils.substring(data, 138, 148).trim();
        String sdndom = StringUtils.substring(data, 148, 180).trim();
        String sdnmid = StringUtils.substring(data, 180, 195).trim();
        String sdtipt = StringUtils.substring(data, 195, 197).trim();

        out.println("sdpoi: " + sdpoi);
        out.println("sdmcp: " + sdmcp);

        StringBuilder result = new StringBuilder();

        //TAG-00
        result.append("00").append(String.format(FORMAT_LENGTH, sdpfi.length())).append(sdpfi);

        //TAG-01

        result.append("01").append(String.format(FORMAT_LENGTH, sdpoi.length())).append(sdpoi);

        //TAG-04
        if (!sdmcp.isEmpty())
            result.append("04").append("15").append(StringUtils.substring(sdmcp, 0, 15));

        //TAG-26
        StringBuilder tag26 = new StringBuilder();
        tag26.append("00").append("19").append(StringUtils.substring(sdbdom, 0, 19));
        tag26.append("01").append("18").append(StringUtils.substring(sdmcpn, 0, 18));
        tag26.append("02").append("15").append(StringUtils.substring(sdmid, 0, 15));
        tag26.append("03").append("03").append(StringUtils.substring(sdmcia, 0, 3));

        result.append("26").append(tag26.length()).append(tag26);

        //TAG-51
        if (!sdnmid.isEmpty())
        {
            StringBuilder tag51 = new StringBuilder();
            tag51.append("00").append(String.format(FORMAT_LENGTH, sdndom.length())).append(sdndom);
            tag51.append("02").append(String.format(FORMAT_LENGTH, sdnmid.length())).append(sdnmid);
            tag51.append("03").append("03").append(StringUtils.substring(sdmcia, 0, 3));

            result.append("51").append(tag51.length()).append(tag51);
        }

        //TAG-52
        result.append("52").append("04").append(sdmcc);

        //TAG-53
        result.append("53").append("03").append(sdmccc.equalsIgnoreCase("ID") ? "360" : "");

        //TAG-55
        if (!(sdtipt.equalsIgnoreCase("00") || sdtipt.equalsIgnoreCase("02")))
            result.append("55").append(String.format(FORMAT_LENGTH, sdtipt.length())).append(sdtipt);

        //TAG-58
        result.append("58").append("02").append(sdmccc);

        //TAG-59
        result.append("59").append(String.format(FORMAT_LENGTH, sdmcnm.length())).append(sdmcnm);

        //TAG-60
        result.append("60").append(String.format(FORMAT_LENGTH, sdmccy.length())).append(sdmccy);

        //TAG-61
        result.append("61").append(String.format(FORMAT_LENGTH, sdmcpc.length())).append(sdmcpc);

        //TAG-63
        result.append("63").append("04");
        result.append(generateCRC(result.toString().getBytes()));

        out.println(result.toString());
    }

    private static String generateCRC(byte[] data)
    {
        int crc = 0xFFFF;          // initial value
        int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12)

        for (byte b : data)
        {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b   >> (7-i) & 1) == 1);
                boolean c15 = ((crc >> 15    & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
            }
        }

        crc &= 0xffff;

        return Integer.toHexString(crc);
    }
}