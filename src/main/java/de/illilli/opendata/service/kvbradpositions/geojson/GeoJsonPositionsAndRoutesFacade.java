package de.illilli.opendata.service.kvbradpositions.geojson;

import java.util.List;
import java.util.Map;

import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradpositions.BikeBo;
import de.illilli.opendata.service.kvbradpositions.Bikes;
import de.illilli.opendata.service.kvbradpositions.RoutingBo;

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
		MyMultiPoint point = new MyMultiPoint(bikeMap);
		featureCollection.addAll(point.getFeatureList());
		MyMultiLineString lineString = new MyMultiLineString(routingList, point.getName());
		featureCollection.addAll(lineString.getFeatureList());
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
