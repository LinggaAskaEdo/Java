package com.polygon.generator.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ShapePolygonUtil
{
	public static final double DEFAULT_BEAM_WIDTH = 60;
	public static final double DEFAULT_AZIMUTH = 0.0D;
	public static final double DEFAULT_RADIUS = 600;
	public static final int DEFAULT_STEP_COUNT = 20;

	public List<Point2D.Double> getFullEllipse (Point2D.Double center, double azimuth, double radius, double beamWidth, int steps)
	{
		return closePolygonPath(getEllipseOutline(center, azimuth, radius, beamWidth, steps));
	}

	private List<Point2D.Double> getEllipseOutline(Point2D.Double center, double azimuth, double radius, double beamWidth, int steps)
	{
		List<Point2D.Double> polygon = null;

		if (steps > 2)
		{
			double radiusDistance;

			if (beamWidth >= 90 || beamWidth == 0)
			{
				beamWidth = DEFAULT_BEAM_WIDTH;
			}

			radiusDistance = Math.tan(Math.toRadians(beamWidth)) * radius;

			polygon = getArcLine(center, azimuth, radiusDistance, radius, (double) 0, (double) 360, steps);
		}
		else
		{
			System.out.println("Unable to draw");
		}

		return polygon;
	}

	private List<Point2D.Double> getArcLine(Point2D.Double center, double azimuth, double width, double height, double startAngle, double stopAngle, int steps)
	{
		double correctionAngle = calculateArcAngleCorrection(startAngle, stopAngle, azimuth);
		double angle;
		List<Point2D.Double> polygon = new ArrayList<>();

		for (int i = 0; i < steps; i++)
		{
			if (steps > 1)
			{
				angle = startAngle + (stopAngle - startAngle) * i / (steps -1);
			}
			else
			{
				angle = startAngle;
			}

			double angleRad = Math.toRadians(angle);

			double cosAngle = Math.cos(angleRad);
			double sinAngle = Math.sin(angleRad);

			double widthTemp = cosAngle * width;
			double heigthTemp = sinAngle * height;

			double distance = Math.sqrt(widthTemp * widthTemp + heigthTemp * heigthTemp);
			double angle2 = Math.atan2(heigthTemp, widthTemp);

			polygon.add(getMeterOffset(center, distance, angle2 + correctionAngle));
		}

		return polygon;
	}

	private double calculateArcAngleCorrection(double startAngle, double stopAngle, double azimuth)
	{
		return Math.toRadians(azimuth - ((stopAngle - startAngle) / ((stopAngle - startAngle) /90D)));
	}

	private Point2D.Double getMeterOffset(Point2D.Double point, double distance, double angleRad)
	{
		double WGS84_POLAR_RADIUS = 6378137.0;
		double WGS84_EQUATORIAL_RADIUS = 6356752.3142;
		double dRP = distance/ WGS84_POLAR_RADIUS;
		double dRE = distance/ WGS84_EQUATORIAL_RADIUS;
		double lat = Math.toRadians(point.getY());
		double lon = Math.toRadians(point.getX());
		double newLat = Math.asin(Math.sin(lat)*Math.cos(dRP) + Math.cos(lat)*Math.sin(dRE)*Math.cos(angleRad));
		double newLon = lon + Math.atan2(Math.sin(angleRad)*Math.sin(dRE)*Math.cos(lat), Math.cos(dRP)-Math.sin(lat)*Math.sin(newLat));
		int COORDINATE_DECIMAL_DIGIT = 8;

		return new Point2D.Double(NumberUtil.getTrimmedDecDigitDouble(Math.toDegrees(newLon), COORDINATE_DECIMAL_DIGIT), NumberUtil.getTrimmedDecDigitDouble(Math.toDegrees(newLat), COORDINATE_DECIMAL_DIGIT));
	}

	private List<Point2D.Double> closePolygonPath (List<Point2D.Double> polygonPoints)
	{
		if (polygonPoints != null && polygonPoints.size() > 2)
		{
			Point2D.Double firstPoint = polygonPoints.get(0);
			Point2D.Double lastPoint = polygonPoints.get(polygonPoints.size()-1);
			
			if (lastPoint.getX() != firstPoint.getX() || lastPoint.getY() != firstPoint.getY())
			{
				polygonPoints.add(firstPoint);
			}
		}
		
		return polygonPoints;
	}
}