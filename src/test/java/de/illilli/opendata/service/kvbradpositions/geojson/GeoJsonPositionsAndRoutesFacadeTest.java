package de.illilli.opendata.service.kvbradpositions.geojson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.junit.Before;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradpositions.AskForBikeRoutings;
import de.illilli.opendata.service.kvbradpositions.AskForBikesList;
import de.illilli.opendata.service.kvbradpositions.BikeBo;
import de.illilli.opendata.service.kvbradpositions.RoutingBo;

public class GeoJsonPositionsAndRoutesFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		int number = 21004;
		List<BikeBo> bikeList = new AskForBikesList(number).getData();
		List<RoutingBo> routingList = new AskForBikeRoutings(number).getData();
		Facade facade = new GeoJsonPositionsAndRoutesFacade(bikeList, routingList);
		String json = facade.getJson();
		System.out.println(json);
	}

}
