package file;

import java.io.File;

public class renameFiles
{
    public static void main(String[] args)
    {
        String fileName = "9844a2c2-149a-4494-9500-4547d2fbd9dd.pdf";
        String ext = getExt(fileName);

        System.out.println("ext: " + ext);

        File file = new File("/app/servers/generated-reports/java.txt");
        File newFile = new File("/app/servers/generated-reports/java1.txt");

        if (file.renameTo(newFile))
        {
            System.out.println("File rename success");;
        }
        else
        {
            System.out.println("File rename failed");
        }
    }

    private static String getExt(String fileName)
    {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}