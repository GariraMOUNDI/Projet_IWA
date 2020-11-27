package org.projet_iwa.alert.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.projet_iwa.alert.api.model.AlertDTO;
import org.projet_iwa.alert.api.model.AlertResponse;
import org.projet_iwa.alert.api.model.AlertResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class AlertService implements IAlertService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Value("${server.user-url-email}")
    private String user_url;

    @KafkaListener(topics = "#{'${spring.kafka.template.default-topic}'}")
    public void processMessage(@Payload String alertDTOString,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException, MessagingException {
//        AlertDTO alertDTO = toAlertDTO(alertDTOString);
//        if(alertDTO != null){
//            String email = getEmailFromUserMicroservice(alertDTO.getUser_id(), alertDTO.getUser_token());
//            if(email == null)
//                System.out.println("Parsing error");
//            else
//                sendEmail(email);
//        }else
//            System.out.println("Error !!!!");
        System.out.println(alertDTOString);
        sendEmail(alertDTOString);
    }


    // For test only
    @Override
    public AlertResponse sendAlert(AlertDTO alertDTO) {
        kafkaTemplate.send(topic, toJSONString(alertDTO));
        System.out.println("Sent sample message [" + alertDTO.getLocation_id() + "]" );
        return new AlertResponse(AlertResponseType.ALERT_SEND);
    }

    private void sendEmail(String email){
        String subject = "Alerte Covid !";
        String message = "Attention ! Vous êtiez peut-être en contacte avec une personne qui a testé positif au COVID. Ne bougez pas de chez vous.";

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setText(message);
        mailSender.send(mail);
    }

    private String getEmailFromUserMicroservice(Long user_id, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ResponseEntity<String> response = userRequestWithAuthentication(token)
                .exchange("/" + user_id,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode email = root.path("payload");
            return email.asText();
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    protected RestTemplate userRequestWithAuthentication(String token){
        return new RestTemplateBuilder()
                .rootUri(user_url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ token)
                .build();
    }

    private ObjectMapper map = new ObjectMapper();

    private String toJSONString(Object obj){
        try {
            return map.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private AlertDTO toAlertDTO(String jsonString) {
        try {
            JsonNode root = map.readTree(jsonString);
            return new AlertDTO(
                    root.path("location_id").asLong(),
                    root.path("user_id").asLong(),
                    root.path("user_token").asText());
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
