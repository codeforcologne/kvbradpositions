package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

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
public class BikesAndPositionsFacade implements Facade {

	private FeatureCollection featureCollection;
	private List<BikeBo> bikeList;

	public BikesAndPositionsFacade(SelectBike selectBike) throws SQLException,
			NamingException, IOException {
		this.bikeList = selectBike.getBikeBoList();
		setFeatureCollection();
	}

	public BikesAndPositionsFacade(List<BikeBo> bikeList) throws SQLException,
			NamingException, IOException {
		this.bikeList = bikeList;
		setFeatureCollection();
	}

	void setFeatureCollection() {
		featureCollection = new FeatureCollection();
		Bikes bikes = new Bikes(this.bikeList);
		Map<Integer, List<BikeBo>> bikeMap = bikes.getBikeMap();

		for (Integer key : bikeMap.keySet()) {
			List<BikeBo> bikeList = bikeMap.get(key);
			if (bikeList.size() > 1) {
				// nur bike mit mehr als zwei Positionen
				Feature feature = new Feature();
				feature.setId(Integer.toString(key));
				LineString lineString = new LineString();
				for (BikeBo bikeBo : bikeList) {
					LngLatAlt geometry = new LngLatAlt(bikeBo.lng, bikeBo.lat);
					lineString.add(geometry);
					feature.setGeometry(lineString);
				}
				featureCollection.add(feature);
			}
		}

	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
