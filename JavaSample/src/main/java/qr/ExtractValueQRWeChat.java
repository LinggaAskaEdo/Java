package qr;

import org.apache.commons.lang3.StringUtils;

import static java.lang.System.*;

public class ExtractValueQRWeChat
{
    public static void main(String[] args)
    {
        String data = "00020101021126570007SWP.COM0102JS0236ca88737518a9466bbfb923644f4338d9---B27990010com.alipay0181https://app.wepayez.com/spay/scanQr?qrcodeId=ca88737518a9466bbfb923644f4338d9---B51380015ID.OR.GPNQR.WWW0215ID20200252506515204581253033605802ID5907UBUDAHH6004BALI6105805716304F22A";

        out.println(StringUtils.substringBetween(data, "0215", "5204"));
        out.println(StringUtils.substringBetween(data, "5802ID", "60").substring(4).trim());
    }
}