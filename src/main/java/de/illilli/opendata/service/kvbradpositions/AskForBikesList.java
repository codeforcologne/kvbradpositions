package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.Config;

public class AskForBikesList implements AskFor<List<BikeBo>> {

	String url = Config.getProperty("kvblive.bikeslist.url");
	private InputStream inputStream;
	private List<BikeBo> bikesList;

	public AskForBikesList() throws MalformedURLException, IOException {
		inputStream = new URL(url).openStream();
		setBikesList();
	}

	public AskForBikesList(InputStream inputStream) throws IOException {
		this.inputStream = inputStream;
		setBikesList();
	}

	void setBikesList() throws IOException {
		Gson gson = new Gson();
		Type type = new TypeToken<List<BikeBo>>() {
		}.getType();
		String json = IOUtils.toString(inputStream);
		bikesList = gson.fromJson(json, type);
	}

	@Override
	public List<BikeBo> getData() {
		return bikesList;
	}
}
