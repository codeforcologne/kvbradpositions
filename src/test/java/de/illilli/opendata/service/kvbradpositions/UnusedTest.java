package de.illilli.opendata.service.kvbradpositions;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;

public class UnusedTest {

	public static final String PATTERN = "dd.MM.yyyy hh:mm:ss";

	@Test
	public void testGet0Days() throws ParseException {
		long time = new SimpleDateFormat(PATTERN).parse("23.01.2016 12:00:00").getTime();
		long since = new SimpleDateFormat(PATTERN).parse("23.01.2016 12:00:00").getTime();
		Unused unused = new Unused(time, since);
		long expected = 0;
		long actual = unused.getDays();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGet1Day() throws ParseException {
		long time = new SimpleDateFormat(PATTERN).parse("23.01.2016  12:00:00").getTime();
		long since = new SimpleDateFormat(PATTERN).parse("24.01.2016  12:00:00").getTime();
		Unused unused = new Unused(time, since);
		long expected = -1;
		long actual = unused.getDays();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGet2Days() throws ParseException {
		long time = new SimpleDateFormat(PATTERN).parse("23.01.2016  12:00:00").getTime();
		long since = new SimpleDateFormat(PATTERN).parse("25.01.2016  01:00:00").getTime();
		Unused unused = new Unused(time, since);
		long expected = -2;
		long actual = unused.getDays();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetMinus2Days() throws ParseException {
		long time = new SimpleDateFormat(PATTERN).parse("25.01.2016  01:00:00").getTime();
		long since = new SimpleDateFormat(PATTERN).parse("23.01.2016  12:00:00").getTime();
		Unused unused = new Unused(time, since);
		long expected = 2;
		long actual = unused.getDays();
		Assert.assertEquals(expected, actual);
	}
}
