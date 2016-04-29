package de.illilli.opendata.service.kvbradpositions;

import java.util.ArrayList;
import java.util.List;

public class MergeBikesAndAggregated {

	private List<BikeAndAggregatedBo> bikeAndAggregatedList = new ArrayList<BikeAndAggregatedBo>();

	public MergeBikesAndAggregated(final List<BikeBo> bikeList, final List<AggregatedBo> aggregatedList) {

		for (BikeBo bike : bikeList) {
			BikeAndAggregatedBo bo = new BikeAndAggregatedBo();
			bo.setBike(bike.getBike());
			bo.setLat(bike.getLat());
			bo.setLng(bike.getLng());
			bo.setName(bike.getName());
			bo.setNumber(bike.getNumber());
			bo.setTimestamp(bike.getTimestamp());
			bo.setUid(bike.getUid());

			int index = aggregatedList.indexOf(new AggregatedBo(bike.getNumber()));
			if (index > -1) {
				AggregatedBo aggregated = aggregatedList.get(index);
				bo.setCountdata(aggregated.getCountdata());
				bo.setDistance(aggregated.getDistance());
				bo.setTimeinmmillis(aggregated.getTimeinmillis());
			}

			bikeAndAggregatedList.add(bo);
		}
	}

	public List<BikeAndAggregatedBo> getData() {
		return bikeAndAggregatedList;
	}

}
