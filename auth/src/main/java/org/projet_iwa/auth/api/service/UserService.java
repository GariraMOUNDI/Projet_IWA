package org.projet_iwa.auth.api.service;

import org.projet_iwa.auth.api.model.User;
import org.projet_iwa.auth.api.model.UserDTO;
import org.projet_iwa.auth.api.model.UserFactory;
import org.projet_iwa.auth.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @Override
    public User createUser(UserDTO user) {
        return userRepository.saveAndFlush(userFactory.createUserModel(user));
    }

}