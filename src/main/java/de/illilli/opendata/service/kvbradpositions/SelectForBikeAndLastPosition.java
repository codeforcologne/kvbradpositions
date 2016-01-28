package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

@Deprecated
public class SelectForBikeAndLastPosition extends SelectBike {

	BikeBo bikeBo;

	public SelectForBikeAndLastPosition(Integer number) throws SQLException, NamingException, IOException {

		queryString = "/queryForBikeAndLastPosition.sql";
		runSelect(number);
	}
}
