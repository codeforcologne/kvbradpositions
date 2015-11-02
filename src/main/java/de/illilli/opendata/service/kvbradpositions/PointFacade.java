package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LngLatAlt;
import org.geojson.Point;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Facade;

public class PointFacade implements Facade {

	private FeatureCollection featureCollection;
	private List<BikeBo> bikeList;

	public PointFacade() throws SQLException, NamingException, IOException {
		bikeList = new SelectForAllBikesAndPositions().getBikeBoList();
		setFeatureCollection();
	}

	public PointFacade(List<BikeBo> bikeList) throws SQLException,
			NamingException, IOException {
		this.bikeList = bikeList;
		setFeatureCollection();
	}

	private void setFeatureCollection() {
		featureCollection = new FeatureCollection();
		int number = -1;
		String key = "";
		for (BikeBo bikeBo : bikeList) {

			Feature feature = new Feature();

			key = bikeBo.number + ";" + bikeBo.lng + ";" + bikeBo.lat;

			LngLatAlt coordinatesOld = new LngLatAlt();
			LngLatAlt coordinates = new LngLatAlt(bikeBo.lng, bikeBo.lat);

			if (number == bikeBo.number) {
				// pruefe, ob es sich lohnt Informationen hinzuzufuegen
				if (!coordinatesOld.equals(coordinates)) {
					Point geometry = new Point(coordinates);
					feature.setGeometry(geometry);
				}
			} else {
				// fuege das bisherige feature zur Liste hinzu
				if (number != -1) {
					featureCollection.add(feature);
				}
				// neues Feature
				number = bikeBo.number;
				feature = new Feature();
				feature.setId(Integer.toString(bikeBo.number));
				Point geometry = new Point(coordinates);
				feature.setGeometry(geometry);
			}
		}
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
