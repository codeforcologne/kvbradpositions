package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.Before;

public class BikesDataTablesFacadeTest {

	private static final Logger logger = Logger.getLogger(BikesDataTablesFacadeTest.class);

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException, NamingException {
		ConnectionFactory.setUpConnectionForJndi();
		String json = new BikesDataTablesFacade(new AskForBikesList()).getJson();
		logger.info(json);
	}

}
