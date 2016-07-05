package com.meli.api.galaxy.model;

import java.util.Map;

import org.springframework.data.annotation.Id;

import com.google.common.collect.Maps;

public class Galaxy {
	
	@Id
	private String id;
	private Map<String, Planet> planets;
	
	private Integer rainyDays;
	private Integer droughtDays;
	private Integer optimalDays;
	private Map<Integer, StatusDay> days;
	private RainyDay maxRainyDay;
	
	public Galaxy() {}

	public Galaxy(Builder builder) {
		this.planets = builder.planets;
		this.rainyDays = builder.rainyDays;
		this.droughtDays = builder.droughtDays;
		this.optimalDays = builder.optimalDays;
		this.days = builder.days;
		this.maxRainyDay = builder.maxRainyDay;
	}
	
	public void movePlanets(Integer day) {
		this.planets.forEach((key, planet) -> planet.move(day));
	}

	public Map<String, Planet> getPlanets() {
		return Maps.newHashMap(planets);
	}

	public void setPlanets(Map<String, Planet> planets) {
		this.planets = planets;
	}

	public Integer getRainyDays() {
		return rainyDays;
	}

	public void setRainyDays(Integer rainyDays) {
		this.rainyDays = rainyDays;
	}

	public Integer getDroughtDays() {
		return droughtDays;
	}

	public void setDroughtDays(Integer droughtDays) {
		this.droughtDays = droughtDays;
	}

	public Integer getOptimalDays() {
		return optimalDays;
	}

	public RainyDay getMaxRainyDay() {
		return maxRainyDay;
	}

	public void setMaxRainyDay(RainyDay maxRainyDay) {
		this.maxRainyDay = maxRainyDay;
	}

	public void setOptimalDays(Integer optimalDays) {
		this.optimalDays = optimalDays;
	}

	public Map<Integer, StatusDay> getDays() {
		return days;
	}

	public void setDays(Map<Integer, StatusDay> days) {
		this.days = days;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((days == null) ? 0 : days.hashCode());
		result = prime * result + ((droughtDays == null) ? 0 : droughtDays.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maxRainyDay == null) ? 0 : maxRainyDay.hashCode());
		result = prime * result + ((optimalDays == null) ? 0 : optimalDays.hashCode());
		result = prime * result + ((planets == null) ? 0 : planets.hashCode());
		result = prime * result + ((rainyDays == null) ? 0 : rainyDays.hashCode());
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
		if (days == null) {
			if (other.days != null)
				return false;
		} else if (!days.equals(other.days))
			return false;
		if (droughtDays == null) {
			if (other.droughtDays != null)
				return false;
		} else if (!droughtDays.equals(other.droughtDays))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maxRainyDay == null) {
			if (other.maxRainyDay != null)
				return false;
		} else if (!maxRainyDay.equals(other.maxRainyDay))
			return false;
		if (optimalDays == null) {
			if (other.optimalDays != null)
				return false;
		} else if (!optimalDays.equals(other.optimalDays))
			return false;
		if (planets == null) {
			if (other.planets != null)
				return false;
		} else if (!planets.equals(other.planets))
			return false;
		if (rainyDays == null) {
			if (other.rainyDays != null)
				return false;
		} else if (!rainyDays.equals(other.rainyDays))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Galaxy [id=" + id + ", planets=" + planets + ", rainyDays=" + rainyDays + ", droughtDays=" + droughtDays
				+ ", optimalDays=" + optimalDays + ", days=" + days + ", maxRainyDay=" + maxRainyDay + "]";
	}

	public static class Builder {
		
		private Map<String, Planet> planets;
		private Integer rainyDays;
		private Integer droughtDays;
		private Integer optimalDays;
		private Map<Integer, StatusDay> days;
		private RainyDay maxRainyDay;
		
		public Builder planets(Map<String, Planet> planets) {
			this.planets = planets;
			return this;
		}
		
		public Builder rainyDays(Integer rainyDays) {
			this.rainyDays = rainyDays;
			return this;
		}
		
		public Builder droughtDays(Integer droughtDays) {
			this.droughtDays = droughtDays;
			return this;
		}
		
		public Builder optimalDays(Integer optimalDays) {
			this.optimalDays = optimalDays;
			return this;
		}
		
		public Builder days(Map<Integer, StatusDay> days) {
			this.days = days;
			return this;
		}
		
		public Builder maxRainyDay(RainyDay maxRainyDay) {
			this.maxRainyDay = maxRainyDay;
			return this;
		}
		
		public Galaxy build() {
			return new Galaxy(this);
		}
	}
}
