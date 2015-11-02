package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.illilli.opendata.service.Facade;

@Path("/")
public class Service {

	/**
	 * Method to return all positions of all bicyles like a GeoJson -
	 * LineString. Example: <a
	 * href="http://localhost:8080/kvbradpositions/service">/kvbradpositions
	 * /service</a>
	 * 
	 * @return
	 * @throws IOException
	 * @throws NamingException
	 * @throws SQLException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/")
	public String getAllBikesAndPositions() throws SQLException,
			NamingException, IOException {
		Facade facade = new BikesAndPositionsFacade(
				new SelectForAllBikesAndPositions());
		return facade.getJson();
	}

	/**
	 * Method to return all positions of all bicyles like a GeoJson -
	 * LineString. Example: <a
	 * href="http://localhost:8080/kvbradpositions/service/datatable"
	 * >/kvbradpositions/service/datatable</a>
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/datatable")
	public String getAllBikesForDataTables() throws SQLException,
			NamingException, IOException {
		Facade facade = new BikesDataTablesFacade(
				new SelectForAllBikesAndPositions());
		return facade.getJson();
	}

	/**
	 * Method to return all positions of all bicyles like a GeoJson -
	 * LineString. Example: <a
	 * href="http://localhost:8080/kvbradpositions/service/21577"
	 * >/kvbradpositions/service/&lt;number&gt;</a>
	 * 
	 * @param number
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{number}")
	public String getSimpleLineStridsng(@PathParam("number") int number)
			throws SQLException, NamingException, IOException {
		Facade facade = new BikesAndPositionsFacade(
				new SelectForBikeAndPositions(number));
		return facade.getJson();
	}

}
