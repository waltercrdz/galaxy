package com.meli.api.galaxy.model;

import java.util.List;

public class Galaxy {
	
	private List<Planet> planets;
	private Integer day;
	
	public Galaxy() {}

	public Galaxy(Builder builder) {
		this.planets = builder.planets;
		this.day = 0;
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public Integer getDay() {
		return day;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((planets == null) ? 0 : planets.hashCode());
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
		Galaxy other = (Galaxy) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (planets == null) {
			if (other.planets != null)
				return false;
		} else if (!planets.equals(other.planets))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Galaxy [planets=" + planets + ", day=" + day + "]";
	}

	public static class Builder {
		
		private List<Planet> planets;
		
		public Builder planets(List<Planet> planets) {
			this.planets = planets;
			return this;
		}
		
		public Galaxy build() {
			return new Galaxy(this);
		}
	}
}
