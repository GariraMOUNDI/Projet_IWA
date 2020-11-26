package org.projet_iwa.auth.api.service;

import org.projet_iwa.auth.api.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailUtil implements IMailSender{

    @Value("${server.url}")
    private String serverUrl;

    @Autowired
    private JavaMailSender javaMailSender;

    private void sendEmail(UserDTO userDTO, String token, boolean confirm) {
        String recipientAddesss = userDTO.getEmail();
        String subject;
        String url;
        String message;

        if(confirm){
            subject = "User account Confirmation";
            url = serverUrl+"/"+token;
            message = "Please confirm your account to login the App :"+"\r\n"+url;
        }else{
            subject = "Reset account password ";
            message = "This is your current password :  "+"\r"+token+" \n Make sure you change it at the next connection.";
        }

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddesss);
        email.setSubject(subject);
        email.setText(message);
        javaMailSender.send(email);
    }

    @Override
    public void sendConfirmEmail(UserDTO userDTO, String token) {
        sendEmail(userDTO, token, true);
    }

    @Override
    public void sendForgotEmail(UserDTO userDTO, String token) {
        sendEmail(userDTO, token, false);
    }
}

