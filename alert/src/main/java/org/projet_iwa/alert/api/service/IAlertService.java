package org.projet_iwa.alert.api.service;

import org.projet_iwa.alert.api.model.AlertDTO;
import org.projet_iwa.alert.api.model.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@Component
public interface IAlertService {
    Response<?, ?> sendAlert(AlertDTO alertDTO);
}
