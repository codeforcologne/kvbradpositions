package de.illilli.opendata.service.kvbradpositions;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Facade;

/**
 * This Facade returns GeoJson LineString for a List of
 *
 */
public class GeoJsonLineStringFacade implements Facade {

	private FeatureCollection featureCollection;
	Map<Integer, List<BikeBo>> bikeMap;
	final static String DATE_FORMAT = "dd.MM.yyyy hh:mm";

	public GeoJsonLineStringFacade(List<BikeBo> bikeList) {
		Bikes bikes = new Bikes(bikeList);
		bikeMap = bikes.getBikeMap();
		setFeatureCollection();
	}

	public GeoJsonLineStringFacade(AskFor<Map<Integer, List<BikeBo>>> askFor) {
		bikeMap = askFor.getData();
		setFeatureCollection();
	}

	void setFeatureCollection() {
		featureCollection = new FeatureCollection();

		for (Integer key : bikeMap.keySet()) {
			List<BikeBo> bikeList = bikeMap.get(key);
			if (bikeList.size() > 1) {
				// nur bike mit mehr als zwei Positionen
				Feature feature = new Feature();
				feature.setId(Integer.toString(key));
				LineString lineString = new LineString();
				Map<String, Object> properties = new Hashtable<String, Object>();
				StringBuilder coordinates = new StringBuilder();
				StringBuilder timestamps = new StringBuilder();
				boolean firstRun = true;
				for (BikeBo bikeBo : bikeList) {
					LngLatAlt geometry = new LngLatAlt(bikeBo.lng, bikeBo.lat);
					lineString.add(geometry);
					feature.setGeometry(lineString);
					properties.put("name", bikeBo.getName());
					properties.put("number", bikeBo.getNumber());
					properties.put("uid", bikeBo.getUid());
					properties.put("bike", bikeBo.getBike());

					if (firstRun) {
						firstRun = false;
					} else {
						coordinates.append(",");
						timestamps.append(",");
					}
					coordinates.append("[");
					coordinates.append(bikeBo.lat);
					coordinates.append(",");
					coordinates.append(bikeBo.lng);
					coordinates.append("]");

					String timestamp = new SimpleDateFormat(DATE_FORMAT, Locale.GERMAN).format(bikeBo.getTimestamp());
					timestamps.append(timestamp);

				}
				properties.put("coordinates", coordinates.toString());
				properties.put("timestamps", timestamps);
				feature.setProperties(properties);
				featureCollection.add(feature);
			}
		}

	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
