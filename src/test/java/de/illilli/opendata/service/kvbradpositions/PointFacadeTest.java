package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.InputStream;
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

public class PointFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetJson() throws SQLException, NamingException, IOException {
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/bikeList.json");
		String json = IOUtils.toString(inputStream);

		Gson gson = new Gson();
		Type type = new TypeToken<List<BikeBo>>() {
		}.getType();
		List<BikeBo> bikeList = gson.fromJson(json, type);

		Facade facade = new PointFacade(bikeList);
		String geoJson = facade.getJson();
		System.out.println(geoJson);
	}

}
