package org.projet_iwa.location.api.service;

import org.projet_iwa.location.api.model.*;
import org.projet_iwa.location.api.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LocationService implements ILocationService{

    @Autowired
    private KafkaTemplate<String, LocationDTO> kafkaTemplate;

//    @Autowired
//    private KafkaConsumer<String, LocationDTO> kc;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationFactory locationFactory;

    @Value("${covid-alert.safe-topic}")
    private String safe_topic;

    @Value("${covid-alert.contact-topic}")
    private String contact_topic;

    @Value("${covid-alert.covid-topic}")
    private String covid_topic;

    @Override
    public LocationResponse sendLocation(LocationDTO locationDTO) {
        String topicToUse = safe_topic;
        if(locationDTO.getStatus() == LocationStatus.CONTACT || locationDTO.getStatus() == LocationStatus.COVID){
            if(locationDTO.getStatus() == LocationStatus.CONTACT)
                topicToUse = contact_topic;
            else{
                topicToUse = covid_topic;
                sendAlert(locationDTO);
            }
            Location location_model = locationFactory.createLocationModel(locationDTO);
            locationRepository.saveAndFlush(location_model);
        }

        this.kafkaTemplate.send(topicToUse, locationDTO);
        System.out.println("Sent sample message [" + locationDTO.getIdLocation() + "]" );
        return new LocationResponse(LocationResponseType.LOCATION_SEND);
    }

    private void sendAlert(LocationDTO locationDTO){
        // Consume safe_topic and contact_topic and check the distance then produce the location
        // to alert topic
    }

//    @KafkaListener(topics = "#{'${spring.kafka.template.default-topic}'}")
//    public void processMessage(@Payload LocationDTO locationDTO,
//                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
//                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
//                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException, MessagingException {
////        sendEmail(LocationDTO.getEmail());
////        kc.poll()
//    }

}
