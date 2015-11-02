package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

/**
 * Just one Bike, but all positions.
 */
public class SelectForBikeAndPositions extends SelectBike {

	public SelectForBikeAndPositions(Integer number) throws SQLException,
			NamingException, IOException {

		queryString = "/queryForBikeAndPositions.sql";
		runSelect(number);
	}
}
