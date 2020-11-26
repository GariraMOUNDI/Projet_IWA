package org.projet_iwa.location.api.model;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity(name="clusters")
@Access(AccessType.FIELD)
public class Cluster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idCluster;
    private Long averageLatitude;
    private Long averageLongitude;
    private Timestamp approximateDate;
    private String[] idUsers;


    public Cluster(Long latitude, Long longitude, Timestamp date, String[] idUsers) {
        this.averageLatitude = latitude;
        this.averageLongitude = longitude;
        this.approximateDate = date;
        this.idUsers = idUsers;
    }

    public Cluster() {}

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

    public boolean containsEach(String[] idUsers) {
        boolean[] alreadyIn = new boolean[idUsers.length];
        int found = 0;
        int givenUsersCounter = 0;
        int clusterUsersCounter = 0;

        while (found < idUsers.length && clusterUsersCounter < this.idUsers.length) {

            while (found < idUsers.length && givenUsersCounter < idUsers.length) {

                if ((!alreadyIn[givenUsersCounter]) && (this.idUsers[clusterUsersCounter].equals(idUsers[givenUsersCounter]))) {
                    found += 1;
                    alreadyIn[givenUsersCounter] = true;
                }

                givenUsersCounter += 1;
            }

            givenUsersCounter = 0;
            clusterUsersCounter += 1;
        }

        return found == idUsers.length;
    }
}
