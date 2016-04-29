package de.illilli.opendata.service.kvbradpositions;

import java.util.ArrayList;
import java.util.List;

public class MergeBikesAndAggregated {

	/**
	 * <p>
	 * N/A: not available
	 * </p>
	 * <p>
	 * <a href="https://de.wikipedia.org/wiki/N/A">Wikipedia: N/A</a>
	 */
	public static final String NA = "N/A";

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

				bo.setCount("" + aggregated.getCountdata());
				String distance = "" + aggregated.getDistance();
				bo.setDistance(distance);

				String time = "" + aggregated.getTimeinmillis();
				bo.setTime(time);
			} else {
				bo.setCount(NA);
				bo.setDistance(NA);
				bo.setTime(NA);
			}

			bikeAndAggregatedList.add(bo);
		}
	}

	public List<BikeAndAggregatedBo> getData() {
		return bikeAndAggregatedList;
	}

}
