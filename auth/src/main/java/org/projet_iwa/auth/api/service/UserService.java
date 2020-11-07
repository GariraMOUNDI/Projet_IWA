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
                keycloakService.createKeycloakUser(userDTO);
                User new_user = userRepository.saveAndFlush(userFactory.createUserModel(userDTO));
                return new UserResponse(UserResponseType.USER_CREATED, userFactory.createUserDTO(new_user));
            }
        }
        else
            return new UserResponse(userDTO.checkFields(), null);
    }

}