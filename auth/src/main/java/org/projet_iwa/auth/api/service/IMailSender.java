package org.projet_iwa.auth.api.service;

import org.projet_iwa.auth.api.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public interface IMailSender {
    void sendConfirmEmail(UserDTO userDTO, String token);
    void sendForgotEmail(UserDTO userDTO, String token);
}
