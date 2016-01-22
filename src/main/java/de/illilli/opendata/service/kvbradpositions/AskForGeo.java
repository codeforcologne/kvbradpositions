package de.illilli.opendata.service.kvbradpositions;

public interface AskForGeo<T> {

	T getData(double lag, double lng);
}
