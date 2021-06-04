package qr;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

import static java.lang.System.out;

public class ReadQR
{
    private static final String BASE_PATH = "/Users/lingga.putra/Downloads/QrisGenerator/docs";

    public static void main(String[] args)
    {
        File dir = new File(BASE_PATH);
        File [] files = dir.listFiles((dir1, name) -> name.endsWith(".jpg"));

        for (File qrFile : Objects.requireNonNull(files))
        {
            out.println(qrFile);
            out.println("Data: " + readQRCode(qrFile));
        }
    }

    private static String readQRCode(File filePath)
    {
        String result = "";
        try
        {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
            Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
            result = qrCodeResult.getText();
        }
        catch (Exception e)
        {
            out.println(e.getMessage());
        }

        return result;
    }
}