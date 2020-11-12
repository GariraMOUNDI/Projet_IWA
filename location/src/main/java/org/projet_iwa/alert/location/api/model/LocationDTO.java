package org.projet_iwa.alert.location.api.model;

import java.sql.Timestamp;

public class LocationDTO {

    private String idLocation;
    private Long latitude;
    private Long longitude;
    private Long altitude;
    private String idUser;
    private Timestamp date;

    public LocationDTO(String idLocation, Long latitude, Long longitude,Long altitude) {
        this.idLocation = idLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }


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

    public Long getAltitude() {
        return altitude;
    }

    public void setAltitude(Long altitude) {
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
