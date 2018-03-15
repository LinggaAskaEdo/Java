/**
 * Created by Lingga on 11/07/17.
 */

public class Ampas
{
    public static void main(String[] args)
    {
        /*int xxx = Integer.parseInt("-123");
        System.out.println("xxx: " + xxx);*/

        /*Date tglSekarang = new Timestamp(Calendar.getInstance().getTimeInMillis());
        Date xxx = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date());

        System.out.println("Tanggal Sekarang: " + tglSekarang);
        System.out.println("Tanggal Sekarang: " + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(tglSekarang));
        System.out.println("Tanggal Sekarang: " + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()));*/

        /*String aaa = "0.0";
        double bbb = Double.parseDouble(aaa);

        if (bbb == 0.0)
            System.out.println("bbb: " + bbb);
        else
            System.out.println("not zero");*/

        System.out.println("a: " + Float.valueOf("0.00000000"));
        System.out.println("b:" + Float.valueOf("0.00269470"));

        if (Float.valueOf("0.00000000") == 0 && Float.valueOf("0.00269470") == 0)
        {
            System.out.println("Ada yang nol !!!");
        }
        else
        {
            System.out.println("Ga ada yang nol !!!");
        }

        System.out.println("latitude: " + parseStringToDouble("0.0"));
        System.out.println("longitude: " + parseStringToDouble("0.0"));

        double a = 0.00000000;
        double b = 0.00269470;

        if(a == 0 || b == 0)
        {
            System.out.println("Longitude or Latitude = 0.0 ");
        }

        System.out.println("a: " + millisToDegree(183978985));
    }

    private static Double parseStringToDouble(String string)
    {
        Double doubleVal = null;

        try
        {
            doubleVal = Double.valueOf(string);
        }
        catch (Exception e)
        {
            System.out.println("Exception parseStringToDouble: " + e.getMessage());
        }

        return doubleVal;
    }

    private static double millisToDegree(int coord)
    {
        double aReturn = Math.abs(coord / 1000D / 3600D);

        if (coord < 0)
        {
            aReturn *= -1;
        }

        return aReturn;
    }

    private static double calculateCenterPoint(int coor)
    {
        /*int radius = getRadius(cellDB);
        double distanceRad = radius / 6378100D;
        double startLatRad = Math.toRadians(millisToDegree(coor));
        double endLatRad = Math.asin(Math.sin(startLatRad) * Math.cos(distanceRad) + Math.cos(startLatRad) * Math.sin(distanceRad) * Math.cos(bearing));*/

        return 0;
    }
}