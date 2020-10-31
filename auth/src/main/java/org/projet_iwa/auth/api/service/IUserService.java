package org.projet_iwa.auth.api.service;

import org.projet_iwa.auth.api.model.User;
import org.projet_iwa.auth.api.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public interface IUserService {
    User createUser(UserDTO user);
}
