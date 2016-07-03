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
	
	private static Double angleToRadians(Integer angle) {
		return angle * (Math.PI / 180);
	}
}
