package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Before;

import de.illilli.opendata.service.Facade;

public class LastPositionsFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		Facade facade = new LastPositionsFacade(new AskForAllbikeslatestposition(), new AskForAddress());
		System.out.println(facade.getJson());
	}

}
