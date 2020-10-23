package org.projet_iwa.auth.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "locations")
public class Location {
    private String location_id;

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    @Id
    public String getLocation_id() {
        return location_id;
    }
}
