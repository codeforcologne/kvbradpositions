package de.illilli.opendata.service.kvbradpositions;

import java.util.List;
import java.util.Map;

import org.junit.Before;

import de.illilli.opendata.service.AskFor;

public class AskForAllbikesTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) {
		AskFor<Map<Integer, List<BikeBo>>> askFor = new AskForAllbikes();
		Map<Integer, List<BikeBo>> bikes = askFor.getData();

	}

}
