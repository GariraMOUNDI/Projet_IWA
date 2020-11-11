package org.projet_iwa.auth.api.service;

import org.projet_iwa.auth.api.model.User;
import org.projet_iwa.auth.api.model.UserDTO;
import org.projet_iwa.auth.api.model.VerificationToken;
import org.projet_iwa.auth.api.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailUtil implements IMailSender{

    @Value("${server.url}")
    private String serverUrl;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public void sendEmail(User user) {

        String token = UUID.randomUUID().toString();
        VerificationToken verifToken = new VerificationToken();
        verifToken.setToken(token);
        verifToken.setUser_id(user.getUser_id());
        verifToken.setExpirydate(VerificationToken.EXPIRATION);
        verificationTokenRepository.saveAndFlush(verifToken);

        String recipientAddesss = user.getEmail();
        String url;
        String message;
        String subject;
//        if(!event.isReset()){
            subject = "User Account Confirmation";
            url = serverUrl+"confirmUser?token="+token;
            message = "Please confirm your account to login the App :"+"\r\n"+url;
//        }else{
//            subject = "Reset your password";
//            message = "Your new password is : " + event.getResetPassword();
//        }

        // send email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddesss);
        email.setSubject(subject);
        email.setText(message);
        javaMailSender.send(email);
    }
}

