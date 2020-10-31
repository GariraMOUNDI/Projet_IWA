package org.projet_iwa.auth.api.model;

import org.springframework.stereotype.Component;

@Component
public class UserFactory{

    public User createUserModel(UserDTO user){
        return new User(user.getUser_id(),user.getFirst_name(), user.getLast_name(), user.getPassword(), user.getEmail(), user.getPhone_number(), user.getLocations());
    }
}