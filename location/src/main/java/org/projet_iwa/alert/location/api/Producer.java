package org.projet_iwa.alert.location.api;


import org.projet_iwa.alert.location.api.model.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final KafkaTemplate<String, LocationDTO> kafkaTemplate;

    @Autowired
    Producer(KafkaTemplate<String, LocationDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, LocationDTO location) {
        this.kafkaTemplate.send(topic, location);
        System.out.println("Sent sample message [" + location.getIdLocation() + "]" );
    }
}