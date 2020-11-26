package org.projet_iwa.location.api.model;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="locations")
@Access(AccessType.FIELD)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long location_id;

    private Long latitude;
    private Long longitude;
    private Long user_id;
    private UserStatus user_status;
    private Timestamp date;

    public Location(Long location_id, Long latitude, Long longitude, Long user_id, UserStatus user_status, Timestamp date) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user_id = user_id;
        this.user_status = user_status;
        this.date = date;
    }
    public Location(){}

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long idLocation) {
        this.location_id = idLocation;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long idUser) {
        this.user_id = idUser;
    }

    public UserStatus getUser_status() {
        return user_status;
    }

    public void setUser_status(UserStatus userStatus) {
        this.user_status = userStatus;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}