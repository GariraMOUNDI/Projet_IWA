package org.projet_iwa.auth.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "locations")
@Access(AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long location_id;

    @ManyToMany(mappedBy = "locations")
    @JsonIgnore
    private List<User> users;

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }

    @Id
    public Long getLocation_id() {
        return location_id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
