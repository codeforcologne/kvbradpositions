package de.illilli.opendata.service.kvbradpositions;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

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
				NumberFormat formatter = new DecimalFormat("#0.000");
				String distance = formatter
						.format(new BigDecimal(aggregated.getDistance()).divide(new BigDecimal(1000)));
				bo.setDistance(distance);

				String time = DateFormatUtils.format(aggregated.getTimeinmillis(), "HH:mm");
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
