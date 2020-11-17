package org.projet_iwa.location.api.model;

public class LocationResponse extends Response<LocationResponseType, Location> {

    public LocationResponse(LocationResponseType locationResponseType){
        super(locationResponseType, null);
    }
}
