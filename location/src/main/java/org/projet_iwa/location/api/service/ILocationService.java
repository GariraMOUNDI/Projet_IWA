package org.projet_iwa.location.api.service;

import org.projet_iwa.location.api.model.LocationDTO;
import org.projet_iwa.location.api.model.Response;
import org.springframework.stereotype.Component;

@Component
public interface ILocationService {
    Response<?,?> sendLocation(LocationDTO locationDTO);
}
