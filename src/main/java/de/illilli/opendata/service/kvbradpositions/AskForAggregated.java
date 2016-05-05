package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Config;

public class AskForAggregated implements AskFor<List<AggregatedBo>> {

	private static final Logger logger = Logger.getLogger(AskForAggregated.class);

	String url = Config.getProperty("kvbrouting.bikeslist.allbikesaggregated.url");
	private List<AggregatedBo> bikesList;
	private InputStream inputStream;
	private String json;

	public AskForAggregated() throws MalformedURLException, IOException {
		inputStream = new URL(url).openStream();
	}

	public AskForAggregated(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public List<AggregatedBo> getData() {
		Gson gson = new Gson();
		Type type = new TypeToken<List<AggregatedBo>>() {
		}.getType();
		try {
			json = IOUtils.toString(inputStream);
		} catch (IOException e) {
			logger.error(e);
		}
		bikesList = gson.fromJson(json, type);
		return bikesList;
	}

}
