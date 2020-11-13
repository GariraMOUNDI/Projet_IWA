package org.projet_iwa.alert.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "alerts")
@Access(AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alert_id;
    private Long location_id;
    private String email;

    public Alert(Long alert_id, Long location_id, String email) {
        this.alert_id = alert_id;
        this.location_id = location_id;
        this.email = email;
    }

    public Alert() {}

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
