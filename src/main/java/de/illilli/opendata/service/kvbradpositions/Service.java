package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import de.illilli.opendata.service.Facade;

@Path("/")
public class Service {

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	/**
	 * Method to return all positions of all bicyles like a GeoJson -
	 * LineString. Example:
	 * <a href="http://localhost:8080/kvbradpositions/service/geojson" >
	 * /kvbradpositions /service/geojson</a>
	 * 
	 * @return
	 * @throws IOException
	 * @throws NamingException
	 * @throws SQLException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/geojson")
	public String getAllBikesAndPositions() throws SQLException, NamingException, IOException {
		Facade facade = new GeoJsonLineStringFacade(new AskForAllbikes());
		return facade.getJson();
	}

	/**
	 * Method to return all positions of all bicyles like a GeoJson - LineString
	 * newer than specified days. Example:
	 * <a href="http://localhost:8080/kvbradpositions/service/geojson/1" >
	 * /kvbradpositions /service/geojson/&lt;days&gt;</a>
	 * 
	 * @param days
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/geojson/{days}")
	public String getAllBikesAndPositions(@PathParam("days") int days)
			throws SQLException, NamingException, IOException {
		long daysInMillis = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(days);
		Facade facade = new GeoJsonLineStringFacade(new AskForAllbikes(daysInMillis));
		return facade.getJson();
	}

	/**
	 * Method to return all positions of all bicyles like a GeoJson -
	 * LineString. Example:
	 * <a href="http://localhost:8080/kvbradpositions/service/datatable" >
	 * /kvbradpositions/service/datatable</a>
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/datatable")
	public String getAllBikesForDataTables() throws SQLException, NamingException, IOException {
		Facade facade = new BikesDataTablesFacade(new AskForBikesList());
		return facade.getJson();
	}

	/**
	 * Method to return all positions of all bicyles like a GeoJson -
	 * LineString. Example: <a href=
	 * "http://localhost:8080/kvbradpositions/service/bike/21577?geojson" >
	 * /kvbradpositions/service/geojson/bike/&lt;number&gt;?geojson</a>
	 * 
	 * @param number
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/bike/{number}")
	public String getSimpleLineStridsng(@PathParam("number") int number)
			throws SQLException, NamingException, IOException {
		boolean geojson = request.getParameter("geojson") != null;
		boolean datatables = request.getParameter("datatables") != null;
		Facade facade = new GeoJsonLineStringFacade(new AskForBikesList(number).getData());
		return facade.getJson();
	}

	/**
	 * Method to return all positions of all bicyles like a GeoJson -
	 * LineString. Examples:
	 * <ul>
	 * <li><a href=
	 * "http://localhost:8080/kvbradpositions/service/allbikeslatestposition" >
	 * /kvbradpositions/service/allbikeslatestposition</a></li>
	 * <li><a href=
	 * "http://localhost:8080/kvbradpositions/service/allbikeslatestposition?geojson"
	 * > /kvbradpositions/service/allbikeslatestposition?geojson</a></li>
	 * <li><a href=
	 * "http://localhost:8080/kvbradpositions/service/allbikeslatestposition?datatables"
	 * > /kvbradpositions/service/allbikeslatestposition?datatables</a></li>
	 * </ul>
	 * 
	 * @param number
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/allbikeslatestposition")
	public String getAllbikeslatestposition() throws SQLException, NamingException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		boolean geojson = request.getParameter("geojson") != null;
		boolean datatables = request.getParameter("datatables") != null;
		Facade facade = null;
		if (geojson) {
			facade = new LastPositionsGeoJsonFacade(new AskForAllbikeslatestposition());
		} else if (datatables) {
			facade = new LastPositionsDataTablesFacade(new AskForAllbikeslatestposition(), new AskForAggregated());
		} else {
			facade = new LastPositionsFacade(new AskForAllbikeslatestposition());
		}

		return facade.getJson();
	}

}
