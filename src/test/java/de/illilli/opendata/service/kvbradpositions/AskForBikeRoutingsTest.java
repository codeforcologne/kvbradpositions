package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.util.List;

import org.junit.Before;

import de.illilli.opendata.service.AskFor;

/**
 * There is not test at the moment. It's just the way to proof what the class is
 * doing manually.
 * 
 * @author wolfram
 *
 */
public class AskForBikeRoutingsTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException {
		AskFor<List<RoutingBo>> askFor = new AskForBikeRoutings(21004);
		List<RoutingBo> routingBoList = askFor.getData();
		System.out.println(routingBoList);
	}

}
