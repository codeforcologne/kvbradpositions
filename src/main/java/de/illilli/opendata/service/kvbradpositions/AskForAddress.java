package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.Config;

public class AskForAddress implements AskForGeo<AddressBo> {

	private static final Logger logger = Logger.getLogger(AskForAddress.class);

	String url = Config.getProperty("kvbaddress.latlng.url");
	private InputStream inputStream;
	private String json;

	public AskForAddress() throws MalformedURLException, IOException {
	}

	@Override
	public AddressBo getData(double lat, double lng) {
		String urlString = this.url + "/" + lat + "/" + lng;
		logger.debug(urlString);
		try {
			inputStream = new URL(urlString).openStream();
		} catch (IOException e) {
			logger.error(e);
		}
		Gson gson = new Gson();
		Type type = new TypeToken<AddressBo>() {
		}.getType();
		try {
			json = IOUtils.toString(inputStream);
		} catch (IOException e) {
			logger.error(e);
		}
		AddressBo address = gson.fromJson(json, type);
		return address;
	}

}
