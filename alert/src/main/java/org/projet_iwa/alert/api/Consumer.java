package org.projet_iwa.alert.api;

//import org.projet_iwa.alert.api.model.Location;
import org.projet_iwa.alert.api.model.MailSender;
//import org.projet_iwa.alert.api.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

//import javax.mail.MessagingException;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Component
public class Consumer {


    @KafkaListener(topics = "jip6qp3z-default")
    public void processMessage(String message,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException, MessagingException {
        System.out.println(message);
        MailSender.sendEmail(message);
    }
}
