package org.projet_iwa.gateway.api.controller;

import org.projet_iwa.gateway.api.service.MicroserviceWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private MicroserviceWebRequest microserviceWebRequest;

}
