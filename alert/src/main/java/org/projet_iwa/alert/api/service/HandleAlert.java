package org.projet_iwa.alert.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@Service
public class HandleAlert implements IHandleAlert {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendAlert(String emailAddress) throws AddressException, MessagingException, IOException {
        String subject = "Alerte Covid !";
        String message = "Attention ! Vous êtiez peut-être en contacte avec une personne qui a testé positif au COVID. Ne bougez pas de chez vous.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
