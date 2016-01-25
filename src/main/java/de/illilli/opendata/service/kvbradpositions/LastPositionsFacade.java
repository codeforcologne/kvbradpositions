package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LngLatAlt;
import org.geojson.Point;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Facade;

/**
 * Within this class Informations from kvbradlive and kvbradaddresses will be
 * integrated.
 * 
 * @author wolfram
 *
 */
public class LastPositionsFacade implements Facade {

	private List<BikeBo> bikeList;
	private FeatureCollection featureCollection = new FeatureCollection();
	final static String DATE_FORMAT = "dd.MM.yyyy hh:mm";

	public LastPositionsFacade(AskFor<List<BikeBo>> askForAllBikeslatestpositions) throws IOException {
		this.bikeList = askForAllBikeslatestpositions.getData();
		setFeatureCollection();
	}

	void setFeatureCollection() throws MalformedURLException, IOException {
		for (BikeBo bike : bikeList) {
			Feature feature = new Feature();
			LngLatAlt lngLatAlt = new LngLatAlt(bike.getLng(), bike.getLat());
			Point geometry = new Point(lngLatAlt);
			feature.setGeometry(geometry);
			Map<String, Object> properties = new Hashtable<String, Object>();
			properties.put("number", bike.getNumber());
			properties.put("name", bike.getName());
			properties.put("bike", bike.getBike());
			String timestamp = new SimpleDateFormat(DATE_FORMAT, Locale.GERMAN).format(bike.getTimestamp());
			properties.put("timestamp", timestamp);
			long unused = new Unused(bike.getTimestamp().getTime()).getDays();
			properties.put("unused", unused);
			feature.setProperties(properties);
			featureCollection.add(feature);
		}
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
