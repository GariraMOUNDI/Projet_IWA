package org.projet_iwa.location.api.model;

import java.sql.Timestamp;

public class LocationDTO {

    private String location_id;
    private Long latitude;
    private Long longitude;
    private String user_id;
    private UserStatus user_status;
    private Timestamp date;

    public LocationDTO(String location_id, Long latitude, Long longitude, String user_id, UserStatus user_status, Timestamp date) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user_id = user_id;
        this.user_status = user_status;
        this.date = date;
    }


    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public UserStatus getUser_status() {
        return user_status;
    }

    public void setUser_status(UserStatus user_status) {
        this.user_status = user_status;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}