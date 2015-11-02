package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.junit.BeforeClass;

import com.google.gson.Gson;

/**
 * Mit dieser Klasse ist es möglich eine alle Bike-Datensätze in der Datenbank
 * abzufragen. Da ein Test eine bestehende Datenbank-Verbindung voraussetzt,
 * wird die Abfrage in einer main Methode durchgeführt.
 *
 */
public class SelectForAllBikesAndPositionsTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		// setup the jndi context and the datasource
		ConnectionFactory.setUpConnectionForJndi();
	}

	public static void main(String[] args) throws SQLException,
			NamingException, IOException {
		ConnectionFactory.setUpConnectionForJndi();
		SelectForAllBikesAndPositions select = new SelectForAllBikesAndPositions();
		List<BikeBo> bikeBoList = select.getBikeBoList();
		for (BikeBo bikeBo : bikeBoList) {
			System.out.println(bikeBo);
		}

		Gson gson = new Gson();
		String bikeListAsJson = gson.toJson(bikeBoList);
		PrintWriter out = new PrintWriter("bikeList.json");
		out.println(bikeListAsJson);
		out.close();

	}

}
