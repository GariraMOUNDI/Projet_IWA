package org.projet_iwa.alert.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "org.projet_iwa.alert.api")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
