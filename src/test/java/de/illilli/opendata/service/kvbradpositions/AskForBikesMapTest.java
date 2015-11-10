package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.junit.Before;

public class AskForBikesMapTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws MalformedURLException,
			IOException {
		Map<Integer, List<BikeBo>> bikesMap = new AskForBikesMap()
				.getBikesMap();

		System.out.println(bikesMap);
	}

}
