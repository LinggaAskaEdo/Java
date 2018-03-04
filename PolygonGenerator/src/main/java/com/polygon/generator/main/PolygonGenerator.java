package com.polygon.generator.main;

import com.polygon.generator.util.ShapePolygonUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.List;

public class PolygonGenerator
{
    public static void main(String[] args)
    {
        /*Integer intLat = 182036520;
        Integer intLong = 94394880;

        double latitude = GeofenceUtil.millisToDegree(intLat);
        double longitude = GeofenceUtil.millisToDegree(intLong);*/

        double latitude = 40.87668000;
        double longitude = 21.96218700;

        System.out.println("latitude: " + latitude + ", longitude: " + longitude + "\n");

        Point2D.Double center = new Point2D.Double(longitude, latitude);

        ShapePolygonUtil util = new ShapePolygonUtil();
        List<Point2D.Double> polygonPoints = util.getFullEllipse(center, ShapePolygonUtil.DEFAULT_AZIMUTH, ShapePolygonUtil.DEFAULT_RADIUS, ShapePolygonUtil.DEFAULT_BEAM_WIDTH, ShapePolygonUtil.DEFAULT_STEP_COUNT);

        int npoints = polygonPoints.size();
        Double xpoints[] = new Double[npoints];
        Double ypoints[] = new Double[npoints];

        for (int i = 0; i < npoints; i++)
        {
            xpoints[i] = polygonPoints.get(i).getX();
            ypoints[i] = polygonPoints.get(i).getY();

            System.out.println("X: " + xpoints[i]);
            System.out.println("Y: " + ypoints[i]);
            //System.out.println("polygonPoints[" + i +"]: " + polygonPoints.get(i));
        }

        /*Path2D path = new Path2D.Double();
        path.moveTo(xpoints[0], ypoints[0]);

        for (int i = 1; i < xpoints.length; ++i)
        {
            path.lineTo(xpoints[i], ypoints[i]);
        }

        path.closePath();*/

        /*JFrame frame = new JFrame();
        frame.getContentPane().add(new PolygonGenerator());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);*/
    }
}