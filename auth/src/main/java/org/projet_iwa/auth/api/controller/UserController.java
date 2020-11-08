package org.projet_iwa.auth.api.controller;

import org.projet_iwa.auth.api.model.User;
import org.projet_iwa.auth.api.model.UserDTO;
import org.projet_iwa.auth.api.model.UserResponse;
import org.projet_iwa.auth.api.repository.UserRepository;
import org.projet_iwa.auth.api.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService iUserService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody final UserDTO userDTO){
        return iUserService.createUser(userDTO);
    }

    @PostMapping("/login")
    public UserResponse loginUser(@RequestParam String username, @RequestParam String password){
        return iUserService.loginUser(username, password);
    }


    @GetMapping
    @RolesAllowed({"user"})
    public List<User> list() {
        return userRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // TODO: Ajouter ici une validation si tous
        // les champs ont ete passes
        // Sinon, retourner une erreur 400 (Bad Payload)
        User existingUser = userRepository.getOne(id);
        BeanUtils.copyProperties(user,existingUser,"user_id");
        return userRepository.saveAndFlush(existingUser);
    }

}
