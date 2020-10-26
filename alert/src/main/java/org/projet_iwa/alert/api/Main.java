package org.projet_iwa.alert.api;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "org.projet_iwa")
public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Alert Microservice");
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public ApplicationRunner runner(Producer producer) {
        return (args) -> {
                producer.send("Topic", "hello");
        };
    }
}
