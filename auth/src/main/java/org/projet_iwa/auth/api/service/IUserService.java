package org.projet_iwa.auth.api.service;

import org.projet_iwa.auth.api.model.UserDTO;
import org.projet_iwa.auth.api.model.UserResponse;
import org.springframework.stereotype.Component;

@Component
public interface IUserService {
    UserResponse createUser(UserDTO userDTO);
    UserResponse loginUser(String username, String password);
    UserResponse confirmUser(String token);
}
