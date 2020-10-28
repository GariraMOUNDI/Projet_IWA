package org.projet_iwa.alert.api;

import org.projet_iwa.alert.api.model.Location;
import org.projet_iwa.alert.api.model.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class Producer {

    private final KafkaTemplate<String, User> kafkaTemplate;

    Producer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, User user) {
        this.kafkaTemplate.send(topic, user);
        System.out.println("Sent sample message [" + user.toString() + "] to " );
    }
}
