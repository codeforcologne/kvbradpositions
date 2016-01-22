package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Before;

public class AskForAddressTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		AskForGeo<AddressBo> askFor = new AskForAddress();
		AddressBo addressBo = askFor.getData(50.9202716, 6.93347807625939);
		System.out.println(addressBo);
	}

}
