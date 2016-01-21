package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.Config;

public class AskForAllbikeslatestposition {

	String url = Config.getProperty("kvblive.bikeslist.allbikeslatestposition.url");
	private List<BikeBo> bikesList;
	private InputStream inputStream;

	public AskForAllbikeslatestposition() throws MalformedURLException, IOException {
		bikesList = new ArrayList<BikeBo>();
		inputStream = new URL(url).openStream();
	}

	public AskForAllbikeslatestposition(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public List<BikeBo> getBikesList() throws IOException {
		Gson gson = new Gson();
		Type type = new TypeToken<List<BikeBo>>() {
		}.getType();
		String json = IOUtils.toString(inputStream);
		bikesList = gson.fromJson(json, type);
		return bikesList;
	}

}