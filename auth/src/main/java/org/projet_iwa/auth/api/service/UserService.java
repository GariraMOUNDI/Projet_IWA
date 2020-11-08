package org.projet_iwa.auth.api.service;

import org.projet_iwa.auth.api.config.KeycloakService;
import org.projet_iwa.auth.api.model.*;
import org.projet_iwa.auth.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private KeycloakService keycloakService;

    @Override
    public UserResponse createUser(UserDTO userDTO) {
        if(userDTO.checkFields() == UserResponseType.VALID_USER){
            if(userRepository.existsByUsername(userDTO.getUsername())){
                return new UserResponse(UserResponseType.USER_EXIST, userDTO);
            }else{
                User user_model = userFactory.createUserModel(userDTO);
                keycloakService.createKeycloakUser(user_model);
                user_model = userRepository.saveAndFlush(user_model);
                return new UserResponse(UserResponseType.USER_CREATED, userFactory.createUserDTO(user_model));
            }
        }
        else
            return new UserResponse(userDTO.checkFields(), null);
    }

    @Override
    public UserResponse loginUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user == null)
            return new UserResponse(UserResponseType.USER_NOT_EXIST, null);
        else{
            String hash_password = user.getEncryptedPassword(username + password);
            if(!hash_password.equals(user.getPassword()))
                return new UserResponse(UserResponseType.INCORRECT_PASSWORD, null);
            else{
                UserDTO logged_user = userFactory.createUserDTO(user);
                logged_user.setToken(keycloakService.getToken(user.getUsername(), user.getPassword()));
                return new UserResponse(UserResponseType.USER_LOGGED_IN, logged_user);
            }
        }
    }

}