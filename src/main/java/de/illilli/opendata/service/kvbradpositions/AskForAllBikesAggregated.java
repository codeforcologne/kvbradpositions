package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.Config;

public class AskForAllBikesAggregated implements AskFor<Map<Integer, BikeAggregatedBo>> {

	private static final Logger logger = Logger.getLogger(AskForAllBikesAggregated.class);

	String url = Config.getProperty("kvbrouting.bikeslist.allbikesaggregated.url");
	private Map<Integer, BikeAggregatedBo> bikesMap;
	private InputStream inputStream;
	private String json;

	public AskForAllBikesAggregated() throws MalformedURLException, IOException {
		bikesMap = new Hashtable<Integer, BikeAggregatedBo>();
		inputStream = new URL(url).openStream();
	}

	public AskForAllBikesAggregated(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public Map<Integer, BikeAggregatedBo> getData() {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<Integer, BikeAggregatedBo>>() {
		}.getType();
		try {
			json = IOUtils.toString(inputStream);
		} catch (IOException e) {
			logger.error(e);
		}
		bikesMap = gson.fromJson(json, type);
		return bikesMap;
	}

}
