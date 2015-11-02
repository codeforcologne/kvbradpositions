package de.illilli.opendata.service.kvbradpositions;

public class BikeDao {

	BikeBo bikeBo;

	public BikeDao(BikeFromDb bikeFromDb) {
		bikeBo = new BikeBo();
		bikeBo.setNumber(bikeFromDb.number);
		bikeBo.setName(bikeFromDb.name);
		bikeBo.setLng(bikeFromDb.geom.getGeometry().getFirstPoint().getY());
		bikeBo.setLat(bikeFromDb.geom.getGeometry().getFirstPoint().getX());
		bikeBo.setBike(bikeFromDb.bike);
		bikeBo.setTimestamp(bikeFromDb.modtime);
		bikeBo.setUid(bikeFromDb.uid);
	}

	public BikeBo getBikeBo() {
		return bikeBo;
	}

}
