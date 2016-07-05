package com.meli.api.galaxy.utils;

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
	
	public Double getOrientation(Double x1, Double y1, Double x2, Double y2, Double x3, Double y3){
    	return (x1 - x3)*(y2 - y3)-(y1 - y3)*(x2 - x3);
    }
	
	private static Double angleToRadians(Integer angle) {
		return angle * (Math.PI / 180);
	}
}
