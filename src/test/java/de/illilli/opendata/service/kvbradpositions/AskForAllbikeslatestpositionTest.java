package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Before;

import de.illilli.opendata.service.AskFor;

public class AskForAllbikeslatestpositionTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		AskFor<List<BikeBo>> askfor = new AskForAllbikeslatestposition();
		List<BikeBo> bikeBoList = askfor.getData();
		System.out.println(bikeBoList);
	}

}
