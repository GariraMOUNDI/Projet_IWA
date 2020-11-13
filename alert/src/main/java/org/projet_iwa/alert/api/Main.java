package org.projet_iwa.alert.api;

import org.projet_iwa.alert.api.model.AlertDTO;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "org.projet_iwa.alert")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
