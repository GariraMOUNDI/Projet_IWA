package org.projet_iwa.auth.api.service;

import org.projet_iwa.auth.api.model.User;
import org.springframework.stereotype.Component;

@Component
public interface IMailSender {
    void sendEmail(User user, String token);
}
