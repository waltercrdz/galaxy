package com.meli.api.galaxy.utils;

import com.meli.api.galaxy.model.Coordinates;

public class PlanetUtils {

	public static Double getXCoordinates(Integer angle, Integer distanceFromSun) {
		return Math.cos(angleToRadians(angle)) * distanceFromSun;
	}

	public static Double getYCoordinates(Integer angle, Integer distanceFromSun) {
		return Math.sin(angleToRadians(angle)) * distanceFromSun;
	}

	public static Integer getAngleMinor360(Integer angle) {
		if (angle <= 360)
			return angle;

		Integer laps = angle / 360;
		return angle - (360 * laps);
	}

	public static boolean areAligned(Integer angle1, Integer angle2) {
		if (angle1.equals(angle2))
			return true;

		Integer opposite = angle2 + 180;
		opposite = (opposite >= 360) ? opposite - 360 : opposite;

		return angle1.equals(opposite);
	}

	public static boolean isPointInTriangle(Coordinates p1, Coordinates p2, Coordinates p3, Coordinates p4) {
		if (getOrientation(p1, p2, p3) >= 0)
			return (getOrientation(p1, p2, p4) >= 0) && (getOrientation(p2, p3, p4) >= 0)
					&& (getOrientation(p3, p1, p4) >= 0);
		else
			return (getOrientation(p1, p2, p4) < 0) && (getOrientation(p2, p3, p4) < 0)
					&& (getOrientation(p3, p1, p4) < 0);
	}

	public static Double perimeter(Coordinates p0, Coordinates p1, Coordinates p2) {
		Double perimeter = 0.0;
		perimeter += distance(p0, p1);
		perimeter += distance(p0, p2);
		perimeter += distance(p1, p2);
		
		return perimeter;
	}

	public static Double distance(Coordinates pFrom, Coordinates pTo) {
		return Math.sqrt(Math.pow(pTo.getXpos() - pFrom.getXpos(), 2) + Math.pow(pTo.getYpos() - pFrom.getYpos(), 2));
	}

	private static Double getOrientation(Coordinates p1, Coordinates p2, Coordinates p3) {
		return (p1.getXpos() - p3.getXpos()) * (p2.getYpos() - p3.getYpos())
				- (p1.getYpos() - p3.getYpos()) * (p2.getXpos() - p3.getXpos());
	}

	private static Double angleToRadians(Integer angle) {
		return angle * (Math.PI / 180);
	}
}
