package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import de.illilli.opendata.service.Facade;

public class LastPositionsDataTablesFacadeTest {

	@Test
	public void testGetJson() throws IOException {
		Facade facade = new LastPositionsDataTablesFacade(MergeBikesAndAggregatedTest.getbikeList(),
				MergeBikesAndAggregatedTest.getAggregatedList());
		InputStream inputStream = this.getClass().getResourceAsStream("/bikeAndAggregated012.json");
		String expected = IOUtils.toString(inputStream);
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

}
