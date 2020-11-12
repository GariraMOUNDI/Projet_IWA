package org.projet_iwa.alert.api;

//import org.projet_iwa.alert.api.model.MailSender;
//import org.projet_iwa.alert.api.model.User;
import org.projet_iwa.alert.api.model.AlertDTO;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;


@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "org.projet_iwa.alert")
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Alert Microservice");
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public ApplicationRunner runner(Producer producer) {
        AlertDTO a = new AlertDTO(new Long(100),new Long(1001));
        return (args) -> {
                producer.send("jip6qp3z-default", a);
        };
    }
}
