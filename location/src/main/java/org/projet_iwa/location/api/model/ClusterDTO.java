package org.projet_iwa.location.api.model;

import java.sql.Timestamp;

public class ClusterDTO {
    private String idCluster;
    private Long averageLatitude;
    private Long averageLongitude;
    private Timestamp approximateDate;
    private String[] idUsers;


    public ClusterDTO(Long latitude, Long longitude, Timestamp date, String[] idUsers) {
        this.averageLatitude = latitude;
        this.averageLongitude = longitude;
        this.approximateDate = date;
        this.idUsers = idUsers;
    }

    public String getIdCluster() {
        return idCluster;
    }

    public void setIdCluster(String idCluster) {
        this.idCluster = idCluster;
    }

    public Long getAverageLatitude() {
        return averageLatitude;
    }

    public void setAverageLatitude(Long averageLatitude) {
        this.averageLatitude = averageLatitude;
    }

    public Long getAverageLongitude() {
        return averageLongitude;
    }

    public void setAverageLongitude(Long averageLongitude) {
        this.averageLongitude = averageLongitude;
    }

    public Timestamp getApproximateDate() {
        return approximateDate;
    }

    public void setApproximateDate(Timestamp approximateDate) {
        this.approximateDate = approximateDate;
    }

    public String[] getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(String[] idUsers) {
        this.idUsers = idUsers;
    }
}
