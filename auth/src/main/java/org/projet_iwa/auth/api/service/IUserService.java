package org.projet_iwa.auth.api.service;

import org.projet_iwa.auth.api.model.Response;
import org.projet_iwa.auth.api.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    Response<?, ?> createUser(UserDTO userDTO);
    Response<?, ?>loginUser(String username, String password);
    Response<?, ?> confirmUser(String token);
    Response<?, ?> forgotPassword(String username);
    Response<?,?> changeStatus(Long id, String status);
}
