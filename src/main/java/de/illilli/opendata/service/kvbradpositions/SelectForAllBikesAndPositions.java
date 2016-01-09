package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;

public class SelectForAllBikesAndPositions extends SelectBike {

	public SelectForAllBikesAndPositions() throws SQLException, NamingException, IOException {
		queryString = "/queryForAllBikesAndPositions.sql";
		runSelect();
	}

	public SelectForAllBikesAndPositions(int days) throws SQLException, NamingException, IOException {
		long time = System.currentTimeMillis() - (days * SelectBike.ONEDAY);
		queryString = "/queryForAllBikesAndPositionsWhereModtimeHigherDays.sql";
		runSelect(new Timestamp(time));
	}

}
