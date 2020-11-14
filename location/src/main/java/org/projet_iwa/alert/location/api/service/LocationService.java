package org.projet_iwa.alert.location.api.service;

import org.projet_iwa.alert.location.api.model.*;
import org.projet_iwa.alert.location.api.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LocationService implements ILocationService{

    @Autowired
    private KafkaTemplate<String, LocationDTO> kafkaTemplate;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationFactory locationFactory;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Override
    public LocationResponse sendLocation(LocationDTO locationDTO) {
        this.kafkaTemplate.send(topic, locationDTO);
        System.out.println("Sent sample message [" + locationDTO.getIdLocation() + "]" );
        return new LocationResponse(LocationResponseType.LOCATION_SEND);
    }

    @KafkaListener(topics = "#{'${spring.kafka.template.default-topic}'}")
    public void processMessage(@Payload LocationDTO locationDTO,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException, MessagingException {
        /*sendEmail(LocationDTO.getEmail());*/
    }

    @Override
    public LocationResponse saveLocation(LocationDTO locationDTO) {
        Location location_model = locationFactory.createLocationModel(locationDTO);
        locationRepository.saveAndFlush(location_model);
        return new LocationResponse(LocationResponseType.LOCATION_SEND);
    }

}
