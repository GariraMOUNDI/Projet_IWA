package org.projet_iwa.alert.location.api;

import org.projet_iwa.alert.location.api.model.LocationDTO;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "org.projet_iwa")
public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Location Microservice");
        SpringApplication.run(Main.class, args);
    }
}
