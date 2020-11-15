package org.projet_iwa.alert.api.service;

import org.projet_iwa.alert.api.model.AlertDTO;
import org.projet_iwa.alert.api.model.AlertResponse;
import org.projet_iwa.alert.api.model.AlertResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
public class AlertService implements IAlertService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private KafkaTemplate<String, AlertDTO> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @KafkaListener(topics = "#{'${spring.kafka.template.default-topic}'}")
    public void processMessage(@Payload AlertDTO alertDTO,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException, MessagingException {
        sendEmail(alertDTO.getEmail());
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
}
