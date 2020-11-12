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

    public Alert(Long alert_id, Long location_id) {
        this.alert_id = alert_id;
        this.location_id = location_id;
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
}
