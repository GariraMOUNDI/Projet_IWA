package org.projet_iwa.auth.api.controller;

import org.projet_iwa.auth.api.model.User;
import org.projet_iwa.auth.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @RolesAllowed({"user"})
    public List<User> list() {
        return userRepository.findAll();
    }

}
