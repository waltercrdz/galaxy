package com.meli.api.galaxy.model;

import com.meli.api.galaxy.utils.PlanetUtils;

public class Planet {

	private String name;
	private Integer distanceFromSun;
	private Integer angle;
	private Integer angularRate;
	private Coordinates coordinate;

	// TODO strategy
	private boolean clockwise;

	public Planet() {
	}

	private Planet(Builder builder) {
		this.name = builder.name;
		this.distanceFromSun = builder.distanceFromSun;
		this.angle = builder.angle;
		this.angularRate = builder.angularRate;
		this.clockwise = builder.clockwise;
		this.coordinate = builder.coordinate;
	}

	public void move(Integer day) {
		if (!this.clockwise) {
			this.angle = PlanetUtils.getAngleMinor360(day * this.angularRate);
		} else {
			this.angle = 360 - PlanetUtils.getAngleMinor360(day * this.angularRate);
		}

		this.coordinate = new Coordinates.Builder()
				.xpos(PlanetUtils.getXCoordinates(this.angle, this.distanceFromSun))
				.ypos(PlanetUtils.getYCoordinates(this.angle, this.distanceFromSun))
				.build();
	}

	public String getName() {
		return name;
	}

	public Integer getDistanceFromSun() {
		return distanceFromSun;
	}

	public Integer getAngularRate() {
		return angularRate;
	}

	public boolean isClockwise() {
		return clockwise;
	}

	public Integer getAngle() {
		return angle;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDistanceFromSun(Integer distanceFromSun) {
		this.distanceFromSun = distanceFromSun;
	}

	public void setAngle(Integer angle) {
		this.angle = angle;
	}

	public void setAngularRate(Integer angularRate) {
		this.angularRate = angularRate;
	}

	public void setClockwise(boolean clockwise) {
		this.clockwise = clockwise;
	}

	public Coordinates getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinates coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((angle == null) ? 0 : angle.hashCode());
		result = prime * result + ((angularRate == null) ? 0 : angularRate.hashCode());
		result = prime * result + (clockwise ? 1231 : 1237);
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + ((distanceFromSun == null) ? 0 : distanceFromSun.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (angle == null) {
			if (other.angle != null)
				return false;
		} else if (!angle.equals(other.angle))
			return false;
		if (angularRate == null) {
			if (other.angularRate != null)
				return false;
		} else if (!angularRate.equals(other.angularRate))
			return false;
		if (clockwise != other.clockwise)
			return false;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (distanceFromSun == null) {
			if (other.distanceFromSun != null)
				return false;
		} else if (!distanceFromSun.equals(other.distanceFromSun))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Planet [name=" + name + ", distanceFromSun=" + distanceFromSun + ", angle=" + angle + ", angularRate="
				+ angularRate + ", coordinate=" + coordinate + ", clockwise=" + clockwise + "]";
	}

	public static class Builder {
		private String name;
		private Integer distanceFromSun;
		private Integer angle;
		private Integer angularRate;
		private boolean clockwise;
		private Coordinates coordinate;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder distanceFromSun(Integer distanceFromSun) {
			this.distanceFromSun = distanceFromSun;
			return this;
		}

		public Builder angle(Integer angle) {
			this.angle = angle;
			return this;
		}

		public Builder angularRate(Integer angularRate) {
			this.angularRate = angularRate;
			return this;
		}

		public Builder clockwise(boolean clockwise) {
			this.clockwise = clockwise;
			return this;
		}

		public Builder coordinate(Coordinates coordinate) {
			this.coordinate = coordinate;
			return this;
		}

		public Planet build() {
			return new Planet(this);
		}
	}
}