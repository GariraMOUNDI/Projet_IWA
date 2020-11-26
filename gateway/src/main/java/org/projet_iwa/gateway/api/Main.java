package org.projet_iwa.gateway.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.projet_iwa.gateway.api")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
