package de.illilli.opendata.service.kvbradpositions;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Facade;

public class LastPositionsDataTablesFacade implements Facade {

	private List<BikeBo> bikeList;
	private List<AggregatedBo> aggregatedList;
	private List<BikeAndAggregatedBo> bikeAndAggregatedList;

	public LastPositionsDataTablesFacade(AskFor<List<BikeBo>> askForAllBikeslatestpositions,
			AskFor<List<AggregatedBo>> askForAggregated) {
		this.bikeList = askForAllBikeslatestpositions.getData();
		this.aggregatedList = askForAggregated.getData();
		this.bikeAndAggregatedList = new MergeBikesAndAggregated(this.bikeList, this.aggregatedList).getData();
	}

	public LastPositionsDataTablesFacade(List<BikeBo> bikeList, List<AggregatedBo> aggregatedList) {
		this.bikeList = bikeList;
		this.aggregatedList = aggregatedList;
		this.bikeAndAggregatedList = new MergeBikesAndAggregated(this.bikeList, this.aggregatedList).getData();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		String json = "{\"data\":" + new Gson().toJson(bikeAndAggregatedList) + "}";
		return json;
	}

}
