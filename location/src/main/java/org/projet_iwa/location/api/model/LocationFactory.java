package org.projet_iwa.location.api.model;

import org.springframework.stereotype.Component;

@Component
public class LocationFactory {

    public Location createLocationModel(LocationDTO locationDTO){
        return new Location(locationDTO.getLocation_id(), locationDTO.getLatitude(), locationDTO.getLongitude(), locationDTO.getUser_id(), locationDTO.getUser_status(), locationDTO.getDate());
    }
}