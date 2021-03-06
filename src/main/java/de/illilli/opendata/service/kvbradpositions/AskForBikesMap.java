package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Config;

public class AskForBikesMap implements AskFor<Map<Integer, List<BikeBo>>> {

	String url = Config.getProperty("kvblive.bikesmap.url");
	private InputStream inputStream;
	private Map<Integer, List<BikeBo>> bikesMap;

	public AskForBikesMap() throws MalformedURLException, IOException {
		inputStream = new URL(url).openStream();
		setBikesMap();
	}

	public AskForBikesMap(InputStream inputStream) throws IOException {
		this.inputStream = inputStream;
		setBikesMap();
	}

	void setBikesMap() throws IOException {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<Integer, List<BikeBo>>>() {
		}.getType();
		String json = IOUtils.toString(inputStream);
		bikesMap = gson.fromJson(json, type);
	}

	@Override
	public Map<Integer, List<BikeBo>> getData() {
		return bikesMap;
	}
}
