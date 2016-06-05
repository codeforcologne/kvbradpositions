package de.illilli.opendata.service.kvbradpositions.geojson;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.geojson.Feature;
import org.geojson.LineString;
import org.geojson.LngLatAlt;

import de.illilli.opendata.service.kvbradpositions.RoutingBo;

public class MyMultiLineString {

	private List<Feature> featureList = new ArrayList<>();
	private String name;

	public MyMultiLineString(List<RoutingBo> routingList, String name) {
		this.name = name;
		for (RoutingBo routing : routingList) {
			Feature feature = new Feature();
			feature.setId(Integer.toString(routing.getNumber()));
			feature.setGeometry(getGeometry(routing.getValue()));
			feature.setProperties(getProperties(routing));
			featureList.add(feature);
		}
	}

	Map<String, Object> getProperties(RoutingBo routing) {
		Map<String, Object> properties = new Hashtable<String, Object>();
		properties.put("name", this.name);
		properties.put("timeinmillis", routing.getTimeinmillis());
		properties.put("distance", routing.getDistance());
		properties.put("modtime", routing.getModtime());
		properties.put("type", routing.getType());
		properties.put("typeString", routing.getTypeString());
		properties.put("srid", routing.getSrid());
		properties.put("number", routing.getNumber());
		return properties;
	}

	LineString getGeometry(String value) {
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

	public List<Feature> getFeatureList() {
		return featureList;
	}

}
