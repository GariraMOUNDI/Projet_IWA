package org.projet_iwa.alert.api.model;

public class AlertDTO {

    private Long alert_id;
    private Long location_id;
    private String email;

    public AlertDTO(Long alert_id, Long location_id, String email) {
        this.alert_id = alert_id;
        this.location_id = location_id;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
