package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.Config;

public class AskForAllbikes implements AskFor<Map<Integer, List<BikeBo>>> {

	private static final Logger logger = Logger.getLogger(AskForAllbikes.class);

	private String url = Config.getProperty("kvblive.bikesmap.url");
	private String urlString;
	private InputStream inputStream;
	private String json;

	private Map<Integer, List<BikeBo>> bikes;

	public AskForAllbikes() {
		urlString = this.url;
	}

	/**
	 * Days
	 * 
	 * @param modtime
	 *             milliseconds
	 */
	public AskForAllbikes(long modtime) {
		urlString = this.url + "/" + modtime;
	}

	@Override
	public Map<Integer, List<BikeBo>> getData() {
		logger.debug(urlString);
		try {
			inputStream = new URL(urlString).openStream();
		} catch (IOException e) {
			logger.error(e);
		}
		Gson gson = new Gson();
		Type type = new TypeToken<Map<Integer, List<BikeBo>>>() {
		}.getType();
		try {
			json = IOUtils.toString(inputStream);
		} catch (IOException e) {
			logger.error(e);
		}
		bikes = gson.fromJson(json, type);
		return bikes;
	}

}
