package org.projet_iwa.location.api.model;

public class AlertDTO {

    private Long location_id;
    private Long user_id;
    private String user_token;

    public AlertDTO(Long alert_id, Long location_id, Long user_id) {
        this.location_id = location_id;
        this.user_id = user_id;
    }

    public Long getLocation_id() {
        return location_id;
    }


    public Long getUser_id() {
        return user_id;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }
}
