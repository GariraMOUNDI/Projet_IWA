package org.projet_iwa.alert.location.api.controller;


import org.projet_iwa.alert.location.api.model.Location;
import org.projet_iwa.alert.location.api.model.LocationDTO;
import org.projet_iwa.alert.location.api.model.LocationResponse;
import org.projet_iwa.alert.location.api.model.Response;
import org.projet_iwa.alert.location.api.repository.LocationRepository;
import org.projet_iwa.alert.location.api.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private ILocationService iLocationService;

//    @GetMapping
//    public List<Location> getAllLocations() {
//        return locationRepository.findAll();
//    }
//
//    @GetMapping
//    @RequestMapping("{id}")
//    public Location getLocation(@PathVariable long location_id) {
//        return locationRepository.getOne(location_id);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Location addLocation(@RequestBody final Location location) {
//        return locationRepository.saveAndFlush(location);
//    }
//
//    @PostMapping("getLocation")
//    public String register(@Valid @ModelAttribute("location")
//                                   Location location, BindingResult result) {
//        locationRepository.saveAndFlush(location);
//        return "index";
//    }

    @GetMapping
    public Response<?,?> sendLocation(@RequestBody LocationDTO locationDTO){
        return iLocationService.sendLocation(locationDTO);
    }
}
