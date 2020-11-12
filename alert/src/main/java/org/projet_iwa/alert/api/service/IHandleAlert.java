package org.projet_iwa.alert.api.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@Component
public interface IHandleAlert {

    void sendAlert(String emailAddress) throws AddressException, MessagingException, IOException;

}
