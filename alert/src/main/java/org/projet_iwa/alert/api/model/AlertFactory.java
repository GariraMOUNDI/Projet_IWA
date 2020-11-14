package org.projet_iwa.alert.api.model;

public class AlertFactory {

    public Alert createAlertModel(AlertDTO alertDTO) {
        return new Alert(alertDTO.getAlert_id(), alertDTO.getLocation_id(), alertDTO.getEmail());
    }

    public AlertDTO createAlertDTO(Alert alert) {
        return new AlertDTO(alert.getAlert_id(), alert.getLocation_id(), alert.getEmail());
    }
}
