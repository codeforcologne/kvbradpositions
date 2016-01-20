package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.Facade;

public class BikesAndPositionsFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetJson() throws IOException, SQLException, NamingException {
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/bikeList.json");
		String json = IOUtils.toString(inputStream);

		Gson gson = new Gson();
		Type type = new TypeToken<List<BikeBo>>() {
		}.getType();
		List<BikeBo> bikeList = gson.fromJson(json, type);

		Facade facade = new GeoJsonLineStringFacade(bikeList);
		String geoJson = facade.getJson();
		System.out.println(geoJson);
	}

	/**
	 * This method writes the contents of bike-table to json-Object. Than I can
	 * use this data for testing without the need of a database. The file
	 * bikeList.json will be written to project-directory and can then be copied
	 * to src/test/resources. The Method needs a database with bikes in it.
	 * 
	 * @param args
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SQLException,
			NamingException, IOException {

		ConnectionFactory.setUpConnectionForJndi();

		SelectBike selectBike = new SelectForAllBikesAndPositions();
		Facade facade = new GeoJsonLineStringFacade(selectBike);
		String json = facade.getJson();
		System.out.println(json);

		PrintWriter out = new PrintWriter("bikeList.json");
		out.println(json);
		out.close();
	}
}
