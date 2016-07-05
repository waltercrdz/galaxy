package com.meli.api.galaxy.model;

public class StatusDay {

	private Integer day;
	private Weather weather;

	public StatusDay() {}
	
	private StatusDay(Builder builder) {
		this.day = builder.day;
		this.weather = builder.weather;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public static class Builder {

		private Integer day;
		private Weather weather;

		public Builder day(Integer day) {
			this.day = day;
			return this;
		}
		
		public Builder weather(Weather weather) {
			this.weather = weather;
			return this;
		}

		public StatusDay build() {
			return new StatusDay(this);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((weather == null) ? 0 : weather.hashCode());
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
		StatusDay other = (StatusDay) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (weather != other.weather)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatusDay [day=" + day + ", weather=" + weather + "]";
	}
}
