package org.projet_iwa.alert.location.api.model;

import org.springframework.stereotype.Component;

@Component
public class LocationFactory {

    public Location createLocationModel(LocationDTO locationDTO){
        return new Location(locationDTO.getIdLocation() ,locationDTO.getLatitude(), locationDTO.getLongitude(),locationDTO.getAltitude());
    }
}
