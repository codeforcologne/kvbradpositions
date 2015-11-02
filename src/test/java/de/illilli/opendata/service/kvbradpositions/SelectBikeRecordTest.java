package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.BeforeClass;

public class SelectBikeRecordTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		// setup the jndi context and the datasource
		ConnectionFactory.setUpConnectionForJndi();
	}

	public static void main(String[] args) throws SQLException,
			NamingException, IOException {
		ConnectionFactory.setUpConnectionForJndi();
		SelectForBikeAndLastPosition select = new SelectForBikeAndLastPosition(21517);
		for (BikeBo bikeBo : select.getBikeBoList()) {
			System.out.println(bikeBo);
		}
	}

}
