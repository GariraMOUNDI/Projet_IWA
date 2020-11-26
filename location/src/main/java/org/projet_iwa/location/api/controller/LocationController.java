package org.projet_iwa.location.api.controller;


import org.projet_iwa.location.api.model.LocationDTO;
import org.projet_iwa.location.api.model.Response;
import org.projet_iwa.location.api.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/location")
@CrossOrigin
public class LocationController {

    @Autowired
    private ILocationService iLocationService;

    @PostMapping
    @RolesAllowed("user")
    public Response<?,?> sendLocation(@RequestBody LocationDTO locationDTO){
        return iLocationService.sendLocation(locationDTO);
    }
}
