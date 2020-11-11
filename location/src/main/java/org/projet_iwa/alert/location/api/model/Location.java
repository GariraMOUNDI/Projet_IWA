package org.projet_iwa.alert.location.api.model;


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
    private Long altitude;
    private String idUser;
    private Timestamp date;

    public Location(String idLocation, Long latitude, Long longitude,Long alt) {
        this.idLocation = idLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }
    public Location(){};
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

    public Long getAlt() {
        return altitude;
    }

    public void setAlt(Long altitude) {
        this.altitude = altitude;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
