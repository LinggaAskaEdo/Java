package com.polygon.generator.util;

public class GeofenceUtil
{
    public static double millisToDegree(int coord)
    {
        double result = Math.abs(coord / 1000.0D / 3600.0D);

        if (coord < 0)
        {
            result *= -1.0D;
        }

        return result;
    }
}