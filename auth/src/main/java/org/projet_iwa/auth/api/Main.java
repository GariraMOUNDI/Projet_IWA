package org.projet_iwa.auth.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("org.projet_iwa")
@SpringBootApplication(scanBasePackages = "org.projet_iwa.auth.api")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
