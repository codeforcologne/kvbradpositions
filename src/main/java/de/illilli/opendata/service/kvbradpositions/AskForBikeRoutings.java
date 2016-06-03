package de.illilli.opendata.service.kvbradpositions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.AskFor;
import de.illilli.opendata.service.Config;

public class AskForBikeRoutings implements AskFor<List<RoutingBo>> {

	private List<RoutingBo> data = new ArrayList<RoutingBo>();

	public AskForBikeRoutings(int number) throws IOException {
		String url = Config.getProperty("kvbrouting.routinglist.url") + number;
		Gson gson = new Gson();
		Type type = new TypeToken<List<RoutingBo>>() {
		}.getType();
		InputStream inputStream = new URL(url).openStream();
		String json = IOUtils.toString(inputStream);
		data = gson.fromJson(json, type);
	}

	@Override
	public List<RoutingBo> getData() {
		return data;
	}
}
