package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

public class SelectForAllBikesAndPositions extends SelectBike {

	public SelectForAllBikesAndPositions() throws SQLException,
			NamingException, IOException {

		queryString = "/queryForAllBikesAndPositions.sql";
		runSelect();
	}
}
