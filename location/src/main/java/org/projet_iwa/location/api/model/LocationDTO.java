package org.projet_iwa.location.api.model;

import java.sql.Timestamp;

public class LocationDTO {

    private Long location_id;
    private Long latitude;
    private Long longitude;
    private Long user_id;
    private UserStatus user_status;
    private String user_token;
    private Timestamp date;

    public LocationDTO(Long location_id, Long latitude, Long longitude, Long user_id, UserStatus user_status, Timestamp date, String user_token) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user_id = user_id;
        this.user_status = user_status;
        this.date = date;
        this.user_token = user_token;
    }

    public LocationDTO(){}

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
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

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }
}