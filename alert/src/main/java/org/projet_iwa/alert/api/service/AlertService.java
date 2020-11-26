package org.projet_iwa.alert.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.projet_iwa.alert.api.model.AlertDTO;
import org.projet_iwa.alert.api.model.AlertResponse;
import org.projet_iwa.alert.api.model.AlertResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
    private KafkaTemplate<String, AlertDTO> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Value("${server.user-url-email}")
    private String user_url;

    @KafkaListener(topics = "#{'${spring.kafka.template.default-topic}'}")
    public void processMessage(@Payload AlertDTO alertDTO,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException, MessagingException {

        String email = getEmailFromUserMicroservice(alertDTO.getUser_id(), alertDTO.getUser_token());
        if(email == null)
            System.out.println("Parsing error");
        else
            sendEmail(email);
    }


    // For test only
    @Override
    public AlertResponse sendAlert(AlertDTO alertDTO) {
        kafkaTemplate.send(topic, alertDTO);
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
        RestTemplate request = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer "+ token);

        ResponseEntity<String> response = request.exchange(user_url + user_id, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode email = root.path("payload");
            return email.asText();
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
