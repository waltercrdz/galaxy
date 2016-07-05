package com.meli.api.galaxy.model;

public class Coordinates {
	
	private Double xpos;
	private Double ypos;
	
	public Coordinates() {
	}
	
	private Coordinates(Builder builder) {
		this.xpos = builder.xpos;
		this.ypos = builder.ypos;
	}
	
	public Double getXpos() {
		return xpos;
	}

	public void setXpos(Double xpos) {
		this.xpos = xpos;
	}

	public Double getYpos() {
		return ypos;
	}

	public void setYpos(Double ypos) {
		this.ypos = ypos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((xpos == null) ? 0 : xpos.hashCode());
		result = prime * result + ((ypos == null) ? 0 : ypos.hashCode());
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
		Coordinates other = (Coordinates) obj;
		if (xpos == null) {
			if (other.xpos != null)
				return false;
		} else if (!xpos.equals(other.xpos))
			return false;
		if (ypos == null) {
			if (other.ypos != null)
				return false;
		} else if (!ypos.equals(other.ypos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordinate [xpos=" + xpos + ", ypos=" + ypos + "]";
	}

	public static class Builder {
		private Double xpos;
		private Double ypos;
	
		public Builder xpos(Double xpos) {
			this.xpos = xpos;
			return this;
		}
		
		public Builder ypos(Double ypos) {
			this.ypos = ypos;
			return this;
		}
		
		public Coordinates build() {
			return new Coordinates(this);
		}
	}
}
