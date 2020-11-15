package org.projet_iwa.auth.api.controller;

import org.projet_iwa.auth.api.model.Response;
import org.projet_iwa.auth.api.model.User;
import org.projet_iwa.auth.api.model.UserDTO;
import org.projet_iwa.auth.api.model.UserResponse;
import org.projet_iwa.auth.api.repository.UserRepository;
import org.projet_iwa.auth.api.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    @Qualifier("UserService")
    private IUserService iUserService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<?, ?> createUser(@RequestBody final UserDTO userDTO){
        return iUserService.createUser(userDTO);
    }

    @PostMapping("/login")
    public Response<?, ?> loginUser(@RequestBody UserDTO user){
        return iUserService.loginUser(user.getUsername(), user.getPassword());
    }

    @GetMapping("/confirmUser")
    public Response<?, ?> confirmUser(@RequestParam String token){
        return iUserService.confirmUser(token);
    }

    @PostMapping("/forgotUser")
    public Response<?, ?> forgotPassword(@RequestBody UserDTO userDTO){
        return iUserService.forgotPassword(userDTO);
    }


//    @GetMapping
//    @RolesAllowed({"user"})
//    public List<User> list() {
//        return userRepository.findAll();
//    }

//    @DeleteMapping("{id}")
//    public void deleteUser(@PathVariable Long id){
//        userRepository.deleteById(id);
//    }

//    @PutMapping("{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User user) {
//        // TODO: Ajouter ici une validation si tous
//        // les champs ont ete passes
//        // Sinon, retourner une erreur 400 (Bad Payload)
//        User existingUser = userRepository.getOne(id);
//        BeanUtils.copyProperties(user,existingUser,"user_id");
//        return userRepository.saveAndFlush(existingUser);
//    }

}
