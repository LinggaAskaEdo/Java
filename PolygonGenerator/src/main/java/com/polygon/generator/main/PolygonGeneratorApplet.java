package com.polygon.generator.main;

import com.polygon.generator.util.ShapePolygonUtil;

import java.applet.Applet;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class PolygonGeneratorApplet extends Applet
{
    public void paint(Graphics g)
    {
        double latitude = 40.87668000;
        double longitude = 21.96218700;

        System.out.println("latitude: " + latitude + ", longitude: " + longitude + "\n");

        Point2D.Double center = new Point2D.Double(longitude, latitude);

        ShapePolygonUtil util = new ShapePolygonUtil();
        List<Point2D.Double> polygonPoints = util.getFullEllipse(center, ShapePolygonUtil.DEFAULT_AZIMUTH, ShapePolygonUtil.DEFAULT_RADIUS, ShapePolygonUtil.DEFAULT_BEAM_WIDTH, ShapePolygonUtil.DEFAULT_STEP_COUNT);

        int numPoints = polygonPoints.size();
        int xPoints[] = new int[numPoints];
        int yPoints[] = new int[numPoints];

        for (int i = 0; i < numPoints; i++)
        {
            xPoints[i] = (int) polygonPoints.get(i).getX();
            yPoints[i] = (int) polygonPoints.get(i).getY();

            //System.out.println("X: " + xPoints[i]);
            //System.out.println("Y: " + yPoints[i]);
            System.out.println(polygonPoints.get(i));
            //System.out.println("polygonPoints[" + i +"]: " + polygonPoints.get(i));
        }

        // Set the drawing color to RED
        g.setColor(Color.RED);

        // Draw a filled in polygon
        g.fillPolygon(xPoints, yPoints, numPoints);
    }
}