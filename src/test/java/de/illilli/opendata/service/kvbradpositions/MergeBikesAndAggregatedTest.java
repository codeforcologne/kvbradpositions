package de.illilli.opendata.service.kvbradpositions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MergeBikesAndAggregatedTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetCountdata() {
		List<BikeAndAggregatedBo> boList = new MergeBikesAndAggregated(MergeBikesAndAggregatedTest.getbikeList(),
				MergeBikesAndAggregatedTest.getAggregatedList()).getData();
		BikeAndAggregatedBo bikeAndAggregated = boList.get(0);
		String expected = "0";
		String actual = bikeAndAggregated.getCount();
		Assert.assertEquals(expected, actual);

		bikeAndAggregated = boList.get(1);
		expected = "1";
		actual = bikeAndAggregated.getCount();
		Assert.assertEquals(expected, actual);

		bikeAndAggregated = boList.get(2);
		expected = "2";
		actual = bikeAndAggregated.getCount();
		Assert.assertEquals(expected, actual);
	}

	static List<BikeBo> getbikeList() {
		List<BikeBo> bikeList = new ArrayList<>();
		BikeBo bo = new BikeBo();
		bo.setNumber(0);
		bikeList.add(bo);
		bo = new BikeBo();
		bo.setNumber(1);
		bikeList.add(bo);
		bo = new BikeBo();
		bo.setNumber(2);
		bikeList.add(bo);
		return bikeList;
	}

	static List<AggregatedBo> getAggregatedList() {
		List<AggregatedBo> aggregatedList = new ArrayList<>();
		AggregatedBo bo = new AggregatedBo(0);
		bo.setCountdata(0);
		aggregatedList.add(bo);
		bo = new AggregatedBo(1);
		bo.setCountdata(1);
		aggregatedList.add(bo);
		bo = new AggregatedBo(2);
		bo.setCountdata(2);
		aggregatedList.add(bo);
		return aggregatedList;
	}

}
