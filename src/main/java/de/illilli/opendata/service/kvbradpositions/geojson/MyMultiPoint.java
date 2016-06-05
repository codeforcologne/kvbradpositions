package de.illilli.opendata.service.kvbradpositions.geojson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.geojson.Feature;
import org.geojson.LngLatAlt;
import org.geojson.Point;

import de.illilli.opendata.service.kvbradpositions.BikeBo;

public class MyMultiPoint {

	private static final Logger logger = Logger.getLogger(MyMultiPoint.class);

	private String name;
	final static String DATE_FORMAT = "dd.MM hh:mm";
	private List<Feature> featureList = new ArrayList<>();

	public MyMultiPoint(Map<Integer, List<BikeBo>> bikeMap) {

		// iterate for every bike
		for (Integer key : bikeMap.keySet()) {
			logger.info("multipoint for '" + key + "'");
			List<BikeBo> bikeList = bikeMap.get(key);

			// iterate for every bike in list
			for (BikeBo bikeBo : bikeList) {
				Feature feature = new Feature();
				feature.setId(Integer.toString(key));

				// get coordinates and add to MulitPoint
				Point point = new Point();
				LngLatAlt geometry = new LngLatAlt(bikeBo.getLng(), bikeBo.getLat());
				point.setCoordinates(geometry);

				// set properties
				Map<String, Object> properties = new Hashtable<String, Object>();
				this.name = bikeBo.getName();
				properties.put("name", this.name);
				properties.put("number", bikeBo.getNumber());
				properties.put("uid", bikeBo.getUid());
				properties.put("bike", bikeBo.getBike());
				String timestamp = new SimpleDateFormat(DATE_FORMAT).format(bikeBo.getTimestamp());
				properties.put("timestamp", timestamp);

				feature.setProperties(properties);
				feature.setGeometry(point);

				featureList.add(feature);
			}
		}

	}

	public List<Feature> getFeatureList() {
		return featureList;
	}

	public String getName() {
		return name;
	}

}
