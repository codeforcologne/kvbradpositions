package de.illilli.opendata.service.kvbradpositions;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;
import org.geojson.MultiPoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Facade;

/**
 * This Facade returns GeoJson LineString for a List of
 *
 */
public class GeoJsonPositionsAndRoutesFacade implements Facade {

	private FeatureCollection featureCollection;
	private Map<Integer, List<BikeBo>> bikeMap;
	private List<RoutingBo> routingList;
	final static String DATE_FORMAT = "dd.MM.yyyy hh:mm";

	public GeoJsonPositionsAndRoutesFacade(List<BikeBo> bikeList, List<RoutingBo> routingList) {
		this.bikeMap = new Bikes(bikeList).getBikeMap();
		this.routingList = routingList;
		setFeatureCollection();
	}

	/**
	 * Constructor for testing purpose.
	 * 
	 * @param askFor
	 */
	public GeoJsonPositionsAndRoutesFacade(AskFor<Map<Integer, List<BikeBo>>> askFor) {
		bikeMap = askFor.getData();
		setFeatureCollection();
	}

	void setFeatureCollection() {
		featureCollection = new FeatureCollection();
		setMultiPoint();
		setMultiLineString();
	}

	void setMultiLineString() {
		for (RoutingBo routing : routingList) {
			Feature feature = new Feature();
			feature.setId(Integer.toString(routing.getNumber()));
			feature.setGeometry(getLineString(routing.getValue()));
			feature.setProperties(getLineStringProperties(routing));
			featureCollection.add(feature);
		}
	}

	Map<String, Object> getLineStringProperties(RoutingBo routing) {
		Map<String, Object> properties = new Hashtable<String, Object>();
		properties.put("timeinmillis", routing.getTimeinmillis());
		properties.put("distance", routing.getDistance());
		properties.put("modtime", routing.getModtime());
		properties.put("type", routing.getType());
		properties.put("typeString", routing.getTypeString());
		properties.put("srid", routing.getSrid());
		return properties;
	}

	LineString getLineString(String value) {
		LineString line = new LineString();
		List<LngLatAlt> coordinates = new ArrayList<>();
		value = value.substring(1, value.length() - 1);
		StringTokenizer st = new StringTokenizer(value, ",");
		while (st.hasMoreTokens()) {
			String coords = st.nextToken();
			double lng = Double.valueOf(coords.substring(0, coords.indexOf(" ")));
			double lat = Double.valueOf(coords.substring(coords.indexOf(" "), coords.length()));
			LngLatAlt geometry = new LngLatAlt(lng, lat);
			coordinates.add(geometry);
		}
		line.setCoordinates(coordinates);
		return line;
	}

	void setMultiPoint() {
		// iterate for every bike
		for (Integer key : bikeMap.keySet()) {
			List<BikeBo> bikeList = bikeMap.get(key);

			Feature feature = new Feature();
			feature.setId(Integer.toString(key));

			MultiPoint points = new MultiPoint();
			Map<String, Object> properties = new Hashtable<String, Object>();

			boolean firstRun = true;

			// iterate for every bike in list
			for (BikeBo bikeBo : bikeList) {

				// get coordinates and add to MulitPoint
				LngLatAlt geometry = new LngLatAlt(bikeBo.lng, bikeBo.lat);
				points.add(geometry);

				if (firstRun) {
					// do this once
					properties.put("name", bikeBo.getName());
					properties.put("number", bikeBo.getNumber());
					properties.put("uid", bikeBo.getUid());
					properties.put("bike", bikeBo.getBike());
					firstRun = false;
				}

			}
			feature.setProperties(properties);
			feature.setGeometry(points);
			featureCollection.add(feature);
		}
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
