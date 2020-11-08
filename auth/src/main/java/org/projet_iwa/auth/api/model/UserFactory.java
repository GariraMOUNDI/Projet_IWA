package org.projet_iwa.auth.api.model;

import org.springframework.stereotype.Component;

@Component
public class UserFactory{

    public User createUserModel(UserDTO userDTO){
        return new User(userDTO.getUser_id(), userDTO.getUsername(),userDTO.getFirst_name(), userDTO.getLast_name(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getPhone_number());
    }

    public UserDTO createUserDTO(User user) {
        return new UserDTO(user.getUser_id(), user.getUsername(),user.getFirst_name(), user.getLast_name(), user.getPassword(), user.getEmail(), user.getPhone_number());
    }
}