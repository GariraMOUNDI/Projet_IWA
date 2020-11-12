package org.projet_iwa.alert.api.model;

public class AlertFactory {

    public Alert createAlertModel(AlertDTO alert) {
        return new Alert(alert.getAlert_id(), alert.getLocation_id());
    }

    public AlertDTO createAlertDTO(Alert alert) {
        return new AlertDTO(alert.getAlert_id(), alert.getLocation_id());
    }
}
