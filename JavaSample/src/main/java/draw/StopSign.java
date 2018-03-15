package draw;

import java.applet.Applet;
import java.awt.*;

public class StopSign extends Applet
{
    public void paint(Graphics g)
    {
        // Using the points (0,9), (9,0), (21,0), (30,9) ...
        // initialize the xPoints and yPoints array...
        int[] xPoints = {0, 9, 21, 30, 30, 21, 9, 0};
        int[] yPoints = {9, 0, 0,  9,  21, 30, 30, 21};
        int numPoints = 8;

        // Set the drawing color to RED
        g.setColor(Color.RED);

        // Draw a filled in polygon
        //Polygon polygon = new Polygon();

        //g.fillPolygon(xPoints, yPoints, numPoints);
        g.drawPolygon(xPoints, yPoints, numPoints);
    }
}