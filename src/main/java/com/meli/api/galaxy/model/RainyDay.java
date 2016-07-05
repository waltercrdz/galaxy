package com.meli.api.galaxy.model;

public class RainyDay {
	
	private Integer day;
	private Double perimeter;
	
	public RainyDay() {
	}
	
	private RainyDay(Builder builder) {
		this.day = builder.day;
		this.perimeter = builder.perimeter;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Double getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(Double perimeter) {
		this.perimeter = perimeter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((perimeter == null) ? 0 : perimeter.hashCode());
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
		RainyDay other = (RainyDay) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (perimeter == null) {
			if (other.perimeter != null)
				return false;
		} else if (!perimeter.equals(other.perimeter))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RainyDay [day=" + day + ", perimeter=" + perimeter + "]";
	}

	public static class Builder {

		private Integer day;
		private Double perimeter;

		public Integer getDay() {
			return day;
		}

		public Builder day(Integer day) {
			this.day = day;
			return this;
		}
		
		public Builder perimeter(Double perimeter) {
			this.perimeter = perimeter;
			return this;
		}

		public RainyDay build() {
			return new RainyDay(this);
		}
	}
}
