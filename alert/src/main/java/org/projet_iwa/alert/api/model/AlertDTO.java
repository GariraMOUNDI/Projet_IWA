package org.projet_iwa.alert.api.model;

public class AlertDTO {

    private Long alert_id;

    private Long location_id;

    public AlertDTO(Long alert_id, Long location_id) {
        this.alert_id = alert_id;
        this.location_id = location_id;
    }

    public AlertDTO() {}

    public Long getAlert_id() {
        return alert_id;
    }

    public void setAlert_id(Long alert_id) {
        this.alert_id = alert_id;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }
}
