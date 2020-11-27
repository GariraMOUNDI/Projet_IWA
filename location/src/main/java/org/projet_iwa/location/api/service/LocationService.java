package org.projet_iwa.location.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.projet_iwa.location.api.config.KafkaConsumerConfig;
import org.projet_iwa.location.api.model.*;
import org.projet_iwa.location.api.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.UUID;

@Service
public class LocationService implements ILocationService{

    private List<LocationDTO> recentLocations;

    @Autowired
    private KafkaTemplate<String, LocationDTO> kafkaLocationTemplate;

    @Autowired
    private KafkaTemplate<String, ClusterDTO> kafkaClusterTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaAlertTemplate;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationFactory locationFactory;

    @Value("${covid-alert.location-topic}")
    private String location_topic;

    @Value("${covid-alert.cluster-topic}")
    private String cluster_topic;

    @Value("${covid-alert.alert-topic}")
    private String alert_topic;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    LocationService() {
        this.recentLocations = new ArrayList<>();

        Timer timer = new Timer("DetectionRunnerThreadTimer");
        DetectionRunnerThread task = new DetectionRunnerThread(this.recentLocations,120000);
        timer.schedule(task, 60000);
    }

    // Location Producer

    @Override
    public LocationResponse sendLocation(LocationDTO locationDTO) {

        this.kafkaLocationTemplate.send(location_topic, locationDTO);

        if (locationDTO.getUser_status() == UserStatus.COVID) {
            // Consume Clusters ...
        }

        return new LocationResponse(LocationResponseType.LOCATION_SEND);
    }


    // Location Consumer

//    @KafkaListener(topics = "#{'${covid-alert.location-topic}'}")
//    public void processMessage(@Payload LocationDTO locationDTO,
//                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
//                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
//                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException, MessagingException {
//        recentLocations.add(locationDTO);
//    }

    // Cluster Producer

    public void sendCluster(ClusterDTO clusterDTO) {

        this.kafkaClusterTemplate.send(cluster_topic, clusterDTO);

    }

    // Cluster Consumer

//    @KafkaListener(topics = "#{'${covid-alert.location-topic}'}")
//    public void processMessage(@Payload LocationDTO locationDTO,
//                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
//                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
//                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException, MessagingException {
//        // ?
//        System.out.println("Consume!!!");
//    }

    // Alert Producer

//    public void sendAlert(AlertDTO alertDTO) {
//
//        this.kafkaAlertTemplate.send(alert_topic, alertDTO);
//
//    }

    // Simple way to threat location

    @Override
    public Response<?, ?> threatLocation(LocationDTO locationDTO) {
        if(locationDTO.getUser_status() == UserStatus.SAFE ){
            kafkaLocationTemplate.send(location_topic, locationDTO);
            return new LocationResponse(LocationResponseType.LOCATION_SEND);
        }

        if(locationDTO.getUser_status() == UserStatus.CONTACT){
            Location location = locationFactory.createLocationModel(locationDTO);
            locationRepository.save(location);
            kafkaLocationTemplate.send(location_topic, locationDTO);
            return new LocationResponse(LocationResponseType.LOCATION_SEND);
        }

        if(locationDTO.getUser_status() == UserStatus.COVID){
//            Location location = locationFactory.createLocationModel(locationDTO);
//            locationRepository.save(location);
//            KafkaConsumerConfig.setCurrentLocation(locationDTO.getLatitude(), locationDTO.getLongitude());
//            for(LocationDTO locationDTO1 : KafkaConsumerConfig.poll(bootstrapAddress, UUID.randomUUID().toString(), location_topic))
//                kafkaAlertTemplate.send(alert_topic, locationDTOToAlertDTO(locationDTO1));

            kafkaAlertTemplate.send(alert_topic, locationDTOToAlertDTOString(locationDTO));
            return new LocationResponse(LocationResponseType.LOCATION_SEND);


        }

        return null;
    }

    private ObjectMapper map = new ObjectMapper();

    private String locationDTOToAlertDTOString(LocationDTO locationDTO){
        AlertDTO alert  = new AlertDTO(locationDTO.getLocation_id(), locationDTO.getUser_id(), locationDTO.getUser_token());
        try {
            return map.writeValueAsString(alert);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


//    @KafkaListener(topics = "#{'${covid-alert.location-topic}'}")
//    @KafkaListener(
//            id = "jip6qp3z-consumers",
//            topics = "#{'${covid-alert.location-topic}'}",
//            containerFactory = "filterKafkaListenerContainerFactoryByDate",
//            autoStartup = "true")
    public void threatMessage(){

    }
}
