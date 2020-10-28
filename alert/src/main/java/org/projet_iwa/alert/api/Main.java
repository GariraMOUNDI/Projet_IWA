package org.projet_iwa.alert.api;

import org.projet_iwa.alert.api.model.MailSender;
import org.projet_iwa.alert.api.model.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootApplication(scanBasePackages = "org.projet_iwa")
public class Main {

    public static void main(String[] args) throws IOException, MessagingException {
        System.out.println("Welcome to Alert Microservice");
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public ApplicationRunner runner(Producer producer) {
        System.out.println("test");
        return (args) -> {
            System.out.println("testin return");
            User remy = new User();
            producer.send("Topic", remy);
        };
    }
}
