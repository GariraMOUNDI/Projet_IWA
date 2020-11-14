package org.projet_iwa.alert.location.api.service;

import org.projet_iwa.alert.location.api.model.LocationDTO;
import org.projet_iwa.alert.location.api.model.Response;
import org.springframework.stereotype.Component;

@Component
public interface ILocationService {
    Response<?,?> sendLocation(LocationDTO locationDTO);
    Response<?,?> saveLocation(LocationDTO locationDTO);
}
