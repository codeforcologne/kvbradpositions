package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.opendata.service.Facade;

/**
 * This Facade returns GeoJson LineString for a List of
 *
 */
public class BikesDataTablesFacade implements Facade {

	private List<BikeBo> bikeList;

	public BikesDataTablesFacade(List<BikeBo> bikeList) throws SQLException, NamingException, IOException {
		this.bikeList = bikeList;
	}

	public BikesDataTablesFacade(AskFor<List<BikeBo>> askFor) {
		this.bikeList = askFor.getData();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		String json = "{\"data\":" + new Gson().toJson(bikeList) + "}";
		return json;
	}

}
