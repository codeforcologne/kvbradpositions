package de.illilli.opendata.service.kvbradpositions;

import java.util.concurrent.TimeUnit;

/**
 * This class calculates the days gone between to timestamps.
 */
public class Unused {

	long days;

	/**
	 * since set with System.currentTimeMillis();
	 * 
	 * @param time
	 *            Timestamp in millis to compare
	 */
	public Unused(long time) {
		this(time, System.currentTimeMillis());
	}

	/**
	 * Since set with parameter.
	 * 
	 * @param time
	 *            Timestamp in millis
	 * @param since
	 *            Timestamp in millis
	 */
	public Unused(long time, long since) {
		long lasted = since - time;

		days = TimeUnit.MILLISECONDS.toDays(lasted);
	}

	/**
	 * if the compared timestamp is before, it returns a negative value.
	 * 
	 * @return
	 */
	public long getDays() {
		return days * -1;
	}
}
