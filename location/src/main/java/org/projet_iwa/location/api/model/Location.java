package org.projet_iwa.location.api.model;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name="locations")
@Access(AccessType.FIELD)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idLocation;
    private Long latitude;
    private Long longitude;
    private String idUser;
    private UserStatus userStatus;
    private Timestamp date;

    public Location(String idLocation, Long latitude, Long longitude, String idUser, UserStatus userStatus, Timestamp date) {
        this.idLocation = idLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.idUser = idUser;
        this.userStatus = userStatus;
        this.date = date;
    }
    public Location(){}

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
