package org.projet_iwa.gateway.api.controller;

import org.projet_iwa.gateway.api.model.Response;
import org.projet_iwa.gateway.api.service.MicroserviceWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MicroserviceWebRequest microserviceWebRequest;

    @PostMapping("/create")
    public Response<?, ?> createUser(@RequestBody String body){
        return microserviceWebRequest.createUser(body);
    }

    @PostMapping("/login")
    public Response<?, ?> loginUser(@RequestBody String body){
        return microserviceWebRequest.loginUser(body);
    }

    @GetMapping("/confirmUser")
    public Response<?, ?> confirmUser(@RequestParam String token){
        return microserviceWebRequest.confirmUser(token);
    }

    @PostMapping("/forgotUser")
    public Response<?, ?> forgotPassword(@RequestBody String body){
        return microserviceWebRequest.forgotPassword(body);
    }


    @GetMapping("/changeStatus/{id}")
    @RolesAllowed("user")
    public Response<?,?> changeStatus(@PathVariable Long id, @RequestParam String status, @RequestParam String token){
        return microserviceWebRequest.changeStatus(id, status, token);
    }

    @GetMapping("/email/{id}")
    @RolesAllowed("user")
    public Response<?,?> getUserEmail(@PathVariable Long id, @RequestParam String token){
        return microserviceWebRequest.getUserEmail(id, token);
    }
}
