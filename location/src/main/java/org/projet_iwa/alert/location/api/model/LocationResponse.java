package org.projet_iwa.alert.location.api.model;

public class LocationResponse extends Response<LocationResponseType, Location> {

    public LocationResponse(LocationResponseType locationResponseType, Location location) {
        super(locationResponseType, location);
    }

    public LocationResponse(LocationResponseType locationResponseType){
        super(locationResponseType, null);
    }
}
