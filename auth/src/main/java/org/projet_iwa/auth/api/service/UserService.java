package org.projet_iwa.auth.api.service;

import org.projet_iwa.auth.api.config.KeycloakService;
import org.projet_iwa.auth.api.model.*;
import org.projet_iwa.auth.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("UserService")
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private KeycloakService keycloakService;

    @Autowired
    private MailUtil mailUtil;

    @Override
    public UserResponse createUser(UserDTO userDTO) {
        if(userDTO.checkFields() != UserResponseType.VALID_USER)
            return new UserResponse(userDTO.checkFields(), null);

        if(userRepository.existsByUsername(userDTO.getUsername()))
            return new UserResponse(UserResponseType.USER_EXIST, userDTO);

        User user_model = userFactory.createUserModel(userDTO);
        keycloakService.createKeycloakUser(user_model);
        userRepository.saveAndFlush(user_model);
        mailUtil.sendEmail(user_model);
        return new UserResponse(UserResponseType.USER_CREATED, null);
    }

    @Override
    public UserResponse loginUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user == null)
            return new UserResponse(UserResponseType.USER_NOT_EXIST, null);

        String hash_password = user.getEncryptedPassword(username + password);
        if(!hash_password.equals(user.getPassword()))
            return new UserResponse(UserResponseType.INCORRECT_PASSWORD, null);

        if(!user.isEnabled())
            return new UserResponse(UserResponseType.EMAIL_NOT_CONFIRMED, null);

        UserDTO logged_user = userFactory.createUserDTO(user);
        logged_user.setToken(keycloakService.getToken(user.getUsername(), user.getPassword()));
        return new UserResponse(UserResponseType.USER_LOGGED_IN, logged_user);
    }

}