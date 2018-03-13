package telco;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Lingga on 25/07/17.
 */

public class TestTelco
{
    public static void main(String[] args)
    {
        List<SftpSetup> sftpSetupList = new ArrayList<>();

        SftpSetup setup1 = new SftpSetup();
        setup1.setSetupId(519);
        setup1.setName("KBX10");
        setup1.setMcc("602");
        setup1.setMnc("3");
        setup1.setIphost("10.32.9.21");
        setup1.setPort(22);
        setup1.setUsername("edo");
        setup1.setPassword("#1rstwap");
        setup1.setLocalDir("/home/edo/telco/LOCALDIR/testLingga");
        setup1.setRemoteDir("/home/edo/telco/REMOTEDIR/testLingga/temp");
        setup1.setInputFileExt("txt");
        setup1.setOutputFileExt("log");
        setup1.setProtocol("SFTP");
        setup1.setDecompressMethod("gz");
        setup1.setDecoding(true);
        setup1.setExtract(false);
        setup1.setVendorId(2);
        setup1.setRadioTech("3G");
        setup1.setTraceReference("http://10.32.9.189:8080/");
        setup1.setLocationIntrogateUrl(null);
        setup1.setCreated(new Date());

        SftpSetup setup2 = new SftpSetup();
        setup2.setSetupId(519);
        setup2.setName("KBX11");
        setup2.setMcc("603");
        setup2.setMnc("4");
        setup2.setIphost("10.32.9.22");
        setup2.setPort(23);
        setup2.setUsername("edox");
        setup2.setPassword("#1rstwapx");
        setup2.setLocalDir("/home/edo/telco/LOCALDIR/testLinggax");
        setup2.setRemoteDir("/home/edo/telco/REMOTEDIR/testLingga/tempx");
        setup2.setInputFileExt("dat");
        setup2.setOutputFileExt("logs");
        setup2.setProtocol("SFTP");
        setup2.setDecompressMethod("gz");
        setup2.setDecoding(true);
        setup2.setExtract(false);
        setup2.setVendorId(2);
        setup2.setRadioTech("3G");
        setup2.setTraceReference("http://10.32.9.189:8080/");
        setup2.setLocationIntrogateUrl(null);
        setup2.setCreated(new Date());

        sftpSetupList.add(setup1);
        sftpSetupList.add(setup2);

        //String setupString1 = setup1.toString();
        //System.out.println("setupString: " + setupString1);

        if (sftpSetupList != null && sftpSetupList.size() > 0)
        {
            for (SftpSetup setup : sftpSetupList)
            {
                setup.setRadioTech("4G");
            }
        }

        System.out.println(sftpSetupList);

        /*
        byte[] xxx = {1, 0, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 35, -117, 8, 81, 121, -58, -1, 0, -71, 6, 1, -110, 4, 1, -110,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, 109};

        byte[] yyy = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 35, -117, 8, 81, 121, -58, -1, 0, -71, 6, 1, -110, 4, 1, -110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, 109};

        System.out.println("Imsi: " + getImsi(yyy));

        int[] arr1 = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 35, -117, 8, 81, 121, -58, -1, 0, -71, 6, 1, -110, 4, 1, -110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, 109};
        */

        /*
        // printing the array
        System.out.println("Printing 1st array:");
        for (int i = 0; i < arr1.length; i++)
        {
            System.out.println(arr1[i]);
        }

        // copying array arr1 to arr2 with range of index from 1 to 3
        int[] arr2 = Arrays.copyOfRange(arr1, 8, 15);

        // printing the array arr2
        System.out.println("Printing new array:");
        for (int i = 0; i < arr2.length; i++)
        {
            System.out.println(arr2[i]);
        }

        System.out.println(new String(arr2, 8, arr2.length, "ASCII"));
        */
    }

    private static Long getImsi(final byte[] eventData)
    {
        return new BigInteger(Arrays.copyOfRange(eventData, 8, 15)).longValue();
    }
}