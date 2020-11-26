package org.projet_iwa.gateway.api.controller;

import org.projet_iwa.gateway.api.model.Response;
import org.projet_iwa.gateway.api.service.MicroserviceWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@CrossOrigin
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private MicroserviceWebRequest microserviceWebRequest;

    @PostMapping
    @RolesAllowed("user")
    public Response<?,?> sendLocation(@RequestBody String body, @RequestParam String token){
        return microserviceWebRequest.sendLocation(body, token);
    }
}
