package org.projet_iwa.alert.location.api.controller;

import org.projet_iwa.alert.location.api.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping({"/", "/index"})
    public String home() { return "index";
    }

    @Autowired
    private LocationRepository locationRepository;
}
