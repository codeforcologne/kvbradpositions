package de.illilli.opendata.service.kvbradpositions;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.opendata.service.Facade;

public class LastPositionsFacade implements Facade {

	private List<BikeBo> bikeList;

	public LastPositionsFacade(AskFor<List<BikeBo>> askForAllBikeslatestpositions) {
		this.bikeList = askForAllBikeslatestpositions.getData();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		String json = new Gson().toJson(bikeList);
		return json;
	}

}
