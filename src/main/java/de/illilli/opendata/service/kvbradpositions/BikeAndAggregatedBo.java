package de.illilli.opendata.service.kvbradpositions;

/**
 * This class extends BikeBo to enable merged data with BikeBo and AggregatedBo.
 * 
 * @see BikeBo
 * @see AggregatedBo
 *
 */
public class BikeAndAggregatedBo extends BikeBo {

	private long timeinmmillis;
	private double distance;
	private int countdata;

	public long getTimeinmmillis() {
		return timeinmmillis;
	}

	public void setTimeinmmillis(long timeinmmillis) {
		this.timeinmmillis = timeinmmillis;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getCountdata() {
		return countdata;
	}

	public void setCountdata(int countdata) {
		this.countdata = countdata;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + countdata;
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (timeinmmillis ^ (timeinmmillis >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BikeAndAggregatedBo other = (BikeAndAggregatedBo) obj;
		if (countdata != other.countdata)
			return false;
		if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
			return false;
		if (timeinmmillis != other.timeinmmillis)
			return false;
		return true;
	}

}
