import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ampas2
{
    public static void main(String[] args)
    {
        //jumlah(1, 3);

        /*Integer azimuth = null;

        if (azimuth == null)
        {
            System.out.println("null");
        }*/

        String path = "/home/lingga/Downloads/";
        String csvFileName = "send-location-result-" + "1111" + ".csv";
        CSVWriter writer = null;

        try
        {
            System.out.println("can write: {}" + new File(path).canWrite());
            System.out.println("can write: {}" + new File(path).canExecute());
            System.out.println("can write: {}" + new File(path).canRead());
            System.out.println("can write: {}" + new File(path).canWrite());

            writer = new CSVWriter(new FileWriter(path + csvFileName), ',');

            String[] header = new String[9];
            header = new String[]{"No", "Nomor Tujuan", "Pengirim", "Pesan", "Status Pengiriman", "Tanggal Dikirim", "Tanggal Terkirim", "Alasan", "\n"};

            String[] content = {"1", "aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg"};
            writer.writeNext(header);
            writer.writeNext(content);
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static boolean jumlah(int a, int b)
    {
        boolean status;

        int c = a + b;

        status = c > 0;

        return status;
    }
}