package org.projet_iwa.alert.api;


import org.projet_iwa.alert.api.model.AlertDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class Producer {

    private final KafkaTemplate<String, AlertDTO> kafkaTemplate;

    @Autowired
    Producer(KafkaTemplate<String,AlertDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, AlertDTO alert) {
        this.kafkaTemplate.send(topic, alert);
        System.out.println("Sent sample message [" + alert.getLocation_id() + "]" );
    }
}
