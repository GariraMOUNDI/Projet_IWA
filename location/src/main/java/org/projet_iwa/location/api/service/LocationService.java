package org.projet_iwa.location.api.service;

import org.projet_iwa.location.api.model.*;
import org.projet_iwa.location.api.repository.LocationRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService implements ILocationService{

    @Autowired
    private List<LocationDTO> recentLocations = new ArrayList<>();

    @Autowired
    private KafkaTemplate<String, LocationDTO> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, ClusterDTO> kafkaClusterTemplate;

    @Autowired
    private KafkaTemplate<String, AlertDTO> kafkaAlertTemplate;


//    @Autowired
//    private KafkaConsumer<String, LocationDTO> kc;

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

    // Location Producer

    @Override
    public LocationResponse sendLocation(LocationDTO locationDTO) {

        this.kafkaTemplate.send(location_topic, locationDTO);

        if (locationDTO.getCovidStatus()) {
            // Consume Clusters ...
        }

        return new LocationResponse(LocationResponseType.LOCATION_SEND);
    }

    // Location Consumer

    @KafkaListener(topics = "#{'${covid-alert.location-topic}'}")
    public void processMessage(@Payload LocationDTO locationDTO,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException, MessagingException {
        recentLocations.add(locationDTO);
        // kc.poll()
    }

    // Cluster Producer

    public void sendCluster(ClusterDTO clusterDTO) {

        this.kafkaClusterTemplate.send(cluster_topic, clusterDTO);

    }

    // Cluster Consumer

    @KafkaListener(topics = "#{'${covid-alert.cluster-topic}'}")
    public void processMessage(@Payload ClusterDTO clusterDTO,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException, MessagingException {
        // ?
    }

    // Alert Producer

    public void sendAlert(AlertDTO alertDTO) {

        this.kafkaAlertTemplate.send(alert_topic, alertDTO);

    }

}
